package com.other.app.NiralosFiveGCore.Repository.YamlConfiguration.Frontend;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.other.app.NiralosFiveGCore.model.YamlFileConfiguration.UpfDataMongoModel;

public interface UpfFrontendDataModelRepository extends MongoRepository<UpfDataMongoModel, String>{

	List<UpfDataMongoModel> findByNfName(String string);

	List<UpfDataMongoModel> findByNfNameRegex(String string);

	
}
