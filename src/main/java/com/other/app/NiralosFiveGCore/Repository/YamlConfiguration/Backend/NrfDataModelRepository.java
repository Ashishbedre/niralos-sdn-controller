package com.other.app.NiralosFiveGCore.Repository.YamlConfiguration.Backend;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.other.app.NiralosFiveGCore.model.YamlFileConfiguration.NrfDataMongoModel;

public interface NrfDataModelRepository extends MongoRepository<NrfDataMongoModel, Long>{

	List<NrfDataMongoModel> findByTenantIdAndSiteIdAndNfName(String tenentName, String siteName, String string);

}
