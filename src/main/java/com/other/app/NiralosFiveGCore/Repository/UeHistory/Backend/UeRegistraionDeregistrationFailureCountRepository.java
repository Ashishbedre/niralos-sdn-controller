package com.other.app.NiralosFiveGCore.Repository.UeHistory.Backend;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.other.app.NiralosFiveGCore.model.UeHisotry.UeRegistraionDeregistrationFailureCount;

public interface UeRegistraionDeregistrationFailureCountRepository extends JpaRepository<UeRegistraionDeregistrationFailureCount, Long> {
	public UeRegistraionDeregistrationFailureCount findByNfNameAndNfType(String nfName, String nfType);

//	public List<UeRegistraionDeregistrationFailureCount> findByTenentIdAndSiteId(String tenentId, String siteId);
}
