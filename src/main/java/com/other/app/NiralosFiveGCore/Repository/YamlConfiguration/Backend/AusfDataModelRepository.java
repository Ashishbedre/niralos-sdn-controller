package com.other.app.NiralosFiveGCore.Repository.YamlConfiguration.Backend;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.other.app.NiralosFiveGCore.model.YamlFileConfiguration.AusfDataMongoModel;

public interface AusfDataModelRepository extends MongoRepository<AusfDataMongoModel, String>{


	List<AusfDataMongoModel> findByNfName( String string);

}
