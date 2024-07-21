package com.other.app.NiralosFiveGCore.Repository.Subscriber;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.other.app.NiralosFiveGCore.model.Subscriber.Subscribers;

public interface MongoDbRepository extends MongoRepository<Subscribers, String> {
	
	List<Subscribers> findByImsi(String imsi);
	@Query("{'imsi': { $regex: ?0, $options: 'i' }}")
    List<Subscribers> findByImsiRegex(String partialValue);

	
	
	
}
