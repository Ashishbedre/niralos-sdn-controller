//package com.other.app.config.TLSConfig;
//
//
//import java.io.File;
//
//import javax.net.ssl.SSLException;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.client.reactive.ReactorClientHttpConnector;
//import org.springframework.web.reactive.function.client.WebClient;
//
//import io.netty.handler.ssl.SslContextBuilder;
//import reactor.netty.http.client.HttpClient;
//import reactor.netty.tcp.SslProvider;
//
//@Configuration
//public class WebClientConfig {
//
//    @Value("${tls.certificate.path}")
//    private String certificatePath;
//
//    @Bean
//    public WebClient.Builder customWebClientBuilder() {
//        try {
//            // File paths for SSL certificates
//            File agentCertFile = new File(certificatePath, "agent.crt");
//            File agentKeyFile = new File(certificatePath, "agent.key");
//
//            // Check if certificate files exist
//            if (!agentCertFile.exists() || !agentKeyFile.exists()) {
//                throw new SSLException("Certificate files do not exist at the specified paths.");
//            }
//
//            // Build SSL context with certificates
//         // Build SSL context with certificates and trust manager
//            var sslContext = SslContextBuilder.forClient()
//                    .keyManager(agentCertFile, agentKeyFile)
//                    .trustManager(agentCertFile) 
//                    .build();
//
//
//            // Build SSL provider
//            var sslProvider = SslProvider.builder().sslContext(sslContext).build();
//
//            // Create HttpClient with SSL configuration
//            var httpClient = HttpClient.create().secure(sslProvider);
//
//            // Return the WebClient.Builder without setting the base URL
//            return WebClient.builder().clientConnector(new ReactorClientHttpConnector(httpClient));
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException("Error creating WebClient.Builder with SSL", e);
//        }
//    }
//}
//
//
