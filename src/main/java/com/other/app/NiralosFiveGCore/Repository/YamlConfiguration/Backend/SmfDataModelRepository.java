package com.other.app.NiralosFiveGCore.Repository.YamlConfiguration.Backend;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.other.app.NiralosFiveGCore.model.YamlFileConfiguration.SmfDataMongoModel;

public interface SmfDataModelRepository extends MongoRepository<SmfDataMongoModel, Long>{

	List<SmfDataMongoModel> findByTenantIdAndSiteIdAndNfName(String tenentName, String siteName, String string);

}
