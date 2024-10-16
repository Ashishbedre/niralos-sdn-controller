package com.other.app.NiralosFiveGCore.Repository.YamlConfiguration.Frontend;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.other.app.NiralosFiveGCore.model.YamlFileConfiguration.NrfDataMongoModel;


public interface NrfFrontendDataModelRepository extends MongoRepository<NrfDataMongoModel, String>{

	List<NrfDataMongoModel> findByNfName( String string);

	List<NrfDataMongoModel> findByNfNameRegex(String string);

}
