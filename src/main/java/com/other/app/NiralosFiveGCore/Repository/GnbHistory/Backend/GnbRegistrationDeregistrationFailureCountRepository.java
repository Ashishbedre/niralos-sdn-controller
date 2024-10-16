package com.other.app.NiralosFiveGCore.Repository.GnbHistory.Backend;

import org.springframework.data.jpa.repository.JpaRepository;

import com.other.app.NiralosFiveGCore.model.GnbHistory.GnbRegistrationDeregistrationFailureCount;

public interface GnbRegistrationDeregistrationFailureCountRepository extends JpaRepository<GnbRegistrationDeregistrationFailureCount, Long> {
	public GnbRegistrationDeregistrationFailureCount findByNfNameAndNfType(String nfName, String nfType);


//	public List<GnbRegistrationDeregistrationFailureCount> findByTenentIdAndSiteId(String tenantId, String siteId);

}
