package com.other.app.NiralosFiveGCore.Repository.UeHistory.Backend;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.other.app.NiralosFiveGCore.model.UeHisotry.UeHistoryModel;

@Repository
public interface UeHistoryRepository extends MongoRepository<UeHistoryModel, ObjectId> {
    
	int countByImsiAndGnbIdAndUeStatusAndPduStatusAndTime(
	        String imsi, String gnbId, String ueStatus, String pduStatus, String time
	);

//	public List<UeHistoryModel> findByTenentIdAndSiteId(String tenentId, String siteId);
    List<UeHistoryModel> findByImsiOrderByTimeDesc(String imsi, PageRequest pageable);

}
