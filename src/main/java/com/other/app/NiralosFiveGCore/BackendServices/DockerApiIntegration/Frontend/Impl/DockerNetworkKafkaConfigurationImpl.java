//package com.other.app.Service.BackendServices.DockerApiIntegration.Frontend.Impl;
// 
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.annotation.TopicPartition;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.support.KafkaHeaders;
//import org.springframework.messaging.handler.annotation.Header;
//import org.springframework.messaging.handler.annotation.Payload;
//import org.springframework.stereotype.Service;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.other.app.Repository.DockerNetworkConfiguration.DockerNetworkConfigurationrepo;
//import com.other.app.Service.BackendServices.DockerApiIntegration.Backend.ServiceofNetworkfunctionCompose;
//import com.other.app.Service.BackendServices.DockerApiIntegration.Frontend.DockerNetworkKafkaConfiguration;
//import com.other.app.Service.BackendServices.InternalServices.Backend.InternalDataService;
//import com.other.app.model.NetworkConfiguration; 
//@Service
//public class DockerNetworkKafkaConfigurationImpl implements DockerNetworkKafkaConfiguration {
// 
//	@Autowired
//	DockerNetworkConfigurationrepo dockerNetworkConfigurationrepo;
//	
//	@Autowired
//	InternalDataService internalDataService;
//	
// 
//	 @Autowired
//	 ServiceofNetworkfunctionCompose serviceofNetworkfunctionCompose;
//	
////	@Override
////	public void storenetworkConfiguration( String networking,NetworkConfiguration networkConfiguration) {
////		serviceofNetworkfunctionCompose.niralosDeletionofUpfContainer();
////		serviceofNetworkfunctionCompose.niralosDeletionofAmfContainer();
////		serviceofNetworkfunctionCompose.niralosDeletionofSmfContainer();
////		switch (networking) {
////		case "0": {
////			dockerNetworkConfigurationrepo.updateIpwithoutNetworking(networkConfiguration.getN2Ip(), networkConfiguration.getN3Ip(), networkConfiguration.getLocalControllerClientId());
////			break;
////		}
////		case "1": {
////			dockerNetworkConfigurationrepo.updateIpWithNetworking(networkConfiguration.getN2Ip(),networkConfiguration.getN3Ip(),networkConfiguration.getGatewayIp(),networkConfiguration.getLocalControllerClientId());
////			break;
////		}
////		default:
////			throw new IllegalArgumentException("Unexpected value: " + networking);
////		}
////		 serviceofNetworkfunctionCompose.niralosAmf();
////		serviceofNetworkfunctionCompose.niralosSmf();
////		 serviceofNetworkfunctionCompose.niralosUpf(networking);
////		 dockerNetworkConfigurationrepo.updateNetworkingInDatabase(networking,networkConfiguration.getLocalControllerClientId());
////		}
//		private static final String REQUEST_TOPIC11 = "my_topic11";
//	 @KafkaListener(topicPartitions = {@TopicPartition(topic = REQUEST_TOPIC11, partitions = {"0"})}, groupId = "my_group_id")
//	 public void storenetworkConfiguration(@Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String networking,
//	                                       @Payload NetworkConfiguration networkConfiguration) { 
//	    System.out.println("1111111111111111111111111111111111111Received message from Kafka: {}"+ networkConfiguration);
//	     serviceofNetworkfunctionCompose.niralosDeletionofUpfContainer();
//	     serviceofNetworkfunctionCompose.niralosDeletionofAmfContainer();
//	     serviceofNetworkfunctionCompose.niralosDeletionofSmfContainer();
//	     switch (networking) {
//	     case "0": {
//	         dockerNetworkConfigurationrepo.updateIpwithoutNetworking(networkConfiguration.getN2Ip(), networkConfiguration.getN3Ip(), networkConfiguration.getLocalControllerClientId());
//	         break;
//	     }
//	     case "1": {
//	         dockerNetworkConfigurationrepo.updateIpWithNetworking(networkConfiguration.getN2Ip(), networkConfiguration.getN3Ip(), networkConfiguration.getGatewayIp(), networkConfiguration.getLocalControllerClientId());
//	         break;
//	     }
//	     default:
//	         throw new IllegalArgumentException("Unexpected value: " + networking);
//	     }
//	     serviceofNetworkfunctionCompose.niralosAmf();
//	     serviceofNetworkfunctionCompose.niralosSmf();
//	     serviceofNetworkfunctionCompose.niralosUpf(networking);
//	     dockerNetworkConfigurationrepo.updateNetworkingInDatabase(networking, networkConfiguration.getLocalControllerClientId());
//	    
//	 }
//		//private static final String REQUEST_TOPIC12 = "my_topic12";
//		private static final String REQUEST_TOPIC12 = "my_topic12";
//
//		private static final String RESPONSE_TOPIC1= "response_topic1"; 
//		@Autowired
//		private KafkaTemplate<String, Object> kafkaTemplate;
//		 @KafkaListener(topicPartitions = { @TopicPartition(topic = REQUEST_TOPIC12, partitions = { "0" }) }, 
//                 groupId = "my_group_id", 
//                 containerFactory = "kafkaListenerContainerFactory")
//  public List<NetworkConfiguration> returnnetworkConfiguration() {
//      List<NetworkConfiguration> configurations = dockerNetworkConfigurationrepo.findAll();
//      sendConfigurationsToKafka(configurations);
//      return configurations;
//  }
//
//  private void sendConfigurationsToKafka(List<NetworkConfiguration> configurations) {
//      ObjectMapper objectMapper = new ObjectMapper();
//      try {
//          String jsonConfigurations = objectMapper.writeValueAsString(configurations);
//          kafkaTemplate.send(RESPONSE_TOPIC1, jsonConfigurations);
//      } catch (JsonProcessingException e) {
//          e.printStackTrace();
//          // Handle the JSON processing exception
//      }
//  }
//	//Initial Service
//	@Override
//	public void confiurationofDockerIp(String dockern2Ip,String dockern3Ip,String gatewayIp, String dockerversionofFivegcore)
//	{
//		if(dockerNetworkConfigurationrepo.count()==0) {	
//			dockerNetworkConfigurationrepo.save(new NetworkConfiguration(dockern2Ip,dockern3Ip,gatewayIp,dockerversionofFivegcore,internalDataService.getNiralControllerClientId()) );
//		}else {
//			//No need of update service here 
////			dockerNetworkConfigurationrepo.updateIpWithNetworking(dockern2Ip,dockern3Ip,gatewayIp,internalDataService.getNiralControllerClientId());
//		System.out.println("No need of update : data is there for N2 and N3");
//		}
//	}
//
//	
// 
//	
// 
//	
// 
//}