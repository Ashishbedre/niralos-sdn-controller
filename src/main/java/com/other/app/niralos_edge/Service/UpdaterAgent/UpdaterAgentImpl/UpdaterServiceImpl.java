package com.other.app.niralos_edge.Service.UpdaterAgent.UpdaterAgentImpl;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.apache.kafka.clients.consumer.ConsumerRecords;
//import org.apache.kafka.clients.consumer.KafkaConsumer;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.TopicPartition;
//import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.kafka.core.DefaultKafkaProducerFactory;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.core.ProducerFactory;
//import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.other.app.niralos_edge.Model.HypervisorNetworkStatsModel;
import com.other.app.niralos_edge.Model.HypervisorStatsModel;
import com.other.app.niralos_edge.Model.InternalDataModels;
import com.other.app.niralos_edge.Model.ListOfIsoModel;
import com.other.app.niralos_edge.Model.NodeUpdateDto;
import com.other.app.niralos_edge.Model.VmNetworkStatsModel;
import com.other.app.niralos_edge.Model.VmStatsModel;
import com.other.app.niralos_edge.Repository.HypervisorNetworkStatsRepo;
import com.other.app.niralos_edge.Repository.HypervisorStatsRepo;
import com.other.app.niralos_edge.Repository.InternalDataRepositorys;
import com.other.app.niralos_edge.Repository.VmNetworkInterfaceRepo;
import com.other.app.niralos_edge.Repository.VmStatsRepo;
import com.other.app.niralos_edge.Service.HypervisorStat.Backend.HypervisorStatsService;
import com.other.app.niralos_edge.Service.HypervisorSubnet.HypervisorSubnetService;
import com.other.app.niralos_edge.Service.NodeBoot.NodeBootInterface;
import com.other.app.niralos_edge.Service.SavingInternalData.InternalService;
import com.other.app.niralos_edge.Service.UpdaterAgent.UpdaterService;
import com.other.app.niralos_edge.Service.VMManagement.VmMangementService;
import com.other.app.niralos_edge.Service.VmCreation.VmCreation;
import com.other.app.niralos_edge.Service.VmModification.VmModificationService;
import com.other.app.niralos_edge.dto.AddInterfaceDto;
import com.other.app.niralos_edge.dto.CreateVmConfigDto;
import com.other.app.niralos_edge.dto.HypervisorNetworkIntFaceCreationDto;
import com.other.app.niralos_edge.dto.InternalDataDto;
import com.other.app.niralos_edge.dto.PorxmoxInterfaceBody;
import com.other.app.niralos_edge.dto.VmUpdateDto;
import com.other.app.niralos_edge.dto.KafkaSender.KafkaMessageSenderDto;
import com.other.app.niralos_edge.dto.updaterDto.DataEntityDto;

@Service
public class UpdaterServiceImpl implements UpdaterService {

	private static final Logger logger=LoggerFactory.getLogger("EDGE: UpdaterServiceImpl");
	@Value("${updater.agent.ip}")
	private String updaterIp;
	
	@Autowired
	InternalDataRepositorys internalDataRepositorys;
	
	@Autowired
	HypervisorStatsRepo hypervisorStatsRepo;
	
	@Autowired
	HypervisorNetworkStatsRepo hypervisorNetworkStatsRepo;
	
	@Autowired
	VmStatsRepo vmStatsRepo;
	
	@Autowired
	VmNetworkInterfaceRepo vmNetworkInterfaceRepo;
	
	@Autowired
	VmMangementService vmMangementService;
	
	@Autowired
	InternalService internalService;
	
	@Autowired
	VmCreation vmCreation;
	
	@Autowired
	NodeBootInterface bootInterface;
	
	@Autowired
	HypervisorStatsService hypervisorStatsService;
	
	@Autowired
	HypervisorSubnetService hypervisorSubnetService;
	
	@Autowired
	VmModificationService vmModificationService; 
	
//	TopicPartition topicPartition;
//
//	private KafkaConsumer<String, String> kafkaConsumer;
//	
//	public UpdaterServiceImpl() {
//		
//        Properties props = new Properties();
//        
//        props.put("bootstrap.servers", "localhost:9092");
//        props.put("group.id", "group-1");
//        props.put(ConsumerConfig.GROUP_INSTANCE_ID_CONFIG, "456");
//        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//
//        kafkaConsumer = new KafkaConsumer<>(props);
//    }
//	
//	private KafkaTemplate<String,KafkaMessageSenderDto> kafkaTemplate()
//	{
//		return new KafkaTemplate<>(producerFactory());
//	}
//	
//	public ProducerFactory<String, KafkaMessageSenderDto> producerFactory()
//	{
//		Map<String, Object> configProps = new HashMap<>();
//		configProps.put("bootstrap.servers", "localhost:9092");
//	    configProps.put("group.id", "group-1");
//	    configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
//	    configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());
//	    
//        return new DefaultKafkaProducerFactory<>(configProps);
//	}
	
	@Override
	public String settingPartition() {

		try {
			
			WebClient proxmoxClient = WebClient.builder()
		                                       .baseUrl("http://"+updaterIp+":8089/api/v1")
				                               .build();
			
			DataEntityDto entityDto=proxmoxClient.get()
					                .uri("/siteAndContactInfo")
			                        .retrieve()
			                        .bodyToMono(DataEntityDto.class)
			                        .block();
			
//			runningKafkaAgent(entityDto.getKafkaPartition(),entityDto.getDeploymentId(),entityDto.getTenantName());
			
		} catch (Exception e) {
			
			logger.error("Unable to Set Partition In Kafka"+e);
		}
		return "Partition Set Successfully";
	}

//	@Override
//	public void runningKafkaAgent(Integer partition,String deploymentId,String tenantName) {
//		
//		String depId=deploymentId;
//		String tentId=tenantName;
//
//		topicPartition = new TopicPartition("edge-data", partition);
//        kafkaConsumer.assign(Collections.singletonList(topicPartition));
//                
//        while (true) {
//        	
//            ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(500));
//            
//            for (ConsumerRecord<String, String> record : records) {
//            	
//            	KafkaMessageSenderDto senderDto=new KafkaMessageSenderDto();
//            	
//            	if(record.value().isEmpty())
//    			{
//    				
//    			}else {
//    				
//    				try {
//						
//    					ObjectMapper objectMapper=new ObjectMapper();
//        				
//        				String jsonString=record.value();
//    					
//        				JsonNode jsonNode= objectMapper.readTree(jsonString);
//        				
//        				Integer id=jsonNode.get("id").asInt();
//        				
//        				switch (id) {
//						case 1: {
//							
//							senderDto.setDeploymentId(depId);
//							senderDto.setTenantName(tentId);
//			            	List<InternalDataModels> list=internalDataRepositorys.getCoreDetails();
//			            	senderDto.setInternalDataModels(list);
//			            	
//			            	List<HypervisorStatsModel> data=new ArrayList<>();
//			            	List<HypervisorNetworkStatsModel> data2=new ArrayList<>();
//			            	List<VmStatsModel> data3=new ArrayList<>();
//			            	List<VmNetworkStatsModel> data4=new ArrayList<>();
//			            	List<ListOfIsoModel> data5=new ArrayList<>();
//			            	
//			            	
//			            	for (InternalDataModels temp : list) {
//			            		
//			            		HypervisorStatsModel mm= hypervisorStatsRepo.getData(temp.getEdgeClientId());
//			            		data.add(mm);
//			            		List<HypervisorNetworkStatsModel> nn= hypervisorNetworkStatsRepo.findByEdgeClientId(temp.getEdgeClientId());
//			            		for (HypervisorNetworkStatsModel rec : nn) {
//				    			
//			            			data2.add(rec);
//								}
//			            		List<VmStatsModel> bb=vmStatsRepo.findData(temp.getEdgeClientId());
//			            		for (VmStatsModel consumer : bb) {
//									
//			            			data3.add(consumer);
//								}
//			            	 	List<VmNetworkStatsModel> vv = vmNetworkInterfaceRepo.findByEdgeId(temp.getEdgeClientId());
//			            	 	
//			            	 	for (VmNetworkStatsModel consumed : vv) {
//									
//			            	 		data4.add(consumed);
//								}
//			            		
//			            	 	List<ListOfIsoModel> qaz=hypervisorStatsService.getListOfIso(temp.getEdgeClientId());
//			            	 	
//			            	 	for (ListOfIsoModel consum : qaz) {
//									
//			            	 		ListOfIsoModel model=new ListOfIsoModel();
//			            	 		
//			            	 		model.setCreatedTime(consum.getCreatedTime());
//			            	 		model.setEdgeClientId(consum.getEdgeClientId());
//			            	 		model.setFormat(consum.getFormat());
//			            	 		model.setSize(consum.getSize());
//			            	 		model.setVolumeId(consum.getVolumeId());
//			            	 		
//			            	 		data5.add(model);
//								}
//							}
//			            	
//			            	senderDto.setHypervisorNetworkStatsModels(data2);
//			            	senderDto.setHypervisorStatsModels(data);
//			            	senderDto.setVmNetworkStatsModels(data4);
//			            	senderDto.setVmStatsModels(data3);
//			            	senderDto.setListOfIsoModels(data5);
//			            	
//			            	kafkaTemplate().send("edge-topic", senderDto);
//							
//						}
//						break;
//						
//						case 2: {
//							
//							vmMangementService.startVm(jsonNode.get("vmId").asLong(), jsonNode.get("edgeClientId").asText());
//						}
//						break;
//						
//						case 3: {
//							
//							vmMangementService.stopVm(jsonNode.get("vmId").asLong(), jsonNode.get("edgeClientId").asText());
//						}
//						break;
//						
//						case 4: {
//							
//							vmMangementService.restartVm(jsonNode.get("vmId").asLong(), jsonNode.get("edgeClientId").asText());
//						}
//						break;
//						
//						case 5: {
//							
//							vmMangementService.deleteVm(jsonNode.get("vmId").asLong(), jsonNode.get("edgeClientId").asText());
//						}
//						
//						break;
//						
//						case 6: {
//							 
//							InternalDataDto mode=new InternalDataDto();
//							
//							mode.setHypervisorIp(jsonNode.get("hypervisorIp").asText());
//							mode.setHypervisorPort(jsonNode.get("hypervisorPort").asText());
//							mode.setHypervisorToken(jsonNode.get("hypervisorToken").asText());
//							mode.setPassword(jsonNode.get("password").asText());
//							mode.setUserName(jsonNode.get("userName").asText());
//							
//							internalService.saveInternalData(mode);
//						}
//						break;
//						
//						case 7: {
//							
//                            InternalDataDto mode=new InternalDataDto();
//							
//							mode.setHypervisorIp(jsonNode.get("hypervisorIp").asText());
//							mode.setHypervisorPort(jsonNode.get("hypervisorPort").asText());
//							mode.setHypervisorToken(jsonNode.get("hypervisorToken").asText());
//							mode.setPassword(jsonNode.get("password").asText());
//							mode.setUserName(jsonNode.get("userName").asText());
//							
//							internalService.updateInternalData(mode, jsonNode.get("edgeClientId").asText());
//							
//						}
//						break;
//						
//						case 8: {
//							
//							internalService.deleteInternalData(jsonNode.get("edgeClientId").asText());
//						}
//						break;
//						
//						case 9: {
//							
//							CreateVmConfigDto vmConfigDto=new CreateVmConfigDto();
//							
//							vmConfigDto.setCores(jsonNode.get("cores").asText());
//							vmConfigDto.setEdgeClientId(jsonNode.get("edgeClientId").asText());
//							vmConfigDto.setIso(jsonNode.get("iso").asText());
//							vmConfigDto.setMemory(jsonNode.get("memory").asText());
//							vmConfigDto.setName(jsonNode.get("name").asText());
//							vmConfigDto.setProvisionStatus(jsonNode.get("provisionStatus").asText());
//							vmConfigDto.setSockets(jsonNode.get("sockets").asText());
//							vmConfigDto.setStorageSpace(jsonNode.get("storageSpace").asText());
//							vmConfigDto.setVmid(jsonNode.get("vmId").asLong());
//							
//							JsonNode interfaces=jsonNode.get("interfaces");
//							List<AddInterfaceDto> json=objectMapper.readValue(interfaces.toString(), new TypeReference<List<AddInterfaceDto>>() {});
//							vmConfigDto.setInterfaces(json);
//							
//							vmCreation.createVm(vmConfigDto);
//						}
//						break;
//						
//						case 10: {
//							
//							NodeUpdateDto dto=new NodeUpdateDto();
//							
//							dto.setCommand(jsonNode.get("command").asText());
//							
//							bootInterface.nodeRebootandShutdown(dto, jsonNode.get("edgeClientId").asText());
//							
//						}
//						break;
//						
//						case 11: {
//							
//							HypervisorNetworkIntFaceCreationDto dto=new HypervisorNetworkIntFaceCreationDto();
//							
//							dto.setAutostart(jsonNode.get("autostart").asText());
//							dto.setCidr(jsonNode.get("cidr").asText());
//							dto.setIface(jsonNode.get("iface").asText());
//							dto.setType(jsonNode.get("type").asText());
//							
//							hypervisorSubnetService.createInterface(jsonNode.get("edgeClientId").asText(), dto);
//						}
//						break;
//						
//						case 12: {
//							
//							VmUpdateDto model=new VmUpdateDto();
//							
//							model.setCores(jsonNode.get("cores").asText());
//							model.setMemory(jsonNode.get("memory").asText());
//							model.setSockets(jsonNode.get("sockets").asText());
//							model.setVmId(jsonNode.get("vmId").asLong());
//							
//							JsonNode interfaces=jsonNode.get("deleteInterfaces");
//							List<PorxmoxInterfaceBody> json=objectMapper.readValue(interfaces.toString(), new TypeReference<List<PorxmoxInterfaceBody>>() {});
//							model.setDeleteInterfaces(json);
//							
//							JsonNode interfacess=jsonNode.get("addInterfaces");
//							List<AddInterfaceDto> jsonn=objectMapper.readValue(interfacess.toString(), new TypeReference<List<AddInterfaceDto>>() {});
//							model.setAddInterfaces(jsonn);
//							
//							vmModificationService.updateVm(model, jsonNode.get("edgeClientId").asText());
//							
//						}
//						default:
//							
//							throw new IllegalArgumentException("Unexpected value:"+id);
//						}
//        				
//					} catch (Exception e) {
//						
//						logger.error("Unable to Perform Kafka Operations"+e);
//					}
//    				
//    				logger.info("///////////Kafka Executed Successfully///////////");
//    				
//    			}
//            }
//            
//        }
//	}

}
