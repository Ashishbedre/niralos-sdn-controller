package com.other.app.NiralosFiveGCore.BackendServices.Subscriber.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.other.app.NiralosFiveGCore.Repository.Slice.SliceMongoDb;
import com.other.app.NiralosFiveGCore.model.Slice.Session;
import com.other.app.NiralosFiveGCore.model.Slice.SliceConfigrationModel;

import org.apache.commons.lang3.RandomStringUtils;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.other.app.NiralosFiveGCore.BackendServices.Subscriber.SubscriberServices;
import com.other.app.NiralosFiveGCore.Dto.Subscriber.Slice;
import com.other.app.NiralosFiveGCore.Dto.Subscriber.FrontendDto.ApnModel;
import com.other.app.NiralosFiveGCore.Dto.Subscriber.FrontendDto.ListofApn;
import com.other.app.NiralosFiveGCore.Dto.Subscriber.FrontendDto.SliceConfigrationDto;
import com.other.app.NiralosFiveGCore.Repository.Subscriber.MongoDbRepository;
import com.other.app.NiralosFiveGCore.model.Subscriber.Subscribers;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SubscriberServicesImplementation implements SubscriberServices {
	private static final Logger loggerTypeB = LoggerFactory.getLogger("5G-FRONTEND:SubscriberServicesImplementation");
	// final Logger logger =
	// LoggerFactory.getLogger(SubscriberServicesImplementation.class);

	@Autowired
	MongoDbRepository mongoDbRepository;
	@Autowired
	SliceMongoDb SlicemongoDbRepository;
	@Autowired
	WebClient webClient;
	
	@Value("${fivegcore.vonr.ipconfiguration}")
	 public String fiveGcoreVonrIpConfiguration;
	public void setfiveGcoreVonrIpConfiguration(String fiveGcoreVonrIpConfiguration) {
		this.fiveGcoreVonrIpConfiguration = fiveGcoreVonrIpConfiguration;
	}
	@Value("${fivegcore.vonr.portconfiguration}")
	 public String fiveGcoreVonrPortConfiguration;
	public void setfiveGcoreVonrPortConfiguration(String fiveGcoreVonrPortConfiguration) {
		this.fiveGcoreVonrPortConfiguration = fiveGcoreVonrPortConfiguration;
	}
	
	

	@Override
	public ResponseEntity<String> addSubscriber(Subscribers subscribers) {
		WebClient webClient = WebClient.builder()
				.baseUrl("http://"+fiveGcoreVonrIpConfiguration+":"+fiveGcoreVonrPortConfiguration) // Set the base URL here
				.build();
		if (!subscribers.getFlag()) {
			this.insertDataInMongo(subscribers);
		} else {
			// Add subscriber and create AUC ID
			if (!subscribers.getSecurity().getOpc().toString().isEmpty()) {
			try {
				String apn_List = this.insertDataInMongo(subscribers);
				CreateAucId(subscribers, 0, webClient, apn_List);
			} catch (Exception e) {
				System.out.println("-----------------------------" +e);

				// Return a bad request response
				return ResponseEntity.badRequest().body("Error: You cannot add the subscriber");
			
			}

			// loggerTypeB.info("addSubscriber: AUC ID created: " + aucId);
			return ResponseEntity.ok()
					.body("New subscriber inserted and AUC ID created with IMSI " + subscribers.getImsi() + ".");
		}}
		return null;
	}

	public String insertDataInMongo(Subscribers subscribers) {
		loggerTypeB.info("addSubscriber: Adding a new subscriber");
		// Check if a document with the same IMSI already exists
		List<Subscribers> existingSubscribers = mongoDbRepository.findByImsi(subscribers.getImsi());
		if (!existingSubscribers.isEmpty()) {
			// If there are existing subscribers with the same IMSI, throw an exception or
			// return an error message
			loggerTypeB.warn("addSubscriber: Subscriber with IMSI {} already exists. Cannot add.",
					subscribers.getImsi());
			throw new IllegalArgumentException("Subscriber with IMSI " + subscribers.getImsi() + " already exists.");
		} else {
				// No existing document with the same IMSI, proceed with insertion
				List<String> apn_List = new ArrayList<>();
				List<Slice> slice = subscribers.getSlice();
				for (Slice iterationofslice : slice) {
					for (Session session : iterationofslice.getSession()) {
						apn_List.add(session.getName());
						Map<String, Object> apnData = this.returnApnbyid(Integer.parseInt(session.getName()));
						session.setName(apnData.get("apn").toString());

					}
					;
				}
				mongoDbRepository.insert(subscribers);
				loggerTypeB.info("addSubscriber: New subscriber inserted with IMSI " + subscribers.getImsi());
				return String.join(",", apn_List);
			}
	}

	public void CreateAucId(Subscribers subscribers, Integer sqn, WebClient webClient, String apn_list) {

		try {
			// Create request body for PUT request
			Map<String, Object> aucRequest = new HashMap<>();
			aucRequest.put("ki", subscribers.getSecurity().getK().toString());
			aucRequest.put("opc", subscribers.getSecurity().getOpc().toString());
			aucRequest.put("imsi", subscribers.getImsi().toString());
			aucRequest.put("sqn", sqn);
			aucRequest.put("amf", subscribers.getSecurity().getAmf().toString());

			// Make PUT request to create AUC ID
			Map<String, String> responseofAuc = webClient.put()
					.uri("/auc/").contentType(MediaType.APPLICATION_JSON)
					.bodyValue(aucRequest)
					.retrieve()
					.bodyToMono(new ParameterizedTypeReference<Map<String, String>>() {
					})
					.block(); // blocking for simplicity, adjust based on your needs

			String[] default_apn_data = String.join(",", apn_list).split(",");
			Boolean enable = true;
			Integer default_apn = Integer.parseInt(default_apn_data[0]);
			String msisdn = "9076543210";
			Integer ue_ambr_dl = 0;
			Integer ue_ambr_ul = 0;

			this.createSubscriberVonr(subscribers.getImsi(), enable, Integer.parseInt(responseofAuc.get("auc_id")),
					default_apn, apn_list, msisdn, ue_ambr_dl,
					ue_ambr_ul, webClient);

		} catch (Exception e) {
			loggerTypeB.error("CreateAucId : it Will work with opc");
		}
	}

	public void createSubscriberVonr(String imsi, Boolean enable, Integer auc_Id,
			Integer default_apn, String apn_list, String msisdn,
			Integer ue_ambr_dl, Integer ue_ambr_ul, WebClient webClient) {

		Map<String, Object> aucRequest = new HashMap<>();
		aucRequest.put("imsi", imsi);
		aucRequest.put("enabled", enable);
		aucRequest.put("auc_id", auc_Id);
		aucRequest.put("default_apn", default_apn);
		aucRequest.put("apn_list", apn_list);
		aucRequest.put("msisdn", msisdn);
		aucRequest.put("ue_ambr_dl", ue_ambr_dl);
		aucRequest.put("ue_ambr_ul", ue_ambr_ul);

		// Make PUT request to create AUC ID
		Map<String, String> responseofAuc = webClient.put()
				.uri("/subscriber/").contentType(MediaType.APPLICATION_JSON)
				.bodyValue(aucRequest)
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<Map<String, String>>() {
				})
				.block(); // blocking for simplicity, adjust based on your needs

	}

	@Override
	public void addSubscriberBulk(Subscribers subscribers, String quantity) {

		if (!subscribers.getFlag()) {
			this.addBulkSubscriberFunction(subscribers,quantity);
		
		} else {
			this.addBulkSubscriberFunction(subscribers,quantity);
			

	
	}
}

	public  void addBulkSubscriberFunction(Subscribers subscribers, String quantity) {
		
		WebClient webClient = WebClient.builder()
				.baseUrl("http://"+fiveGcoreVonrIpConfiguration+":"+fiveGcoreVonrPortConfiguration) // Set the base URL here
				.build();
		List<String> apn_List = new ArrayList<>();
		List<Slice> slice = subscribers.getSlice();
		for (Slice iterationofslice : slice) {
			for (Session session : iterationofslice.getSession()) {
				apn_List.add(session.getName());
				Map<String, Object> apnData = this.returnApnbyid(Integer.parseInt(session.getName()));
				session.setName(apnData.get("apn").toString());

			}
			;
		}
		
//		Capturing IMSI
		String imsi = subscribers.getImsi();
		List<Object> imsiList =   this.findImsiInPihs(subscribers.getImsi());
//		Running a loop to create Subscribers up to Quantity value
		for (int i = 0; i < Integer.parseInt(quantity); i++) {
			ObjectId _id = new ObjectId();
			String id = _id.toString();
			subscribers.set_id(id);
			
			String last =  String.format("%015d",Long.parseLong(imsi) + Long.valueOf(i));

			
			 subscribers.setImsi(last);

			    // Check if the subscriber IMSI already exists in the fetched IMSI list
			    if (imsiList.contains(subscribers.getImsi())) {
			        throw new IllegalArgumentException("Subscriber with IMSI: " + subscribers.getImsi() + " already exists in the list");
			    }

			    // Also, check if the IMSI exists in the MongoDB database
			    List<Subscribers> existingSubscribers = mongoDbRepository.findByImsi(subscribers.getImsi());
			    
			    
			    if (existingSubscribers.isEmpty()) {
			    	this.CreateAucId(subscribers, 0, webClient, String.join(",", apn_List));
			        // Insert the new subscriber since it doesn't exist in both the list and the database
			        mongoDbRepository.insert(subscribers);
			        loggerTypeB.debug("Inserted new subscriber with IMSI: {}", subscribers.getImsi());
			    } else {
			        throw new IllegalArgumentException("Subscriber with IMSI: " + subscribers.getImsi() + " already exists in the database");
			    }
		}
	}
	
	public List<Object>  findImsiInPihs(String imsi) {

		List<Object> imsiList = this.pihsSubscriberList("imsi").block(); // This will block until the result is available
	    return imsiList;
		
	}
	
public Mono<List<Object>> pihsSubscriberList(String data){
	WebClient webClient = WebClient.builder()
			.baseUrl("http://"+fiveGcoreVonrIpConfiguration+":"+fiveGcoreVonrPortConfiguration) // Set the base URL here
			.build();
	     Flux<Map> subscribersFlux = webClient
	                .get()
	                .uri("/subscriber/list")
	                .retrieve()
	                .bodyToFlux(Map.class);
	     
	     return subscribersFlux
	             .map(subscriber -> (Object) subscriber.get(data)) // Extract `imsi` from each subscriber
	             .collectList();
	     
//		System.out.println(subscriberMap.get("imsi") + "111111111111111111111111111111111111111"); 
//		return subscriberMap;

	}
	
	// Function to dynamically add leading zeros to IMSI values
	private String addLeadingZeros(String originalImsi, long number) {
		// Calculate the number of leading zeros needed based on the length of the
		// original IMSI
		int leadingZeros = 15 - originalImsi.length();
		// Append leading zeros to the IMSI value
		String paddedImsi = "0".repeat(Math.max(0, leadingZeros)) + Long.toString(number);
		return paddedImsi;
	}

	@Override
	public List<Subscribers> viewSubscriber(String imsi) {
		loggerTypeB.info("viewSubscriber: Viewing subscriber");
		return mongoDbRepository.findByImsi(imsi);
	}

	@Override
	public List<Subscribers> viewAllSubscribers(int pageNumber, int pageSize) {
		loggerTypeB.info("viewAllSubscribers: Fetching all subscribers");
		Sort sort = Sort.by(Sort.Direction.ASC, "imsi");

		Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);

		Page<Subscribers> subscribersPage = mongoDbRepository.findAll(pageable);

		List<Subscribers> subscribers = subscribersPage.getContent();
		// int totalPages = subscribersPage.getTotalPages();
		return subscribers;

	}

	@Override
	public int getTotalPages(int pageNumber, int pageSize) {
		Sort sort = Sort.by(Sort.Direction.DESC, "id");

		List<Subscribers> subscriberList=mongoDbRepository.findAll();
		if(subscriberList.isEmpty()){
			return  1;
		}
			Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
			Page<Subscribers> subscribersPage = mongoDbRepository.findAll(pageable);
		return subscribersPage.getTotalPages();
	}

	@Override
	public void deleteSubscriber(String _id) {
		WebClient webClient = WebClient.builder()
				.baseUrl("http://"+fiveGcoreVonrIpConfiguration+":"+fiveGcoreVonrPortConfiguration) // Set the base URL here
				.build();
		Optional<Subscribers> subscribers = mongoDbRepository.findById(_id);
		Subscribers subscriber = subscribers.get();
		try {
		Map<String, Object> responseofAuc = webClient.get()
				.uri("/subscriber/imsi/" + subscriber.getImsi())
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {
				})
				.block();
		webClient.delete()
				.uri("/subscriber/" + responseofAuc.get("subscriber_id"))
				.retrieve()
				.bodyToMono(String.class)
				.block();
		webClient.delete()
				.uri("/auc/" + responseofAuc.get("auc_id"))
				.retrieve()
				.bodyToMono(String.class)
				.block();
		}catch (Exception e) {
		}
		mongoDbRepository.deleteById(_id);
	}

	@Override
	public void deleteSubscriberBulk(String imsi, String quantity) {
		WebClient webClient = WebClient.builder()
				.baseUrl("http://"+fiveGcoreVonrIpConfiguration+":"+fiveGcoreVonrPortConfiguration) // Set the base URL here
				.build();
		for (int i = 0; i < Integer.parseInt(quantity); i++) {

			List<Subscribers> subscribers = new ArrayList<>();

			String last = String.format("%015d", Long.parseLong(imsi) + Long.valueOf(i));
			subscribers = mongoDbRepository.findByImsi(last);
			for (Subscribers subscribers2 : subscribers) {
			try {	
				Map<String, Object> responseofAuc = webClient.get()
						.uri("/subscriber/imsi/" + subscribers2.getImsi())
						.retrieve()
						.bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {
						})
						.block();
				
				webClient.delete()
						.uri("/subscriber/" + responseofAuc.get("subscriber_id"))
						.retrieve()
						.bodyToMono(String.class)
						.block();
				
				webClient.delete()
						.uri("/auc/" + responseofAuc.get("auc_id"))
						.retrieve()
						.bodyToMono(String.class)
						.block();
			}catch (Exception e) {
			}
				mongoDbRepository.deleteById(subscribers2.get_id());
				loggerTypeB.debug("deleteSubscriberBulk: Deleted subscriber with ID");
			}
		}
	}
	
	
	public Mono<List<Object>> aucList(String data) {
		WebClient webClient = WebClient.builder()
				.baseUrl("http://"+fiveGcoreVonrIpConfiguration+":"+fiveGcoreVonrPortConfiguration) // Set the base URL here
				.build();
		     Flux<Map> subscribersFlux = webClient
		                .get()
		                .uri("/auc/list")
		                .retrieve()
		                .bodyToFlux(Map.class);
		     
		     return subscribersFlux
		             .map(subscriber -> (Object) subscriber.get(data)) // Extract `imsi` from each subscriber
		             .collectList();
	
	}

	@Override
	public void deleteAllSubscriber() {
		WebClient webClient = WebClient.builder()
				.baseUrl("http://"+fiveGcoreVonrIpConfiguration+":"+fiveGcoreVonrPortConfiguration) // Set the base URL here
				.build();
		loggerTypeB.info("deleteAllSubscriber: Deleting all subscribers.");
		
		try {
		List<Object> subscriber  =	this.pihsSubscriberList("subscriber_id").block();
		for (Object subscriberList : subscriber) {
						webClient.delete()
			.uri("/subscriber/"+subscriberList)
			.retrieve()
			.bodyToMono(String.class)
			.block();
		}
		List<Object> aucId = this.aucList("auc_id").block();
		for (Object subscriberList : aucId) {
		webClient.delete()
		.uri("/auc/" + subscriberList)
		.retrieve()
		.bodyToMono(String.class)
		.block();
		}
		}catch (Exception e) {
			// TODO: handle exception
		}
		mongoDbRepository.deleteAll();
	}
	
	public void updateSubscriberData(String id, Subscribers subscribers) {
		Optional<Subscribers> list = mongoDbRepository.findById(id);
		List<Slice> slice = subscribers.getSlice();
		if(list.isPresent())
		{
			Subscribers subscribers2 = list.get();
			
		
			for (Slice iterationofslice : slice) {
				for (Session session : iterationofslice.getSession()) {
					Map<String, Object> apnData = this.returnApnbyid(Integer.parseInt(session.getName()));
					session.setName(apnData.get("apn").toString());

		
		
				}
				
				
				
				
			}
			subscribers2.setSlice(subscribers.getSlice());
			subscribers2.setMsisdn(subscribers.getMsisdn());
			subscribers2.setDeviceType(subscribers.getDeviceType());
			subscribers2.setSecurity(subscribers.getSecurity());
			subscribers2.setAmbr(subscribers.getAmbr());
			subscribers2.setAccess_restriction_data(subscribers.getAccess_restriction_data());
			subscribers2.setSubscriber_status(subscribers.getSubscriber_status());
			subscribers2.setNetwork_access_mode(subscribers.getNetwork_access_mode());
			subscribers2.setSubscribed_rau_tau_timer(subscribers.getSubscribed_rau_tau_timer());
			subscribers2.set__v(subscribers.get__v());
			mongoDbRepository.save(subscribers2);
		}
	}
	@Override
	public void updateSubscriber(String id, Subscribers subscribers) {
		loggerTypeB.info("updateSubscriber: Updating subscriber with ID: {}", id);
		WebClient webClient = WebClient.builder()
				.baseUrl("http://"+fiveGcoreVonrIpConfiguration+":"+fiveGcoreVonrPortConfiguration) // Set the base URL here
				.build();
	
			if (!subscribers.getFlag()) {
				this.updateSubscriberData(id,subscribers);
			}else {
				try {
					this.updateSubscriberData(id,subscribers);
					Thread.sleep(4000);
				}
				catch (Exception e) {
					System.out.println("--------------------------"+e);
				}
				try {
				String imsi_id=subscribers.getImsi();
				System.out.println(imsi_id +"=====================subscriber imsi_id============================");
				Map<String, Object>subscriberData=findSubscriberId(imsi_id,webClient);
				Integer sub_Id=(Integer)subscriberData.get("subscriber_id");
				System.out.println(sub_Id+"========================subscriber id=============================");
	 
//				Integer aucId=getAucId(imsi_id,webClient);
				Integer aucId=(Integer)subscriberData.get("auc_id");
				
				
				System.out.println(aucId+"====================auc_id===============================");
				
				
				UpdateAucById(subscribers,aucId,0 ,webClient);
				
				Boolean enable=true;
				String msisdn = "907654301234";
				Integer ue_ambr_dl = 0;
				Integer ue_ambr_ul=0;
				String apn_List =find_apn_list(subscribers);
				String[] default_apn_data = String.join(",",apn_List).split(",");
				Integer default_apn = Integer.parseInt(default_apn_data[0]);
	 
				Map<String, Object> subdata = new HashMap<>();
				subdata.put("imsi",imsi_id);
				subdata.put("auc_id",aucId);
				subdata.put("default_apn",default_apn);
				subdata.put("apn_list",apn_List);
				subdata.put("enabled",enable);
				subdata.put("msisdn",msisdn);
				subdata.put("ue_ambr_dl", ue_ambr_dl);
				subdata.put("ue_ambr_ul", ue_ambr_ul);
				SubscribersUpdateById(sub_Id,subdata,webClient);
				
				}catch (Exception e) {
					System.out.println("////////////-------------------------------------------" +e);
				}
				
				
			}
			
		}

	
	public String find_apn_list(Subscribers subscribers) {

		// Check if a document with the same IMSI already exists
		List<Subscribers> existingSubscribers = mongoDbRepository.findByImsi(subscribers.getImsi());
		List<String> apn_List = new ArrayList<>();

		if (!existingSubscribers.isEmpty()) {
			List<Slice> slice = subscribers.getSlice();
			for (Slice iterationofslice : slice) {
				for (Session session : iterationofslice.getSession()) {
					apn_List.add(session.getName());
					Map<String, Object> apnData = this.returnApnbyid(Integer.parseInt(session.getName()));
					session.setName(apnData.get("apn").toString());
					//apn_List.add(apnData.get("apn_id"));
				}
			}

		}
		return String.join(",", apn_List);
	}
	
	
	
	public void SubscribersUpdateById(Integer subscriberId,Map<String, Object> subdata,WebClient webClient) {
		
		
		
		
		try {
			String response = webClient.patch()
					.uri("/subscriber/{subscriberId}", subscriberId)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.bodyValue(subdata)
					.retrieve()
					.bodyToMono(String.class)
					.block();

			System.out.println("Response: ==update subscriber====================================" + response);

		} catch (Exception e) {
			System.err.println("Error occurred while updating the subscriber: " + e);
		}
	}
	public Map<String, Object> findSubscriberId(String imsi_id, WebClient webClient) {

		Map<String, Object> subscriberMap = webClient.get()
				.uri("/subscriber/imsi/{imsi_Id}", imsi_id)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
				.block();

		// Return the subscriber_id as Integer
		//return (Integer) subscriberMap.get("subscriber_id");
		return  subscriberMap;
	}

	public void UpdateAucById(Subscribers subscribers, Integer aucId, int sqn,WebClient webClient) {
		Map<String, Object> aucRequest = new HashMap<>();
		aucRequest.put("ki", subscribers.getSecurity().getK().toString());
		aucRequest.put("opc", subscribers.getSecurity().getOpc().toString());
		aucRequest.put("imsi", subscribers.getImsi().toString());
		aucRequest.put("sqn", sqn);
		aucRequest.put("amf", subscribers.getSecurity().getAmf().toString());

		try {
			String response = webClient.patch()
					.uri("/auc/{aucId}", aucId)
					.contentType(MediaType.APPLICATION_JSON)
					.body(Mono.just(aucRequest), Map.class)
					.retrieve()
					.bodyToMono(String.class)
					.block();
			System.out.println(response + " ============ AUC Data Updated Successfully ===============");

		} catch (Exception e) {
			System.err.println("Error occurred while updating AUC data: " + e);
		}

	}

//	public Integer getAucId(String imsi,WebClient webClient){
//		Map<String, String> responseofAuc = webClient.get()
//				.uri("/auc/imsi/{imsi_id}", imsi)
//				.accept(MediaType.APPLICATION_JSON)
//				.retrieve()
//				.bodyToMono(new ParameterizedTypeReference<Map<String, String>>() {})
//				.block();
//		return Integer.parseInt(responseofAuc.get("auc_id"));
//	}

	@Override
	public List<Subscribers> devicetagFilter(String devicetagfilter) {
		// TODO Auto-generated method stub
		loggerTypeB.info("devicetagFilter: Filtering subscribers by device type");
		List<Subscribers> list = mongoDbRepository.findAll();
		List<Subscribers> dtos = new ArrayList<>();
		for (Subscribers subscribers : list) {
			if (subscribers.getDeviceType().equals(devicetagfilter)) {
				Subscribers subscribers2 = new Subscribers();
				subscribers2.setSchema_version(subscribers.getSchema_version());
				subscribers2.set_id(subscribers.get_id());
				subscribers2.setImsi(subscribers.getImsi());
				subscribers2.setMsisdn(subscribers.getMsisdn());
				subscribers2.setDeviceType(subscribers.getDeviceType());
				subscribers2.setSecurity(subscribers.getSecurity());
				subscribers2.setAmbr(subscribers.getAmbr());
				subscribers2.setSlice(subscribers.getSlice());
				subscribers2.setAccess_restriction_data(subscribers.getAccess_restriction_data());
				subscribers2.setSubscriber_status(subscribers.getSubscriber_status());
				subscribers2.setNetwork_access_mode(subscribers.getNetwork_access_mode());
				subscribers2.setSubscribed_rau_tau_timer(subscribers.getSubscribed_rau_tau_timer());
				subscribers2.set__v(subscribers.get__v());
				dtos.add(subscribers2);
			}
		}
		return dtos;

	}

	public List<Subscribers> searchByImsiPartialData(String partialValue) {
		loggerTypeB.info("searchByImsiPartialData: Searching subscribers by partial IMSI value");
		return mongoDbRepository.findByImsiRegex(partialValue);
	}

	@Override
	public void addApnInfo(Map<String, String> apn) {

		webClient = WebClient.builder().baseUrl("http://" + fiveGcoreVonrIpConfiguration + ":" +fiveGcoreVonrPortConfiguration)
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
		ApnModel apnModel = new ApnModel();
		apnModel.setApn(apn.get("apn"));
		apnModel.setApn_ambr_dl(0);
		apnModel.setApn_ambr_ul(0);
		webClient
				.put().uri("/apn/")
				.bodyValue(apnModel)
				.retrieve()
				.toBodilessEntity()
				.subscribe(
						responseEntity -> {
							// Handle success response here
							loggerTypeB.info("APN update successful. Status code: " + responseEntity.getStatusCode());
						},
						error -> {
							// Handle the error here
							if (error instanceof WebClientResponseException) {
								WebClientResponseException webClientException = (WebClientResponseException) error;
								loggerTypeB.error("Error occurred: " + webClientException.getStatusCode() + " - "
										+ webClientException.getResponseBodyAsString());
							} else {
								// Handle other types of errors
								loggerTypeB.error("An unexpected error occurred: " + error.getMessage());
							}
						});
	}

	@Override
	public ResponseEntity<String> patchApnInfoById(Integer apn_id, Map<String, String> apnValue) {
		WebClient webClient = WebClient.builder()
				.baseUrl("http://"+fiveGcoreVonrIpConfiguration+":"+fiveGcoreVonrPortConfiguration)
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();

		HashMap<String, String> apn = new HashMap<>();
		apn.put("apn", apnValue.get("apn"));
		String response = webClient
				.patch()
				.uri("/apn/{id}", apn_id) // Passing the apn_id in the URI
				.bodyValue(apn) // Setting the body with the ApnModel to be updated
				.retrieve()
				.bodyToMono(String.class) // Expecting a response body as String
				.block();

		return ResponseEntity.ok("ok");

	}

	@Override
	public List<ListofApn> returnApnInfo() {
		WebClient webClient = WebClient.builder()
				.baseUrl("http://"+fiveGcoreVonrIpConfiguration+":"+fiveGcoreVonrPortConfiguration)
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();
		// Make a GET request to the /apn/ endpoint
		ArrayList<ApnModel> apnInfoList = webClient.get()
				.uri("/apn/list")
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<ArrayList<ApnModel>>() {
				})
				.block(); // This will block until the response is received

		List<ListofApn> listofApn = apnInfoList.stream()
				.map(getDataFromModel -> {
					ListofApn listofApnItem = new ListofApn();
					listofApnItem.setApnId(getDataFromModel.getApn_id());
					listofApnItem.setApnValue(getDataFromModel.getApn());
					// listofApnItem.setApn_ambr_ul(getDataFromModel.getApn_ambr_ul());
					// listofApnItem.setApn_ambr_dl(getDataFromModel.getApn_ambr_dl());

					return listofApnItem;
				})
				.collect(Collectors.toList());

		return listofApn;
	}

	@Override
	public Map<String, Object> returnApnbyid(Integer apn_id) {
		
		System.out.println("///////////////////////////////------------/************************");
		WebClient webClient = WebClient.builder()
				.baseUrl("http://"+fiveGcoreVonrIpConfiguration+":"+fiveGcoreVonrPortConfiguration)
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();
System.out.println(apn_id + "888888888888888888888888***********************************");
		// Make a GET request to the /apn/ endpoint
		ApnModel apnbyid = webClient.get()
				.uri("/apn/{id}", apn_id)
				.retrieve()
				.bodyToMono(ApnModel.class)
				.block(); // This will block until the response is received

		Map<String, Object> map = new HashMap<>();

		map.put("apn", apnbyid.getApn());
		map.put("apn_id", apnbyid.getApn_id());

		return map;
	}

	@Override
	public String returnApnDelete(Integer apn_id) {
		WebClient webClient = WebClient.builder()
				.baseUrl("http://"+fiveGcoreVonrIpConfiguration+":"+fiveGcoreVonrPortConfiguration)
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();

		// Make a DELETE request to the /apn/{id} endpoint
		webClient.delete()
				.uri("/apn/{id}", apn_id) // Correctly pass apn_id here
				.retrieve()
				.bodyToMono(String.class)
				.block(); // This will block until the response is received

		return "Delete successful";
	}
	@Override
	public ResponseEntity<String> addSlice(SliceConfigrationDto slice){
//				Generate a very strong id for the device.
				String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
				String sliceId =  RandomStringUtils.random(22, characters);
		
		try {
		List<SliceConfigrationModel>listofsst=SlicemongoDbRepository.findBySstAndSd( slice.getSst(),slice.getSd());
		Map<String,Object> ispresentSst=CompareSliceSst(listofsst);
		SliceConfigrationModel sliceModel = new SliceConfigrationModel();
		if (ispresentSst == null || ispresentSst.get("sstValue") == null || ispresentSst.get("sd") == null) {
			System.out.println("---------------");
//			ConvertApnFormat(slice);
			
			SliceConfigrationDto configrationDto = new SliceConfigrationDto();
       	 
       	 configrationDto.setSst(slice.getSst());
       	 configrationDto.setSd(slice.getSd());
       	 configrationDto.setDefault_indicator(slice.getDefault_indicator());
       	 configrationDto.setSession(slice.getSession());
       	ConvertApnFormat(configrationDto);
       	
//       	System.out.println(slice.getSession());
		System.out.println("--------------------");
		 for (Session dtoSession : slice.getSession()) {

             // Create a new model session and map all fields
             com.other.app.NiralosFiveGCore.model.Slice.Session modelSession = new com.other.app.NiralosFiveGCore.model.Slice.Session();
             modelSession.setApn_id(dtoSession.getApn_id());
             
             System.out.println(dtoSession.getApn_id() + " ----------------------"+ dtoSession.getName());
             
		 }
		
		
			sliceModel.setSliceId(sliceId);
			sliceModel.setSst(slice.getSst());
			sliceModel.setSd(slice.getSd());
			sliceModel.setDefault_indicator(slice.getDefault_indicator());
			sliceModel.setSession(slice.getSession());
			SlicemongoDbRepository.save(sliceModel);
			return ResponseEntity.ok()
					.body("New slice inserted " + slice.getSst());

		}else{
			return ResponseEntity.badRequest()
					.body("Slice already existed " + slice.getSst() + ".");
		}}catch (Exception e) {
			System.out.println("---------------");
		}
		return null;

	}

	public  Map<String, Object> CompareSliceSst(List<SliceConfigrationModel> listofsst) {
		for (SliceConfigrationModel list : listofsst) {
			if (list != null) {
				Integer sstval = list.getSst();
				String sd = list.getSd();
				Map <String,Object> sliceData =  new HashMap<>();
				sliceData.put("sstValue",sstval);
				sliceData.put("sd",sd);

				return sliceData;
			}
		}
		return null;

	}


	public void ConvertApnFormat(SliceConfigrationDto slice) {
		List<ListofApn>listsapn=returnApnInfo();
		for(com.other.app.NiralosFiveGCore.model.Slice.Session session : slice.getSession()) {
			Integer apnid = Integer.parseInt(session.getApn_id());
//			if (session.getName() == null || session.getName().isEmpty()) {
			for (ListofApn apn : listsapn) {
				
				if (apnid.equals(apn.getApnId())) {
					System.out.println("==================================ApnName : " + apn.getApnValue() + apn.getApnId());
					
					session.setName(apn.getApnValue());
					break;
				}
			}
//		}
		}
	}

	public ResponseEntity<String> DeleteSliceById(String Slice_Id){
		//String Slice_Id= String.valueOf(sliceId);
		if (SlicemongoDbRepository.existsById(Slice_Id)) {
			SlicemongoDbRepository.deleteById(Slice_Id);
			return ResponseEntity.ok("Slice with ID " + Slice_Id + " deleted successfully.");
		} else {
			return ResponseEntity.badRequest().body("Slice with ID " + Slice_Id + " not found.");
		}
	}

	public Optional<SliceConfigrationModel> viewSliceById(String sliceId){
		if (SlicemongoDbRepository.existsById(sliceId)) {
			return SlicemongoDbRepository.findById(sliceId);

		} else {
			return Optional.empty();
		}
	}
	
	@Override
	public List<SliceConfigrationModel>getSliceList(){
		
		List<SliceConfigrationModel> SliceList=SlicemongoDbRepository.findAll();
		return SliceList;
	}
	
	@Override
    public List<SliceConfigrationDto> getSliceListtemp(){
		
		 // Fetch the list of models from the MongoDB repository
	    List<SliceConfigrationModel> sliceList = SlicemongoDbRepository.findAll();

	    // Mapping from model to dto and modifying apn_id
	    List<SliceConfigrationDto> dtoList = sliceList.stream()
	            .map(sliceConfigrationModel -> {
	                // Create a new DTO object from the model
	                SliceConfigrationDto dto = new SliceConfigrationDto(sliceConfigrationModel);
                    
                    // Get the current apn_id from the model
                    ArrayList<Session> session = sliceConfigrationModel.getSession();
                    for (Session sliceConfigrationModel2 : session) {
                    	  String id =  sliceConfigrationModel2.getApn_id();
                    	  sliceConfigrationModel2.setName(id);
					}
                    
                    dto.setSession(session);
                    // Return the modified DTO object
                    return dto;
                })
                .collect(Collectors.toList());

        return dtoList;
    }
	
	@Override
	public void deleteAllSlice()
	{
		SlicemongoDbRepository.deleteAll();
	}
	
	@Override
	public void updateSliceInfo(String id, SliceConfigrationModel sliceConfiguration)
	{ 
		  Optional<SliceConfigrationModel> existingSliceData = SlicemongoDbRepository.findById(id);


	        if (existingSliceData.isPresent()) {
	        	 

	        	SliceConfigrationDto configrationDto = new SliceConfigrationDto();
	        	 SliceConfigrationModel sliceToUpdate = existingSliceData.get();
	        	 
	        	 configrationDto.setSst(sliceConfiguration.getSst());
	        	 configrationDto.setSd(sliceConfiguration.getSd());
	        	 configrationDto.setDefault_indicator(sliceConfiguration.getDefault_indicator());
	        	 configrationDto.setSession(sliceConfiguration.getSession());
	        	  
	        	  
	        	ConvertApnFormat(configrationDto);
	        	  sliceToUpdate.setSst(sliceConfiguration.getSst());
	        	  sliceToUpdate.setSd(sliceConfiguration.getSd());
	        	  sliceToUpdate.setDefault_indicator(sliceConfiguration.getDefault_indicator());
	        	  sliceToUpdate.setSession(sliceConfiguration.getSession());
	        	  
	            SlicemongoDbRepository.save(sliceToUpdate);

	            try {     
	                List<Subscribers> subscribers = mongoDbRepository.findBySliceSliceId(sliceConfiguration.getSliceId());
	                for (Subscribers subscribers2 : subscribers) {
	                    List<Slice> sliceList = subscribers2.getSlice();

	                    for (Slice iterationofslice : sliceList) {
	                        iterationofslice.setSst(sliceConfiguration.getSst());
	                        iterationofslice.setSd(sliceConfiguration.getSd());
	                        iterationofslice.setDefault_indicator(sliceConfiguration.getDefault_indicator());
	                        
	                        List<com.other.app.NiralosFiveGCore.model.Slice.Session> updatedSessions = new ArrayList<>();

	                        // Loop over the DTO sessions and map to model sessions
	                        for (Session dtoSession : sliceConfiguration.getSession()) {

	                            // Create a new model session and map all fields
	                            com.other.app.NiralosFiveGCore.model.Slice.Session modelSession = new com.other.app.NiralosFiveGCore.model.Slice.Session();
	                            modelSession.setApn_id(dtoSession.getApn_id());
	                            modelSession.setName(dtoSession.getName());

	                            // Map additional fields (e.g., type, qos, ambr, pcc_rule)
	                            modelSession.setType(dtoSession.getType());
	                            modelSession.setQos(dtoSession.getQos());
	                            modelSession.setAmbr(dtoSession.getAmbr());
	                            modelSession.setPcc_rule(dtoSession.getPcc_rule());


	                            // Fetch APN data and update the modelSession name
	                            Map<String, Object> apnData = this.returnApnbyid(Integer.parseInt(dtoSession.getApn_id()));
	                            modelSession.setName(apnData.get("apn").toString());

	                            // Add the updated model session to the list
	                            updatedSessions.add(modelSession);
	                        }
	                        // Set the updated model sessions back to slice
	                        iterationofslice.setSession((ArrayList<Session>) updatedSessions);
	                    }

	                    // Set the updated slices back to the subscriber and save
	                    subscribers2.setSlice((ArrayList<Slice>) sliceList);
	                    mongoDbRepository.save(subscribers2);
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	            }

	       	            
	            
	            
	        } else {
	            throw new RuntimeException("Slice configuration with ID " + id + " not found.");
	        }
		}



	// public List<String> FindDistinctImsi(String imsi);
	// @Override
	// public void searchSubscriber(String imsi) {
	// // TODO Auto-generated method stub
	// mongoDbRepository.filterByImsi(imsi);
	//
	// }

}


//subscribers.setImsi(last);
//
////String paddedImsi = String.format("%015d", Long.parseLong(imsi));
//
//
// // Check if the subscriber with the same IMSI already exists in the database
//List<Subscribers> existingSubscribers = mongoDbRepository.findByImsi(subscribers.getImsi());
//
//System.out.println("///////////////");
//
//
//if (existingSubscribers.isEmpty()) {
//	
//	
//	
//	
//    // Attempt to insert the new subscriber
//    mongoDbRepository.insert(subscribers);
//	loggerTypeB.debug("Inserted new subscriber with IMSI: {}", subscribers.getImsi());
//}else{
//	throw new IllegalArgumentException("Subscriber with IMSI: " + subscribers.getImsi() + " already exists");
//}




//SliceConfigrationDto sliceConfigrationDto = new SliceConfigrationDto();
//SliceConfigrationModel updatedSlice = existingSliceData.get();
//ArrayList<com.other.app.NiralosFiveGCore.model.Slice.Session> SliceSessionlistExist= existingSliceData.get().getSession();
//ArrayList<com.other.app.NiralosFiveGCore.model.Slice.Session>SliceSessionlistUpdate= sliceConfiguration.getSession();
//Integer sst=sliceConfiguration.getSst();
//String sd= sliceConfiguration.getSd();

//List<SliceConfigrationModel>listofsst=SlicemongoDbRepository.findBySstAndSd(sst,sd);
//Map<String,Object> ispresentSst=CompareSliceSst(listofsst);
//if (ispresentSst == null || ispresentSst.get("sstValue") == null || ispresentSst.get("sd") == null) {
//	updatedSlice.setSst(sliceConfiguration.getSst());
//	updatedSlice.setSd(sliceConfiguration.getSd());
//	for(Integer i=0;i<SliceSessionlistExist.size();i++)
//	{
//		SliceSessionlistExist.get(i).setName(SliceSessionlistUpdate.get(i).getName());
//		SliceSessionlistExist.get(i).setApn_id(SliceSessionlistUpdate.get(i).getApn_id());
//		SliceSessionlistExist.get(i).setType(SliceSessionlistUpdate.get(i).getType());
//		SliceSessionlistExist.get(i).setQos(SliceSessionlistUpdate.get(i).getQos());
//		SliceSessionlistExist.get(i).setAmbr(SliceSessionlistUpdate.get(i).getAmbr());
//		SliceSessionlistExist.get(i).setPcc_rule(SliceSessionlistUpdate.get(i).getPcc_rule());
//	
//		updatedSlice.setSession(SliceSessionlistUpdate);
//		
//	}
//
//}else {
//
//	for(Integer i=0;i<SliceSessionlistExist.size();i++)
//	{
//		SliceSessionlistExist.get(i).setName(SliceSessionlistUpdate.get(i).getName());
//		SliceSessionlistExist.get(i).setApn_id(SliceSessionlistUpdate.get(i).getApn_id());
//		SliceSessionlistExist.get(i).setType(SliceSessionlistUpdate.get(i).getType());
//		SliceSessionlistExist.get(i).setQos(SliceSessionlistUpdate.get(i).getQos());
//		SliceSessionlistExist.get(i).setAmbr(SliceSessionlistUpdate.get(i).getAmbr());
//		SliceSessionlistExist.get(i).setPcc_rule(SliceSessionlistUpdate.get(i).getPcc_rule());
//	
//		updatedSlice.setSession(SliceSessionlistUpdate);
//	}
//}




//try {     
//List<Subscribers> subscribers =   mongoDbRepository.findBySliceSliceId(sliceConfiguration.getSliceId());
//for (Subscribers subscribers2 : subscribers) {
// System.out.println(subscribers2.getImsi() + "////////////////////////////////////");
//  List<Slice> slice = subscribers2.getSlice();
//		for (Slice iterationofslice : slice) {
//			iterationofslice.setSst(sliceConfiguration.getSst());
//			iterationofslice.setSd(sliceConfiguration.getSd());
//			iterationofslice.setDefault_indicator(sliceConfiguration.getDefault_indicator());
//			
//		 List<com.other.app.NiralosFiveGCore.model.Slice.Session> updatedSessions = new ArrayList<>();
//		 
//		  for (com.other.app.NiralosFiveGCore.model.Slice.Session session : sliceConfiguration.getSession()) {
//            System.out.println("Session After Save: " + session.getName());
//     
//				System.out.println("Number of sessions: " + sliceConfiguration.getSession().size());
//				
//				
//				com.other.app.NiralosFiveGCore.model.Slice.Session modelSession = new com.other.app.NiralosFiveGCore.model.Slice.Session();
//            modelSession.setApn_id(session.getApn_id());
//            modelSession.setName(session.getName());
//
//				
//				System.out.println("--------------"+ session.getApn_id());
//				System.out.println("0.222222----------------------------------" + session.getName());
//				Map<String, Object> apnData = this.returnApnbyid(Integer.parseInt(session.getApn_id()));
//				session.setName(apnData.get("apn").toString());
//				updatedSessions.add(session);
//				System.out.println("333333333333333333333333333333333333");
//			}
//
//			
////		subscribers2.setSlice(subscribers.getSlice());
////		subscribers2.setMsisdn(subscribers.getMsisdn());
////		subscribers2.setDeviceType(subscribers.getDeviceType());
////		subscribers2.setSecurity(subscribers.getSecurity());
////		subscribers2.setAmbr(subscribers.getAmbr());
////		subscribers2.setAccess_restriction_data(subscribers.getAccess_restriction_data());
////		subscribers2.setSubscriber_status(subscribers.getSubscriber_status());
////		subscribers2.setNetwork_access_mode(subscribers.getNetwork_access_mode());
////		subscribers2.setSubscribed_rau_tau_timer(subscribers.getSubscribed_rau_tau_timer());
////		subscribers2.set__v(subscribers.get__v());
//
//        // Set the updated model sessions back to slice
//        iterationofslice.setSession(updatedSessions);
//    }
//
//    // Set the updated slices back to subscriber and save
//    subscribers2.setSlice(sliceList);
//		System.out.println("44444444444444444444444444444444444444444");
//		mongoDbRepository.save(subscribers2);
//}


//}
//}catch (Exception e) {
//// TODO: handle exception
//}s