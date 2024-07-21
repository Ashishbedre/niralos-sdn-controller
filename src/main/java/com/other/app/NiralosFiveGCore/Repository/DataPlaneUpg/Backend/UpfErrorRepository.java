package com.other.app.NiralosFiveGCore.Repository.DataPlaneUpg.Backend;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.other.app.NiralosFiveGCore.model.DataPlaneUpg.UpfErrorModel;


public interface UpfErrorRepository extends JpaRepository<UpfErrorModel, Long>{

	@Query("SELECT COUNT(u) FROM UpfErrorModel u WHERE u.agentId = ?1 AND u.tenentId = ?2 AND u.siteId = ?3")
	public Integer countErrorData(String agentId, String tenentId, String siteId);

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "UPDATE upf_error_data SET tun_open_failure_upf=?1, invalid_eth_type_upf =?2, invalid_gtpu_version_upf=?3,"
			+ "small_gtpu_packet_upf =?4, no_outer_header_creation_upf =?5,tun_write_failure_upf=?6, far_not_found_upf=?7,"
			+ " gtpu_packet_decode_failure_upf= ?8, agent_id=?9  WHERE tenent_id=?10 AND site_id=?11 AND nf_name=?12 AND nf_type=?13", nativeQuery = true)
	public void updateError(Integer tun_open_failure_upf, Integer invalid_eth_type_upf,
			Integer invalid_gtpu_version_upf, Integer small_gtpu_packet_upf, Integer no_outer_header_creation_upf,
			Integer tun_write_failure_upf, Integer far_not_found_upf, Integer gtpu_packet_decode_failure_upf,
			String agentId, String tenentId, String siteId,String nfName,
			String nfType);
	
	List<UpfErrorModel> findByTenentIdAndSiteIdAndNfTypeAndNfName(
	        String tenentId, String siteId, String nfType, String nfName);
	 @Query(value = "SELECT " +
	            "ts.id, " +
	            "0 - d.tun_write_failure_upf AS tun_write_failure_upf, " +
	            "0 - d.invalid_eth_type_upf AS invalid_eth_type_upf, " +
	            "0 - d.invalid_gtpu_version_upf AS invalid_gtpu_version_upf, " +
	            "0 - d.small_gtpu_packet_upf AS small_gtpu_packet_upf, " +
	            "0 - d.no_outer_header_creation_upf AS no_outer_header_creation_upf, " +
	            "0 - d.tun_open_failure_upf AS tun_open_failure_upf, " +
	            "0 - d.far_not_found_upf AS far_not_found_upf, " +
	            "0 - d.gtpu_packet_decode_failure_upf AS gtpu_packet_decode_failure_upf, " +
	            "ts.tenent_id, ts.site_id, ts.agent_id, ts.nf_name, ts.nf_type " +
	            "FROM upf_error_data d " +
	            "JOIN upf_errordelta_model ts ON d.id = ts.id " +
	            "WHERE ts.tenent_id = ?1 AND ts.site_id = ?2 AND ts.nf_name = ?3 AND ts.nf_type = ?4", nativeQuery = true)
	    List<UpfErrorModel> subtractFromZero(String tenentId, String siteId, String nfName, String nfType);
	
	@Query(value = "SELECT * FROM upf_error_data", nativeQuery = true)
	List<UpfErrorModel> tenentSiteData();
    public UpfErrorModel findByTenentIdAndSiteIdAndAgentIdAndNfTypeAndNfName(String tenentId, String siteId, String agentId, String nfType, String nfName);
	List<UpfErrorModel> findByTenentIdAndSiteId(String tenentId, String siteId);
	
	@Query("SELECT COUNT(DISTINCT l.siteId) FROM UpfErrorModel l WHERE tenentId =?1")
	public Integer countofSite(String tenantName);
}
