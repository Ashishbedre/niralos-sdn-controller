package com.other.app.NiralosFiveGCore.Repository.YamlConfiguration.Frontend;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.other.app.NiralosFiveGCore.model.YamlFileConfiguration.BsfDataMongoModel;


public interface BsfFrontendDataModelRepository extends MongoRepository<BsfDataMongoModel, String>{

	List<BsfDataMongoModel> findByNfName(String string);

	List<BsfDataMongoModel> findByNfNameRegex(String string);

}
