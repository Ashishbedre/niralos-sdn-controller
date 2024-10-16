package com.other.app.niralos_edge.Service.EdgeHardware.HardwareImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.other.app.niralos_edge.Model.InternalDataModels;
import com.other.app.niralos_edge.Repository.InternalDataRepositorys;
import com.other.app.niralos_edge.dto.HardwaraVM.AuthenticationResponse;
import com.other.app.niralos_edge.dto.HardwaraVM.TokenDetails;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import javax.annotation.PostConstruct;
import javax.net.ssl.SSLException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Service
@EnableScheduling
public class EdgeAuthService {

    @Value("${proxmox.api.url}")
    private String apiUrl;
    private String storedTicket;
    private String storedCsrfToken;

    @Autowired
    InternalDataRepositorys dataRepository;

    public String getStoredTicket() {
        return storedTicket;
    }

    public String getStoredCsrfToken() {
        return storedCsrfToken;
    }


    private WebClient webClient;


    // Map to store the tokens and their expiration time for each VM by edgeClientId
    private final Map<String, TokenDetails> tokenStore = new HashMap<>();

    @PostConstruct
    public void init() throws SSLException {
        SslContext sslContext = SslContextBuilder.forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                .build();

        HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(sslContext));

        this.webClient = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .baseUrl(apiUrl)
                .build();
    }

//    @Scheduled(fixedRate = 3600000) // 1 hour
    public void authenticateAndStore(String edgeClientId) {
        String url = "/access/ticket";
        InternalDataModels data =  dataRepository.getData(edgeClientId);
        Map<String, String> body = Map.of("username", data.getUserName(), "password", data.getPassword());

        String responseBody = webClient.post()
                .uri(url)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(String.class)
                .block();  // Blocking call

        System.out.println("Raw Response Body: " + responseBody);

        // Parse the JSON response
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            AuthenticationResponse response = objectMapper.readValue(responseBody, AuthenticationResponse.class);

            // Calculate expiration time (1 hour from now)
            Instant expirationTime = Instant.now().plusSeconds(3600);

            // Store the CSRF token and ticket with expiration time in the map
            tokenStore.put(edgeClientId, new TokenDetails(
                    response.getData().getTicket(),
                    response.getData().getCsrfPreventionToken(),
                    expirationTime));

//            storedCsrfToken = response.getData().getCsrfPreventionToken();
//            storedTicket = response.getData().getTicket();
//
            System.out.println("CSRFPreventionToken: " + storedCsrfToken);
            System.out.println("Ticket: " + storedTicket);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // Method to get the stored token for a specific VM, re-authenticating if needed
    public TokenDetails getTokenForEdgeClientId(String edgeClientId) {
        TokenDetails tokenDetails = tokenStore.get(edgeClientId);

        if (tokenDetails == null || tokenDetails.isExpired()) {
            System.out.println("Token expired or not found for edgeClientId: " + edgeClientId + ". Re-authenticating...");
            authenticateAndStore(edgeClientId);
            tokenDetails = tokenStore.get(edgeClientId); // Retrieve the new token
        } else {
            System.out.println("Using cached token for edgeClientId: " + edgeClientId);
        }
        System.out.println(tokenDetails.isExpired());

        return tokenDetails;
    }

}
