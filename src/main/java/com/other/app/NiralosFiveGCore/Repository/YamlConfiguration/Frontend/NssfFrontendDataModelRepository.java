package com.other.app.NiralosFiveGCore.Repository.YamlConfiguration.Frontend;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.other.app.NiralosFiveGCore.model.YamlFileConfiguration.NssfDataMongoModel;

public interface NssfFrontendDataModelRepository extends MongoRepository<NssfDataMongoModel, String>{

	List<NssfDataMongoModel> findByNfName( String string);

	List<NssfDataMongoModel> findByNfNameRegex(String string);

}
