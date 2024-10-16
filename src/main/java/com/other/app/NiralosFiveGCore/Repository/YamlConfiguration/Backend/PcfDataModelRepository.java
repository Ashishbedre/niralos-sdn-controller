package com.other.app.NiralosFiveGCore.Repository.YamlConfiguration.Backend;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.other.app.NiralosFiveGCore.model.YamlFileConfiguration.PcfDataMongoModel;

public interface PcfDataModelRepository extends MongoRepository<PcfDataMongoModel, String>{


	public List<PcfDataMongoModel> findByNfName( String string);

}
