package com.other.app.NiralosFiveGCore.Repository.GnbHistory.Backend;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.other.app.NiralosFiveGCore.model.GnbHistory.GnbFailureList;

public interface GnbFailureListRepository extends JpaRepository<GnbFailureList, Long> {
    GnbFailureList findByGnbIdAndFailureReason(String gnbId,  String failureReason);


	List<GnbFailureList> findByGnbId(String gnbId);
}
