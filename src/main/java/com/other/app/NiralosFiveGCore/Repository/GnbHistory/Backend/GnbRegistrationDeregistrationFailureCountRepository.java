package com.other.app.NiralosFiveGCore.Repository.GnbHistory.Backend;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.other.app.NiralosFiveGCore.model.GnbHistory.GnbRegistrationDeregistrationFailureCount;

public interface GnbRegistrationDeregistrationFailureCountRepository extends JpaRepository<GnbRegistrationDeregistrationFailureCount, Long> {
	public GnbRegistrationDeregistrationFailureCount findBySiteId(String siteId);


	public List<GnbRegistrationDeregistrationFailureCount> findByTenentIdAndSiteId(String tenantId, String siteId);

}
