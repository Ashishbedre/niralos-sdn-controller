package com.other.app.NiralosFiveGCore.Repository.DataPlaneUpg.Backend;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.other.app.NiralosFiveGCore.model.DataPlaneUpg.UpgErrorModel;


public interface UpgErrorRepository extends JpaRepository<UpgErrorModel, Long>{

	@Query("SELECT COUNT(u) FROM UpgErrorModel u")
	public Integer countErrorData();
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "UPDATE upg_error_data m SET m.nat44_ed_out2in_slowpath_no_translation=?1,"
			+ "m.dpdk_input_no_error=?2,m.ip4_input_Multicast_RPF_check_failed=?3,m.ip4_local_ip4_source_lookup_miss=?4,"
			+ "m.ip4_icmp_input_unknown_type=?5,m.igmp_input_IGMP_not_enabled_on_this_interface=?6,m.arp_reply_ARP_replies_sent=?7,"
			+ "m.arp_input_IP4_destination_address_is_unset=?8,m.upf_ip4_flow_process_packets_with_an_existing_flow=?9,"
			+ "m.upf_ip4_flow_process_packets_gone_through=?10,m.upf_ip4_flow_process_packets_which_created_a_new_flow=?11,"
			+ "m.upf_gtpu4_input_good_packets_decapsulated=?12,m.upf4_encap_good_packets_encapsulated=?13,m.session_queue_no_buffer=?14,"
			+ "m.udp4_input_enqueued=?15,m.local0_output_interface_is_down=?16,m.local0_output_interface_is_deleted=?17,"
			+ "m.local0_output_no_tx_queue_available=?18,m.N3_GNB_output_interface_is_down=?19,m.N3_GNB_output_interface_is_deleted=?20,"
			+ "m.N3_GNB_output_no_buffers_to_segment_GSO=?21,m.N3_GNB_tx_DPDK_tx_function_returned_an_error=?22,"
			+ "m.N3_GNB_tx_Tx_packet_drops__dpdk_tx_failure=?23,m.N4_SMF_output_interface_is_down=?24,m.N4_SMF_output_interface_is_deleted=?25,"
			+ "m.N4_SMF_output_no_buffers_to_segment_GSO=?26,m.N4_SMF_tx_DPDK_tx_function_returned_an_error=?27,"
			+ "m.N4_SMF_tx_Tx_packet_drops__dpdk_tx_failure=?28,m.N6_DN_output_interface_is_down=?29,m.N6_DN_output_interface_is_deleted=?30,"
			+ "m.N6_DN_output_no_buffers_to_segment_GSO=?31,m.N6_DN_tx_DPDK_tx_function_returned_an_error=?32,"
			+ "m.N6_DN_tx_Tx_packet_drops__dpdk_tx_failure=?33,m.MEC_output_interface_is_down=?34,m.MEC_output_interface_is_deleted=?35,"
			+ "m.MEC_output_no_buffers_to_segment_GSO=?36,m.MEC_tx_DPDK_tx_function_returned_an_error=?37,"
			+ "m.MEC_tx_Tx_packet_drops__dpdk_tx_failure=?38, m.thread=?39 WHERE m.nf_name=?40 AND m.nf_type=?41", nativeQuery = true)
	public void updateError(Integer nat44_ed_out2in_slowpath_no_translation,Integer dpdk_input_no_error,
			Integer ip4_input_Multicast_RPF_check_failed,Integer ip4_local_ip4_source_lookup_miss,Integer ip4_icmp_input_unknown_type,
			Integer igmp_input_IGMP_not_enabled_on_this_interface,Integer arp_reply_ARP_replies_sent,
			Integer arp_input_IP4_destination_address_is_unset,Integer upf_ip4_flow_process_packets_with_an_existing_flow,
			Integer upf_ip4_flow_process_packets_gone_through,Integer upf_ip4_flow_process_packets_which_created_a_new_flow,
			Integer upf_gtpu4_input_good_packets_decapsulated,Integer upf4_encap_good_packets_encapsulated,
			Integer session_queue_no_buffer,Integer udp4_input_enqueued,Integer local0_output_interface_is_down,
			Integer local0_output_interface_is_deleted,Integer local0_output_no_tx_queue_available,
			Integer N3_GNB_output_interface_is_down,Integer N3_GNB_output_interface_is_deleted,
			Integer N3_GNB_output_no_buffers_to_segment_GSO,Integer N3_GNB_tx_DPDK_tx_function_returned_an_error,
			Integer N3_GNB_tx_Tx_packet_drops__dpdk_tx_failure,Integer N4_SMF_output_interface_is_down,
			Integer N4_SMF_output_interface_is_deleted,Integer N4_SMF_output_no_buffers_to_segment_GSO,
			Integer N4_SMF_tx_DPDK_tx_function_returned_an_error,Integer N4_SMF_tx_Tx_packet_drops__dpdk_tx_failure,
			Integer N6_DN_output_interface_is_down,Integer N6_DN_output_interface_is_deleted,Integer N6_DN_output_no_buffers_to_segment_GSO,
			Integer N6_DN_tx_DPDK_tx_function_returned_an_error,Integer N6_DN_tx_Tx_packet_drops__dpdk_tx_failure,
			Integer MEC_output_interface_is_down,Integer MEC_output_interface_is_deleted,Integer MEC_output_no_buffers_to_segment_GSO,
			Integer MEC_tx_DPDK_tx_function_returned_an_error,Integer MEC_tx_Tx_packet_drops__dpdk_tx_failure,Long thread
			,String nfName,
			String nfType);
	
	public List<UpgErrorModel> findBy();
	@Query(value="SELECT * FROM upg_error_data",nativeQuery=true)
	public List<UpgErrorModel> tenentSiteData();

	@Query(value="SELECT * FROM upg_error_data",nativeQuery = true)
	public List<UpgErrorModel> findAllData();
	
//	@Query("SELECT DISTINCT(u.siteId) FROM UpgErrorModel u WHERE u.tenentId =?1")
//	public List<String> returnSiteList(String tenentId);
	
	@Query("SELECT COUNT(DISTINCT l) FROM UpgErrorModel l ")
	public Integer countofSite();
	
	@Query(value = "SELECT tb.id, tb.thread - ts.thread AS thread, "
	        + "     0 - ts.nat44_ed_out2in_slowpath_no_translation AS nat44_ed_out2in_slowpath_no_translation,\r\n"
	        + "     0 - ts.dpdk_input_no_error AS dpdk_input_no_error,\r\n"
	        + "     0 - ts.ip4_input_multicast_rpf_check_failed AS ip4_input_multicast_rpf_check_failed,\r\n"
	        + "     0 - ts.ip4_local_ip4_source_lookup_miss AS ip4_local_ip4_source_lookup_miss,\r\n"
	        + "     0 - ts.ip4_icmp_input_unknown_type AS ip4_icmp_input_unknown_type,\r\n"
	        + "     0 - ts.igmp_input_igmp_not_enabled_on_this_interface AS igmp_input_igmp_not_enabled_on_this_interface,\r\n"
	        + "     0 - ts.arp_reply_arp_replies_sent AS arp_reply_arp_replies_sent,\r\n"
	        + "     0 - ts.arp_input_ip4_destination_address_is_unset AS arp_input_ip4_destination_address_is_unset,\r\n"
	        + "     0 - ts.upf_ip4_flow_process_packets_with_an_existing_flow AS upf_ip4_flow_process_packets_with_an_existing_flow,\r\n"
	        + "     0 - ts.upf_ip4_flow_process_packets_gone_through AS upf_ip4_flow_process_packets_gone_through,\r\n"
	        + "     0 - ts.upf_ip4_flow_process_packets_which_created_a_new_flow AS upf_ip4_flow_process_packets_which_created_a_new_flow,\r\n"
	        + "     0 - ts.upf_gtpu4_input_good_packets_decapsulated AS upf_gtpu4_input_good_packets_decapsulated,\r\n"
	        + "     0 - ts.upf4_encap_good_packets_encapsulated AS upf4_encap_good_packets_encapsulated,\r\n"
	        + "     0 - ts.session_queue_no_buffer AS session_queue_no_buffer,\r\n"
	        + "     0 - ts.udp4_input_enqueued AS udp4_input_enqueued,\r\n"
	        + "     0 - ts.local0_output_interface_is_down AS local0_output_interface_is_down,\r\n"
	        + "     0 - ts.local0_output_interface_is_deleted AS local0_output_interface_is_deleted,\r\n"
	        + "     0 - ts.local0_output_no_tx_queue_available AS local0_output_no_tx_queue_available,\r\n"
	        + "     0 - ts.n3_gnb_output_interface_is_down AS n3_gnb_output_interface_is_down,\r\n"
	        + "     0 - ts.n3_gnb_output_interface_is_deleted AS n3_gnb_output_interface_is_deleted,\r\n"
	        + "     0 - ts.n3_gnb_output_no_buffers_to_segment_gso AS n3_gnb_output_no_buffers_to_segment_gso,\r\n"
	        + "     0 - ts.n3_gnb_tx_dpdk_tx_function_returned_an_error AS n3_gnb_tx_dpdk_tx_function_returned_an_error,\r\n"
	        + "     0 - ts.n3_gnb_tx_tx_packet_drops__dpdk_tx_failure AS n3_gnb_tx_tx_packet_drops__dpdk_tx_failure,\r\n"
	        + "     0 - ts.n4_smf_output_interface_is_down AS n4_smf_output_interface_is_down,\r\n"
	        + "     0 - ts.n4_smf_output_interface_is_deleted AS n4_smf_output_interface_is_deleted,\r\n"
	        + "     0 - ts.n4_smf_output_no_buffers_to_segment_gso AS n4_smf_output_no_buffers_to_segment_gso,\r\n"
	        + "     0 - ts.n4_smf_tx_dpdk_tx_function_returned_an_error AS n4_smf_tx_dpdk_tx_function_returned_an_error,\r\n"
	        + "     0 - ts.n4_smf_tx_tx_packet_drops__dpdk_tx_failure AS n4_smf_tx_tx_packet_drops__dpdk_tx_failure,\r\n"
	        + "     0 - ts.n6_dn_output_interface_is_down AS n6_dn_output_interface_is_down,\r\n"
	        + "     0 - ts.n6_dn_output_interface_is_deleted AS n6_dn_output_interface_is_deleted,\r\n"
	        + "     0 - ts.n6_dn_output_no_buffers_to_segment_gso AS n6_dn_output_no_buffers_to_segment_gso,\r\n"
	        + "     0 - ts.n6_dn_tx_dpdk_tx_function_returned_an_error AS n6_dn_tx_dpdk_tx_function_returned_an_error,\r\n"
	        + "     0 - ts.n6_dn_tx_tx_packet_drops__dpdk_tx_failure AS n6_dn_tx_tx_packet_drops__dpdk_tx_failure,\r\n"
	        + "     0 - ts.mec_output_interface_is_down AS mec_output_interface_is_down,\r\n"
	        + "     0 - ts.mec_output_interface_is_deleted AS mec_output_interface_is_deleted,\r\n"
	        + "     0 - ts.mec_output_no_buffers_to_segment_gso AS mec_output_no_buffers_to_segment_gso,\r\n"
	        + "     0 - ts.mec_tx_dpdk_tx_function_returned_an_error AS mec_tx_dpdk_tx_function_returned_an_error,\r\n"
	        + "     0 - ts.mec_tx_tx_packet_drops__dpdk_tx_failure AS mec_tx_tx_packet_drops__dpdk_tx_failure,\r\n"
	        + "     ts.agent_id, ts.nf_name, ts.nf_type\r\n"
	        + "     FROM upg_error_data ts\r\n"
	        + "     JOIN upg_errordelta_data tb ON ts.id = tb.id\r\n"
	        + "     WHERE ts.nf_name = ?1 AND ts.nf_type = ?2", nativeQuery = true)
	public List<UpgErrorModel> subtractFromZero(String nfName, String nfType);

}
