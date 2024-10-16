package com.other.app.NiralosFiveGCore.Repository.YamlConfiguration.Backend;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.other.app.NiralosFiveGCore.model.YamlFileConfiguration.ScpDataMongoModel;

public interface ScpDataModelRepository extends MongoRepository<ScpDataMongoModel, String>{

	public List<ScpDataMongoModel> findByNfName( String string);

}
