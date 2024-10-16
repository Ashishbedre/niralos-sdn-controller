package com.other.app.NiralosFiveGCore.Repository.YamlConfiguration.Backend;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.other.app.NiralosFiveGCore.model.YamlFileConfiguration.UdrDataMongoModel;

public interface UdrDataModelRepository extends MongoRepository<UdrDataMongoModel, String>{

	List<UdrDataMongoModel> findByNfName( String string);

}
