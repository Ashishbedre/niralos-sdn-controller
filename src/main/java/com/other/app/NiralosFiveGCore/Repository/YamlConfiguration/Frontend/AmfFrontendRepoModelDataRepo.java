package com.other.app.NiralosFiveGCore.Repository.YamlConfiguration.Frontend;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.other.app.NiralosFiveGCore.model.YamlFileConfiguration.AmfYmlModelForMogoDb;
public interface AmfFrontendRepoModelDataRepo extends MongoRepository<AmfYmlModelForMogoDb, String>{

	List<AmfYmlModelForMogoDb> findByNfNameRegex(String string);

	List<AmfYmlModelForMogoDb> findByNfName(String nfName);

}
