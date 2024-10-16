package com.other.app.NiralosFiveGCore.Repository.YamlConfiguration.Frontend;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.other.app.NiralosFiveGCore.model.YamlFileConfiguration.UdrDataMongoModel;


public interface UdrFrontendDataModelRepository extends MongoRepository<UdrDataMongoModel, String>{

	List<UdrDataMongoModel> findByNfName( String string);

	List<UdrDataMongoModel> findByNfNameRegex( String string);

}
