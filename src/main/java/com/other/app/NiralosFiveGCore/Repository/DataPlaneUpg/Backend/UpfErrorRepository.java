package com.other.app.NiralosFiveGCore.Repository.DataPlaneUpg.Backend;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.other.app.NiralosFiveGCore.model.DataPlaneUpg.UpfErrorModel;


public interface UpfErrorRepository extends JpaRepository<UpfErrorModel, Long>{

	@Query("SELECT COUNT(u) FROM UpfErrorModel u")
	public Integer countErrorData();

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "UPDATE upf_error_data SET tun_open_failure_upf=?1, invalid_eth_type_upf =?2, invalid_gtpu_version_upf=?3,"
			+ "small_gtpu_packet_upf =?4, no_outer_header_creation_upf =?5,tun_write_failure_upf=?6, far_not_found_upf=?7,"
			+ " gtpu_packet_decode_failure_upf= ?8  WHERE nf_name=?9 AND nf_type=?10", nativeQuery = true)
	public void updateError(Integer tun_open_failure_upf, Integer invalid_eth_type_upf,
			Integer invalid_gtpu_version_upf, Integer small_gtpu_packet_upf, Integer no_outer_header_creation_upf,
			Integer tun_write_failure_upf, Integer far_not_found_upf, Integer gtpu_packet_decode_failure_upf,String nfName,
			String nfType);
	
//	List<UpfErrorModel> findByNfTypeAndNfName( String nfType, String nfName);
	
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
           " ts.nf_name, ts.nf_type " +
            "FROM upf_error_data d " +
            "JOIN upf_errordelta_model ts ON d.id = ts.id " +
            "WHERE ts.nf_name = ?1 AND ts.nf_type = ?2", nativeQuery = true)
	    List<UpfErrorModel> subtractFromZero(String nfName, String nfType);
	
	@Query(value = "SELECT * FROM upf_error_data", nativeQuery = true)
	List<UpfErrorModel> tenentSiteData();
	
    public UpfErrorModel findByNfTypeAndNfName(String nfType, String nfName);
    
//	List<UpfErrorModel> findByTenentIdAndSiteId(String tenentId, String siteId);
	
	@Query("SELECT COUNT(DISTINCT l) FROM UpfErrorModel l")
	public Integer countofSite();
}
