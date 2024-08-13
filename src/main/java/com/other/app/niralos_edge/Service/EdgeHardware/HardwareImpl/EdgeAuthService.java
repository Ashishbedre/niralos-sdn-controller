package com.other.app.niralos_edge.Service.EdgeHardware.HardwareImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.other.app.niralos_edge.Model.InternalDataModels;
import com.other.app.niralos_edge.Repository.InternalDataRepositorys;
import com.other.app.niralos_edge.dto.AuthenticationResponse;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import javax.annotation.PostConstruct;
import javax.net.ssl.SSLException;
import java.util.Map;

@Service
public class EdgeAuthService {

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

    @PostConstruct
    public void init() throws SSLException {
        SslContext sslContext = SslContextBuilder.forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                .build();

        HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(sslContext));

        this.webClient = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .baseUrl("https://192.168.0.8:8006/api2/json")
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

            storedCsrfToken = response.getData().getCsrfPreventionToken();
            storedTicket = response.getData().getTicket();

            System.out.println("CSRFPreventionToken: " + storedCsrfToken);
            System.out.println("Ticket: " + storedTicket);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
