package com.other.app.NiralosFiveGCore.Repository.YamlConfiguration.Backend;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.other.app.NiralosFiveGCore.model.YamlFileConfiguration.UdmDataMongoModel;

public interface UdmDataModelRepository extends MongoRepository<UdmDataMongoModel, Long>{


	List<UdmDataMongoModel> findByTenantIdAndSiteIdAndNfName(String tenentName, String siteName, String string);

}
