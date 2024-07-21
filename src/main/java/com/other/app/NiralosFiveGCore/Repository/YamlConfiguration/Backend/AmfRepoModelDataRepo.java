
package com.other.app.NiralosFiveGCore.Repository.YamlConfiguration.Backend;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.other.app.NiralosFiveGCore.model.YamlFileConfiguration.AmfYmlModelForMogoDb;


public interface AmfRepoModelDataRepo extends MongoRepository<AmfYmlModelForMogoDb, Long>{

	List<AmfYmlModelForMogoDb> findByTenantIdAndSiteIdAndNfName(String tenentName, String siteName, String nfName);
	
	
	

}
