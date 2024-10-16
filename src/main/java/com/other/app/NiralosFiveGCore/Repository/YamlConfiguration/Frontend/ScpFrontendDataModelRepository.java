package com.other.app.NiralosFiveGCore.Repository.YamlConfiguration.Frontend;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.other.app.NiralosFiveGCore.model.YamlFileConfiguration.ScpDataMongoModel;


public interface ScpFrontendDataModelRepository extends MongoRepository<ScpDataMongoModel, String>{

	public List<ScpDataMongoModel> findByNfName(String string);

	public List<ScpDataMongoModel> findByNfNameRegex(
			String string);

}
