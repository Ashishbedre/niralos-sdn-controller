package com.other.app.NiralosFiveGCore.Repository.YamlConfiguration.Frontend;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.other.app.NiralosFiveGCore.model.YamlFileConfiguration.PcfDataMongoModel;


public interface PcfFrontendDataModelRepository extends MongoRepository<PcfDataMongoModel, String>{

	
	public List<PcfDataMongoModel> findByNfName(String string);

	public List<PcfDataMongoModel> findByNfNameRegex(String string);

}
