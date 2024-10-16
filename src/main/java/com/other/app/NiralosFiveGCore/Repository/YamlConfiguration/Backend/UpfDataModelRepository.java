package com.other.app.NiralosFiveGCore.Repository.YamlConfiguration.Backend;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.other.app.NiralosFiveGCore.model.YamlFileConfiguration.UpfDataMongoModel;

public interface UpfDataModelRepository extends MongoRepository<UpfDataMongoModel, String>{

	List<UpfDataMongoModel> findByNfName( String string);

	
}
