package com.other.app.NiralosFiveGCore.Repository.DataPlaneUpg.Frontend;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.other.app.NiralosFiveGCore.model.DataPlaneUpg.UpfErrorDeltaModel;

public interface UpfErrorDeltaModelRepository extends JpaRepository<UpfErrorDeltaModel, Long> {

	
	@Query("SELECT COUNT(*) FROM UpfErrorDeltaModel")
	Integer countUpfErrorDeltaData();


	 @Query(value = "SELECT tb.id, "
		        + "     tb.tun_write_failure_upf - ts.tun_write_failure_upf AS tun_write_failure_upf, "
		        + "     tb.invalid_eth_type_upf - ts.invalid_eth_type_upf AS invalid_eth_type_upf, "
		        + "     tb.invalid_gtpu_version_upf - ts.invalid_gtpu_version_upf AS invalid_gtpu_version_upf, "
		        + "     tb.small_gtpu_packet_upf - ts.small_gtpu_packet_upf AS small_gtpu_packet_upf, "
		        + "     tb.no_outer_header_creation_upf - ts.no_outer_header_creation_upf AS no_outer_header_creation_upf, "
		        + "     tb.tun_open_failure_upf - ts.tun_open_failure_upf AS tun_open_failure_upf, "
		        + "     tb.far_not_found_upf - ts.far_not_found_upf AS far_not_found_upf, "
		        + "     tb.gtpu_packet_decode_failure_upf - ts.gtpu_packet_decode_failure_upf AS gtpu_packet_decode_failure_upf, "
		        + "     ts.tenent_id, ts.site_id, ts.agent_id, ts.nf_name, ts.nf_type "
		        + "FROM upf_errordelta_model ts "
		        + "JOIN upf_error_data tb ON ts.id = tb.id "
		        + "WHERE ts.tenent_id = ?1 AND ts.site_id = ?2 AND ts.nf_name = ?3 AND ts.nf_type = ?4", nativeQuery = true)
		    List<UpfErrorDeltaModel> subtractData(String tenentId, String siteId, String nfName, String nfType);
	@Query(value="SELECT COUNT(*) FROM upf_errordelta_model u WHERE u.tenent_id = ?1 AND u.site_id = ?2 AND u.agent_id = ?3",nativeQuery=true)
	public Integer countDatainDelta(String tenentId, String siteId,String agentId);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE upf_errordelta_model m SET " +
	    "m.tun_write_failure_upf = ?1, " +
	    "m.invalid_eth_type_upf = ?2, " +
	    "m.invalid_gtpu_version_upf = ?3, " +
	    "m.small_gtpu_packet_upf = ?4, " +
	    "m.no_outer_header_creation_upf = ?5, " +
	    "m.tun_open_failure_upf = ?6, " +
	    "m.far_not_found_upf = ?7, " +
	    "m.gtpu_packet_decode_failure_upf = ?8 " +
	    "WHERE m.tenent_id = ?10 AND m.site_id = ?9 AND m.agent_id = ?11", nativeQuery = true)
	void updateUpfError(Integer tun_write_failure_upf, Integer invalid_eth_type_upf, Integer invalid_gtpu_version_upf,
	                    Integer small_gtpu_packet_upf, Integer no_outer_header_creation_upf, Integer tun_open_failure_upf,
	                    Integer far_not_found_upf, Integer gtpu_packet_decode_failure_upf,
	                    String tenentId, String siteId, String agentId, String nfName, String nfType);


	@Query("SELECT COUNT(DISTINCT l.siteId) FROM UpfErrorDeltaModel l WHERE tenentId =?1")
	public Integer countofSite(String tenantName);




}
