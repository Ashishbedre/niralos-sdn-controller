package com.other.app.NiralosFiveGCore.Repository.PfcpInfo.Backend;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.other.app.NiralosFiveGCore.model.PFCPINFO.PfcpSessioEstablishmentModificationDelationFailReasonList;

public interface PfcpSessioEstablishmentModificationDelationFailReasonListRepository extends JpaRepository<PfcpSessioEstablishmentModificationDelationFailReasonList, Long> {

	//PfcpSessioEstablishmentModificationDelationFailReasonList findByTenentIdAndSiteId(String tenentId, String siteId);

//	 List<PfcpSessioEstablishmentModificationDelationFailReasonList> findByTenentIdAndSiteIdAndSessionEstablishmentFailReasonList(
//	            String tenentId, String siteId, String sessionEstablishmentFailReasonList);
//	 List<PfcpSessioEstablishmentModificationDelationFailReasonList> findByTenentIdAndSiteIdAndSessionModificationFailreasonList(
//	            String tenentId, String siteId, String sessionModificationFailreasonList);
//	 List<PfcpSessioEstablishmentModificationDelationFailReasonList> findByTenentIdAndSiteIdAndSessionDeletionFailreasonList(
//	            String tenentId, String siteId, String sessionDeletionFailreasonList);

	List<PfcpSessioEstablishmentModificationDelationFailReasonList> findBySessionEstablishmentFailReasonListAndNfNameAndNfType(
			 String response, String networkFunctionName, String nfTypeofSmf);

	List<PfcpSessioEstablishmentModificationDelationFailReasonList> findBySessionModificationFailreasonListAndNfNameAndNfType(
			 String response1, String networkFunctionName, String nfTypeofSmf);

	List<PfcpSessioEstablishmentModificationDelationFailReasonList> findBySessionDeletionFailreasonListAndNfNameAndNfType(
			String response2, String networkFunctionName, String nfTypeofSmf);
//	List<PfcpSessioEstablishmentModificationDelationFailReasonList> findByTenentIdAndSiteId(String tenentId, String siteId);

	}