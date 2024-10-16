package com.other.app.NiralosFiveGCore.Repository.YamlConfiguration.Frontend;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.other.app.NiralosFiveGCore.model.YamlFileConfiguration.AusfDataMongoModel;


public interface AusfFrontendDataModelRepository extends MongoRepository<AusfDataMongoModel, String>{

	List<AusfDataMongoModel> findByNfName(String string);

	List<AusfDataMongoModel> findByNfNameRegex(String string);

}
