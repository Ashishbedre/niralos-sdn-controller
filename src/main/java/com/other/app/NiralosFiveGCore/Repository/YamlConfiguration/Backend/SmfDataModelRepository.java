package com.other.app.NiralosFiveGCore.Repository.YamlConfiguration.Backend;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.other.app.NiralosFiveGCore.model.YamlFileConfiguration.SmfDataMongoModel;

public interface SmfDataModelRepository extends MongoRepository<SmfDataMongoModel, String>{

	List<SmfDataMongoModel> findByNfName(String string);

}
