package com.other.app.NiralosFiveGCore.Repository.YamlConfiguration.Backend;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.other.app.NiralosFiveGCore.model.YamlFileConfiguration.NssfDataMongoModel;

public interface NssfDataModelRepository extends MongoRepository<NssfDataMongoModel, String>{

	List<NssfDataMongoModel> findByNfName( String string);

}
