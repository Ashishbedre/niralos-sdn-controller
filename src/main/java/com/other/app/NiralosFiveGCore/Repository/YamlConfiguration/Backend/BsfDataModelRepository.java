package com.other.app.NiralosFiveGCore.Repository.YamlConfiguration.Backend;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.other.app.NiralosFiveGCore.model.YamlFileConfiguration.BsfDataMongoModel;

public interface BsfDataModelRepository extends MongoRepository<BsfDataMongoModel, Long>{

	List<BsfDataMongoModel> findByTenantIdAndSiteIdAndNfName(String tenentName, String siteName, String string);

}
