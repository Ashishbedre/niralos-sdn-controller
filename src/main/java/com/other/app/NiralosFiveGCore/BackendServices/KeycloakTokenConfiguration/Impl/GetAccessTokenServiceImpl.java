//package com.other.app.Service.BackendServices.KeycloakTokenConfiguration.Impl;
//
//import java.time.Duration;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//import org.springframework.web.reactive.function.client.WebClient;
//
//import com.other.app.Repository.InternalServices.InternalDataRepository;
//import com.other.app.Repository.KeycloakTokenConfiguration.AccessTokenRepo;
//import com.other.app.Service.BackendServices.InternalServices.Backend.InternalDataService;
//import com.other.app.Service.BackendServices.KeycloakTokenConfiguration.GetAccessTokenService;
//import com.other.app.Service.BackendServices.NetworkFunction.Backend.Impl.CommonServiceImpl;
//import com.other.app.Service.BackendServices.PfcpInfo.Backend.Impl.PfcpInfoServiceImpl;
//import com.other.app.model.AccesTokenModel;
//import com.other.app.model.GetAccessTokenModel;
//import com.other.app.model.LoginRequest;
//
//@Configuration
//@EnableScheduling
//@Service
//public class GetAccessTokenServiceImpl implements GetAccessTokenService {
//
//	@Autowired
//	AccessTokenRepo accessTokenRepo;
//
//	@Autowired
//	InternalDataService internalDataService;
//
//	@Autowired
//	InternalDataRepository internalDataRepository;
//	CommonServiceImpl commonServiceImpl = new CommonServiceImpl();
//	final Logger logger = LoggerFactory.getLogger(PfcpInfoServiceImpl.class);
//	private String loginUsername;
//
//	@Value("${login.username}")
//	public void setKeycloakUsername(String loginUsername) {
//		this.loginUsername = loginUsername;
//	}
//
//	private String loginPassword;
//
//	@Value("${login.password}")
//	public void setKeycloakPassword(String loginPassword) {
//		this.loginPassword = loginPassword;
//	}
//
//	private String niralapigatewayPort;
//
//	@Value("${niralapigateway.port}")
//	public void setKeycloakPort(String niralapigatewayPort) {
//		this.niralapigatewayPort = niralapigatewayPort;
//	}
//
//	private String authenticationProtocol;
//
//	@Value("${authentication.protocol}")
//	public void setKeycloakprotocol(String authenticationProtocol) {
//		this.authenticationProtocol = authenticationProtocol;
//	}
//
//	@Override
//	// @Scheduled(fixedRate = 30000)
//	public void getAccessToken() {
//		String niralControllerIp = internalDataService.getNiralControllerIp();
//		LoginRequest loginRequest = new LoginRequest();
//		loginRequest.setUsername(loginUsername);
//		loginRequest.setPassword(loginPassword);
//		WebClient client = WebClient.builder()
//				.baseUrl(authenticationProtocol + "://" + niralControllerIp + ":" + niralapigatewayPort)
//				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)// Port should be updated
//																							// accordingly
//				.build();
//		try {
//			AccesTokenModel getToken = client.post().uri("/auth/login").contentType(MediaType.APPLICATION_JSON)
//					.bodyValue(loginRequest).retrieve().bodyToMono(AccesTokenModel.class).timeout(Duration.ofSeconds(3))
//					.block();
//			GetAccessTokenModel dumpAccessToken = new GetAccessTokenModel(1, getToken.getAccess_token());
//			accessTokenRepo.save(dumpAccessToken);
//			logger.info("Access Token is generated for Niral Controller");
//		} catch (Exception e) {
//			logger.info("Authorization Failed : Agent to Niral Controller" + e);
//		}
//
//	}
//
//}
