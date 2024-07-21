package com.other.app.NiralosFiveGCore.Repository.LogManagement;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.other.app.NiralosFiveGCore.model.LogManager.LogModelMongo;

public interface LogModelMongoRepository extends MongoRepository<LogModelMongo, String> {
//    List<LogModelMongo> findTop70ByIdOrderByDesc(String id);

	

//	public void findByLogLevelAndLogLevelAndTenentIdAndSiteId(String level, String logLevel, String tenentId, String siteId);

//	public void findByLevelAndLogLevelAndTenentIdAndSiteId(String level, String logLevel, String tenentId,
//			String siteId);

	

	

	List<LogModelMongo> findByLogLevelAndModule(String level, String module);

}
