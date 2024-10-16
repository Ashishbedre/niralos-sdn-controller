//package com.other.app.NiralosFiveGCore.BackendServices.Nef.Impl;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.other.app.NiralosFiveGCore.BackendServices.InternalServices.Backend.InternalDataService;
//import com.other.app.NiralosFiveGCore.BackendServices.Nef.NefSubscriptionService;
//import com.other.app.NiralosFiveGCore.BackendServices.NetworkFunction.Backend.Impl.CommonServiceImpl;
//import com.other.app.NiralosFiveGCore.Repository.NefRepo.SubscriptionRepository;
//import com.other.app.NiralosFiveGCore.model.NefStatus.SubscriptionStatus;
//import com.other.app.NiralosFiveGCore.model.protocol.UriProtocol;
//import org.bson.Document;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Mono;
//
//import java.util.*;
//
//@Service
//public class NefSubscriptionServiceImpl implements NefSubscriptionService {
//
//    private String localSdnNefIp;
//    @Value("${localsdnip.for.nef}")
//    public void setLocalSdnNefIp(String localSdnNefIp) {
//        this.localSdnNefIp = localSdnNefIp;
//    }
//    @Autowired
//    UriProtocol uriProtocol;
//    private String localSdnNefPort;
//    @Value("${localsdnport.for.nef}")
//    public void setLocalSdnNefPort(String localSdnNefPort) {
//        this.localSdnNefPort = localSdnNefPort;
//    }
//    @Autowired
//    SubscriptionRepository subscriptionRepository;
//    @Autowired
//    MongoTemplate mongoTemplate;
//    @Autowired
//    InternalDataService internalDataService;
//    CommonServiceImpl nfServiceImpl = new CommonServiceImpl();
//    private static final Logger loggerTypeB = LoggerFactory.getLogger("5G-FRONTEND:NefSubscriptionServiceImplementation");
//    @Override
//    public ResponseEntity<String> addNefSubscription(){
////        WebClient webClient = WebClient.builder()
////                .baseUrl("http://192.168.0.89:29599")
////                .build();
//        String nefIp = internalDataService.getnefIp();
//        String nefPort = internalDataService.getnefPort();
//
//        WebClient webClient = WebClient.builder().defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
//
//        for (int count = 1; count <= Integer.parseInt(nefIp); count++) {
//            String networkFunctionName = nfServiceImpl.nefName + count;
//            try {
//                String baseUrl = uriProtocol.getFivegcoreProtocol() + networkFunctionName + ":" + nefPort;
//                Map<String, Object> subscriptionData = new HashMap<>();
//
//                Map<String, Object> event = new HashMap<>();
//                event.put("Type", "LOCATION_REPORT");
//                event.put("ImmediateFlag", true);
//
//                List<Map<String, Object>> eventList = new ArrayList<>();
//                eventList.add(event);
//
//                Map<String, Object> subscription = new HashMap<>();
//                subscription.put("EventList", eventList);
//                subscription.put("EventNotifyUri", "http://" + localSdnNefIp + ":" + localSdnNefPort + "/v2/nef/datacollection/amf-contexts/registration-accept");
//                subscription.put("AnyUE", true);
//                subscription.put("NfId", "NEF_02");
//
//                subscriptionData.put("Subscription", subscription);
//                subscriptionData.put("SupportedFeatures", "xx");
//
////        String response = webClient.post()
////                .uri("/nnef-eventssubscription/v1/subscriptions")
////                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
////                .bodyValue(subscriptionData)
////                .retrieve()
////                .bodyToMono(String.class)
////                .block();
//
//                String responseBody = webClient.post()
//                        .uri(baseUrl + "/nnef-eventssubscription/v1/subscriptions")
//                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                        .bodyValue(subscriptionData)
//                        .exchangeToMono(response -> {
//                            HttpStatus statusCode = response.statusCode(); // Get the status code
//                            if (statusCode == HttpStatus.OK) {
//                                if (subscriptionRepository.count() == 0) {
//                                    saveSubscriptionStatus(1);
//                                    return response.bodyToMono(String.class)
//                                            .map(body -> "Success: " + body);
//
//                                } else {
//                                    SubscriptionStatus latestRecord = subscriptionRepository.findFirstByOrderByIdDesc();
//                                    if (latestRecord != null) {
//                                        Long existingId = latestRecord.getId();
//                                        UpdateSubscriptionStatus(existingId, 1);
//                                    }
//                                    return response.bodyToMono(String.class)
//                                            .map(body -> "Success: " + body);
//                                }
//
//                            } else {
////                        saveSubscriptionStatus(0);
//                                return Mono.just("Failed with status: " + statusCode);
//                            }
//                        })
//                        .block();
//            }catch (Exception e){
//        }
//    }
//        return ResponseEntity.ok().body("Subscription added successfully!.");
//    }
//    public void UpdateSubscriptionStatus(Long id, int status) {
//        Optional<SubscriptionStatus> existingRecord = subscriptionRepository.findById(id);
//
//        if (existingRecord.isPresent()) {
//            SubscriptionStatus subscriptionStatus = existingRecord.get();
//            subscriptionStatus.setStatus(status);
//            subscriptionRepository.save(subscriptionStatus);
//        }
//    }
//
//    public void saveSubscriptionStatus(int status) {
//        SubscriptionStatus subscriptionStatus = new SubscriptionStatus();
//        subscriptionStatus.setStatus(status);
//        subscriptionRepository.save(subscriptionStatus);
//    }
//
//
//    @Override
//    public void createEventNotifyUri(Map<String, Object> payload) {
//
//        if (payload == null || payload.isEmpty()) {
//            loggerTypeB.error("Payload is null or empty");
//            return;
//        }
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//            String jsonString = objectMapper.writeValueAsString(payload);
//
//            // Parse JSON String into Document
//            Document document = Document.parse(jsonString);
//
//            mongoTemplate.insert(document, "NefEventSubscription");
//            loggerTypeB.info("Document inserted successfully: " + document.toString());
//        } catch (Exception e) {
//            loggerTypeB.error("Error occurred while inserting document: ", e); // Log the full stack trace
//        }
//    }
//
//    public List<Document> getLastFiveSubscriptions() {
//        Query query = new Query();
//        query.with(Sort.by(Sort.Direction.DESC, "_id"));
//        query.limit(5);
//        return mongoTemplate.find(query, Document.class, "NefEventSubscription");
//    }
//    @Override
//    public ResponseEntity<String> NefUnSubscription() {
////        WebClient webClient = WebClient.builder()
////                .baseUrl("http://192.168.0.89:29599")
////                .build();
//        String nefIp = internalDataService.getnefIp();
//        String nefPort = internalDataService.getnefPort();
//
//        WebClient webClient = WebClient.builder().defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
//
//        for (int count = 1; count <= Integer.parseInt(nefIp); count++) {
//            String networkFunctionName = nfServiceImpl.nefName + count;
//            try {
//                String baseUrl = uriProtocol.getFivegcoreProtocol() + networkFunctionName + ":" + nefPort;
//
//                Map<String, Object> subscriptionData = new HashMap<>();
//                Map<String, Object> subscription = new HashMap<>();
//                String id = getLastSubscriptionCorrelationId();
//
//                subscription.put("subscriptionCorrelationId", id);
//                subscriptionData.put("Subscription", subscription);
//
//                String response = webClient.post()
//                        .uri(baseUrl + "/nnef-eventssubscription/v1/unsubscribe")
//                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                        .bodyValue(subscriptionData)
//                        .exchangeToMono(responsebody -> {
//                            HttpStatus statusCode = responsebody.statusCode(); // Get the status code
//                            if (statusCode == HttpStatus.OK) {
////
//                                if(subscriptionRepository.count()!=0){
//                                    SubscriptionStatus latestRecord = subscriptionRepository.findFirstByOrderByIdDesc();
//                                    if (latestRecord != null) {
//                                        Long existingId = latestRecord.getId();
//                                        UpdateSubscriptionStatus(existingId, 0);
//                                    }
//                                }
//                                return responsebody.bodyToMono(String.class).map(body -> "Success: " + body);
//
//                            } else {
//
//                                return Mono.just("Failed with status: " + statusCode);
//                            }
//                        })
//                        .block();
//
//                return ResponseEntity.ok().body("UnSubscription  successfully!.");
//            } catch (Exception e) {
//
//            }
//        }
//        return null;
//    }
//
//
//
//
//    public String getLastSubscriptionCorrelationId() {
//        Query query = new Query();
//        query.with(Sort.by(Sort.Direction.DESC, "_id"));
//
//        query.limit(1);
//
//        Document lastDocument = mongoTemplate.findOne(query, Document.class, "NefEventSubscription");
//
//        if (lastDocument != null) {
//            Document subscription = (Document) lastDocument.get("subscription");
//            if (subscription != null) {
//                return subscription.getString("subscriptionCorrelationId");
//            }
//        }
//
//        return null;
//    }
//    @Override
//    public ResponseEntity<Integer> NefSubscriptionstatus(){
//        if (subscriptionRepository.count() != 0) {
//            SubscriptionStatus latestRecord = subscriptionRepository.findFirstByOrderByIdDesc();
//            if (latestRecord != null) {
//                Integer status = latestRecord.getStatus();
//                return ResponseEntity.ok(status);
//            }
//        }
//
//        return null;
//    }
//    
//    
//    
//    
//    
//}
