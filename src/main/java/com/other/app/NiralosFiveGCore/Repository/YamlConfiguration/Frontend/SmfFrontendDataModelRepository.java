package com.other.app.NiralosFiveGCore.Repository.YamlConfiguration.Frontend;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.other.app.NiralosFiveGCore.model.YamlFileConfiguration.SmfDataMongoModel;

public interface SmfFrontendDataModelRepository extends MongoRepository<SmfDataMongoModel, String>{

	List<SmfDataMongoModel> findByNfName(String string);

	List<SmfDataMongoModel> findByNfNameRegex(String string);

}
