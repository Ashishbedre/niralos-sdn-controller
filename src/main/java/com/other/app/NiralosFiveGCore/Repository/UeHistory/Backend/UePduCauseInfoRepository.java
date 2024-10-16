package com.other.app.NiralosFiveGCore.Repository.UeHistory.Backend;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.other.app.NiralosFiveGCore.model.UeHisotry.UePduCauseInfoModel;

public interface UePduCauseInfoRepository extends JpaRepository<UePduCauseInfoModel, Long> {

    List<UePduCauseInfoModel> findByuecauselist(String uecauselist);
    List<UePduCauseInfoModel> findBypducauselist(String pducauselist);
//	List<UePduCauseInfoModel> findByTenentIdAndSiteId(String tenentId, String siteId);


}
