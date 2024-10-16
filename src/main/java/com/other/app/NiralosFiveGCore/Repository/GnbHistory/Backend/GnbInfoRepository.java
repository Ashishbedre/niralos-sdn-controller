package com.other.app.NiralosFiveGCore.Repository.GnbHistory.Backend;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.other.app.NiralosFiveGCore.model.GnbHistory.GnbInfoModel;

public interface GnbInfoRepository extends MongoRepository<GnbInfoModel, ObjectId> {
	int countByGnbIdAndGnbUpTimeAndGnbDownTime(String gnbId, String gnbUpTime, String gnbDownTime);


//	List<GnbInfoModel> findByGnbId(String gnbId, PageRequest of);

	List<GnbInfoModel> findByGnbId(String gnbId, PageRequest of);

    Page<GnbInfoModel> findByGnbIdOrderByIdDesc(String gnbId, Pageable pageable);


}
