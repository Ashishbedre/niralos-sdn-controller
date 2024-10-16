package com.other.app.NiralosFiveGCore.Repository.YamlConfiguration.Frontend;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.other.app.NiralosFiveGCore.model.YamlFileConfiguration.UdmDataMongoModel;

public interface UdmFrontendDataModelRepository extends MongoRepository<UdmDataMongoModel, String >{

	List<UdmDataMongoModel> findByNfName(String string);

	List<UdmDataMongoModel> findByNfNameRegex(String string);

}
