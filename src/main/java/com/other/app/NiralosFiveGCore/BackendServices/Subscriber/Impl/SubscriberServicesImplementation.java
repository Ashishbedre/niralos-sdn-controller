package com.other.app.NiralosFiveGCore.BackendServices.Subscriber.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.other.app.NiralosFiveGCore.BackendServices.Subscriber.SubscriberServices;
import com.other.app.NiralosFiveGCore.Repository.Subscriber.MongoDbRepository;
import com.other.app.NiralosFiveGCore.model.Subscriber.Subscribers;

@Service
public class SubscriberServicesImplementation implements SubscriberServices {
	final Logger logger = LoggerFactory.getLogger(SubscriberServicesImplementation.class);


	@Autowired
	MongoDbRepository mongoDbRepository;
//	@Autowired
//	@Qualifier("secondMongoTemplate")
//	MongoTemplate secondMongoTemplate;
	
	private String subscriber;
	@Value("${subscriber.value}")
	public void setSubscriber(String subscriber) {
		this.subscriber = subscriber;
	}

	@Override
	public String addSubscriber(Subscribers subscribers) {
	    // Check if a document with the same IMSI already exists
	    List<Subscribers> existingSubscribers = mongoDbRepository.findByImsi(subscribers.getImsi());
	    if (!existingSubscribers.isEmpty()) {
	        // If there are existing subscribers with the same IMSI, throw an exception or return an error message
	        throw new IllegalArgumentException("Subscriber with IMSI " + subscribers.getImsi() + " already exists.");
	    } else {
	        // No existing document with the same IMSI, proceed with insertion
	        mongoDbRepository.insert(subscribers);
	        return "New subscriber inserted with IMSI " + subscribers.getImsi() + ".";
	    }
	}


	@Override
	public void addSubscriberBulk(Subscribers subscribers, String quantity) {

//		Capturing IMSI
		String imsi = subscribers.getImsi();
		
//		Running a loop to create Subscribers up to Quantity value
		for (int i = 0; i < Integer.parseInt(quantity); i++) {
			ObjectId _id = new ObjectId();
			String id = _id.toString();
			subscribers.set_id(id);
			
			String last =  String.format("%015d",Long.parseLong(imsi) + Long.valueOf(i));
			subscribers.setImsi(last);
			
//String paddedImsi = String.format("%015d", Long.parseLong(imsi));
		    
			
			 // Check if the subscriber with the same IMSI already exists in the database
	        List<Subscribers> existingSubscribers = mongoDbRepository.findByImsi(subscribers.getImsi());
	        if (existingSubscribers.isEmpty()) {
	            // Attempt to insert the new subscriber
	            mongoDbRepository.insert(subscribers);
	        }
		}
	}
	// Function to dynamically add leading zeros to IMSI values
	private String addLeadingZeros(String originalImsi, long number) {
	    // Calculate the number of leading zeros needed based on the length of the original IMSI
	    int leadingZeros = 15 - originalImsi.length();
	    // Append leading zeros to the IMSI value
	    String paddedImsi = "0".repeat(Math.max(0, leadingZeros)) + Long.toString(number);
	    return paddedImsi;
	}

	@Override
	public List<Subscribers> viewSubscriber(String imsi) {
		return mongoDbRepository.findByImsi(imsi);
	}

	@Override
	 public List<Subscribers> viewAllSubscribers(int pageNumber, int pageSize) {
       Sort sort = Sort.by(Sort.Direction.ASC, "imsi");

       Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);

       Page<Subscribers> subscribersPage = mongoDbRepository.findAll(pageable);
       
      List<Subscribers> subscribers = subscribersPage.getContent();
       //int totalPages = subscribersPage.getTotalPages();
		return  subscribers;

       
   }
	@Override
	public int getTotalPages(int pageNumber, int pageSize) {
	    Sort sort = Sort.by(Sort.Direction.DESC, "id");
	    Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);

	    Page<Subscribers> subscribersPage = mongoDbRepository.findAll(pageable);
	    return subscribersPage.getTotalPages();
	}


	@Override
	public void deleteSubscriber(String _id) {
		mongoDbRepository.deleteById(_id);

	}

	@Override
	public void deleteSubscriberBulk(String imsi, String quantity) {
		
		for (int i = 0; i < Integer.parseInt(quantity); i++) {
			
			List<Subscribers> subscribers = new ArrayList<>();
			
			String last =  String.format("%015d",Long.parseLong(imsi) + Long.valueOf(i));
			subscribers = mongoDbRepository.findByImsi(last);
			for (Subscribers subscribers2 : subscribers) {
				mongoDbRepository.deleteById(subscribers2.get_id());
			}
		}
	}

	@Override
	public void deleteAllSubscriber() {
		mongoDbRepository.deleteAll();
	}

	@Override
	public void updateSubscriber(String id, Subscribers subscribers) {
	Optional<Subscribers> list =	mongoDbRepository.findById(id);
	if(list.isPresent())
	{
		Subscribers subscribers2 = list.get();
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
		mongoDbRepository.save(subscribers2);
	}
	}

	@Override
	public List<Subscribers> devicetagFilter(String devicetagfilter) {
		// TODO Auto-generated method stub
		List<Subscribers> list =	mongoDbRepository.findAll();
		List<Subscribers> dtos = new ArrayList<>();
		for (Subscribers subscribers : list) {
			if(subscribers.getDeviceType().equals(devicetagfilter))
			{
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
        return mongoDbRepository.findByImsiRegex(partialValue);
    }
	
	
//	public List<String> FindDistinctImsi(String imsi);
//	@Override
//	public void searchSubscriber(String imsi) {
//		// TODO Auto-generated method stub
//		mongoDbRepository.filterByImsi(imsi);
//		
//	}

}
