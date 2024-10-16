package com.other.app.NiralosFiveGCore.Repository.DataPlaneUpg.Frontend;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.other.app.NiralosFiveGCore.model.DataPlaneUpg.UpgErrorDeltaModel;

public interface UpgErrorDeltaModelRepository extends JpaRepository<UpgErrorDeltaModel, Long>{
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "UPDATE upg_errordelta_data m SET m.nat44_ed_out2in_slowpath_no_translation=?1,"
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
			+ "m.MEC_tx_Tx_packet_drops__dpdk_tx_failure=?38,m.thread=?42 WHERE m.id = '1'", nativeQuery = true)
	public void updateError(Integer nat44_ed_out2in_slowpath_no_translation, int dpdk_input_no_error,
			int ip4_input_Multicast_RPF_check_failed, int ip4_local_ip4_source_lookup_miss,
			int ip4_icmp_input_unknown_type, int igmp_input_IGMP_not_enabled_on_this_interface,
			int arp_reply_ARP_replies_sent, int arp_input_IP4_destination_address_is_unset,
			int upf_ip4_flow_process_packets_with_an_existing_flow, int upf_ip4_flow_process_packets_gone_through,
			int upf_ip4_flow_process_packets_which_created_a_new_flow, int upf_gtpu4_input_good_packets_decapsulated,
			int upf4_encap_good_packets_encapsulated, int session_queue_Packets_transmitted,
			int udp4_input_Packets_enqueued, int local0_output_interface_is_down,
			int local0_output_interface_is_deleted, int local0_output_no_buffers_to_segment_GSO,
			int n3_GNB_output_interface_is_down, int n3_GNB_output_interface_is_deleted,
			int n3_GNB_output_no_buffers_to_segment_GSO, int n3_GNB_tx_DPDK_tx_function_returned_an_error,
			int n3_GNB_tx_Tx_packet_drops__dpdk_tx_failure, int n4_SMF_output_interface_is_down,
			int n4_SMF_output_interface_is_deleted, int n4_SMF_output_no_buffers_to_segment_GSO,
			int n4_SMF_tx_DPDK_tx_function_returned_an_error, int n4_SMF_tx_Tx_packet_drops__dpdk_tx_failure,
			int n6_DN_output_interface_is_down, int n6_DN_output_interface_is_deleted,
			int n6_DN_output_no_buffers_to_segment_GSO, int n6_DN_tx_DPDK_tx_function_returned_an_error,
			int n6_DN_tx_Tx_packet_drops__dpdk_tx_failure, int mEC_output_interface_is_down,
			int mEC_output_interface_is_deleted, int mEC_output_no_buffers_to_segment_GSO,
			int mEC_tx_DPDK_tx_function_returned_an_error, int mEC_tx_Tx_packet_drops__dpdk_tx_failure, 
			Long thread, String nfName, String nfType);

	@Query(value="SELECT COUNT(*) FROM upg_errordelta_data u ",nativeQuery=true)
	public Integer countDatainDelta();

	@Query("SELECT COUNT(l) FROM UpgErrorDeltaModel l")
	public Integer countUpgErrorDeltaData();
	
	@Query("SELECT COUNT(DISTINCT l) FROM UpgErrorDeltaModel l")
	public Integer countofSite();

	
	
	@Query(value = "SELECT tb.id, tb.thread - ts.thread AS thread, "
	        + "tb.nat44_ed_out2in_slowpath_no_translation - ts.nat44_ed_out2in_slowpath_no_translation AS nat44_ed_out2in_slowpath_no_translation, "
	        + "tb.dpdk_input_no_error - ts.dpdk_input_no_error AS dpdk_input_no_error, "
	        + "tb.ip4_input_multicast_rpf_check_failed - ts.ip4_input_multicast_rpf_check_failed AS ip4_input_multicast_rpf_check_failed, "
	        + "tb.ip4_local_ip4_source_lookup_miss - ts.ip4_local_ip4_source_lookup_miss AS ip4_local_ip4_source_lookup_miss, "
	        + "tb.ip4_icmp_input_unknown_type - ts.ip4_icmp_input_unknown_type AS ip4_icmp_input_unknown_type, "
	        + "tb.igmp_input_igmp_not_enabled_on_this_interface - ts.igmp_input_igmp_not_enabled_on_this_interface AS igmp_input_igmp_not_enabled_on_this_interface, "
	        + "tb.arp_reply_arp_replies_sent - ts.arp_reply_arp_replies_sent AS arp_reply_arp_replies_sent, "
	        + "tb.arp_input_ip4_destination_address_is_unset - ts.arp_input_ip4_destination_address_is_unset AS arp_input_ip4_destination_address_is_unset, "
	        + "tb.upf_ip4_flow_process_packets_with_an_existing_flow - ts.upf_ip4_flow_process_packets_with_an_existing_flow AS upf_ip4_flow_process_packets_with_an_existing_flow, "
	        + "tb.upf_ip4_flow_process_packets_gone_through - ts.upf_ip4_flow_process_packets_gone_through AS upf_ip4_flow_process_packets_gone_through, "
	        + "tb.upf_ip4_flow_process_packets_which_created_a_new_flow - ts.upf_ip4_flow_process_packets_which_created_a_new_flow AS upf_ip4_flow_process_packets_which_created_a_new_flow, "
	        + "tb.upf_gtpu4_input_good_packets_decapsulated - ts.upf_gtpu4_input_good_packets_decapsulated AS upf_gtpu4_input_good_packets_decapsulated, "
	        + "tb.upf4_encap_good_packets_encapsulated - ts.upf4_encap_good_packets_encapsulated AS upf4_encap_good_packets_encapsulated, "
	        + "tb.session_queue_no_buffer - ts.session_queue_no_buffer AS session_queue_no_buffer, "
	        + "tb.udp4_input_enqueued - ts.udp4_input_enqueued AS udp4_input_enqueued, "
	        + "tb.local0_output_interface_is_down - ts.local0_output_interface_is_down AS local0_output_interface_is_down, "
	        + "tb.local0_output_interface_is_deleted - ts.local0_output_interface_is_deleted AS local0_output_interface_is_deleted, "
	        + "tb.local0_output_no_tx_queue_available - ts.local0_output_no_tx_queue_available AS local0_output_no_tx_queue_available, "
	        + "tb.n3_gnb_output_interface_is_down - ts.n3_gnb_output_interface_is_down AS n3_gnb_output_interface_is_down, "
	        + "tb.n3_gnb_output_interface_is_deleted - ts.n3_gnb_output_interface_is_deleted AS n3_gnb_output_interface_is_deleted, "
	        + "tb.n3_gnb_output_no_buffers_to_segment_gso - ts.n3_gnb_output_no_buffers_to_segment_gso AS n3_gnb_output_no_buffers_to_segment_gso, "
	        + "tb.n3_gnb_tx_dpdk_tx_function_returned_an_error - ts.n3_gnb_tx_dpdk_tx_function_returned_an_error AS n3_gnb_tx_dpdk_tx_function_returned_an_error, "
	        + "tb.n3_gnb_tx_tx_packet_drops__dpdk_tx_failure - ts.n3_gnb_tx_tx_packet_drops__dpdk_tx_failure AS n3_gnb_tx_tx_packet_drops__dpdk_tx_failure, "
	        + "tb.n4_smf_output_interface_is_down - ts.n4_smf_output_interface_is_down AS n4_smf_output_interface_is_down, "
	        + "tb.n4_smf_output_interface_is_deleted - ts.n4_smf_output_interface_is_deleted AS n4_smf_output_interface_is_deleted, "
	        + "tb.n4_smf_output_no_buffers_to_segment_gso - ts.n4_smf_output_no_buffers_to_segment_gso AS n4_smf_output_no_buffers_to_segment_gso, "
	        + "tb.n4_smf_tx_dpdk_tx_function_returned_an_error - ts.n4_smf_tx_dpdk_tx_function_returned_an_error AS n4_smf_tx_dpdk_tx_function_returned_an_error, "
	        + "tb.n4_smf_tx_tx_packet_drops__dpdk_tx_failure - ts.n4_smf_tx_tx_packet_drops__dpdk_tx_failure AS n4_smf_tx_tx_packet_drops__dpdk_tx_failure, "
	        + "tb.n6_dn_output_interface_is_down - ts.n6_dn_output_interface_is_down AS n6_dn_output_interface_is_down, "
	        + "tb.n6_dn_output_interface_is_deleted - ts.n6_dn_output_interface_is_deleted AS n6_dn_output_interface_is_deleted, "
	        + "tb.n6_dn_output_no_buffers_to_segment_gso - ts.n6_dn_output_no_buffers_to_segment_gso AS n6_dn_output_no_buffers_to_segment_gso, "
	        + "tb.n6_dn_tx_dpdk_tx_function_returned_an_error - ts.n6_dn_tx_dpdk_tx_function_returned_an_error AS n6_dn_tx_dpdk_tx_function_returned_an_error, "
	        + "tb.n6_dn_tx_tx_packet_drops__dpdk_tx_failure - ts.n6_dn_tx_tx_packet_drops__dpdk_tx_failure AS n6_dn_tx_tx_packet_drops__dpdk_tx_failure, "
	        + "tb.mec_output_interface_is_down - ts.mec_output_interface_is_down AS mec_output_interface_is_down, "
	        + "tb.mec_output_interface_is_deleted - ts.mec_output_interface_is_deleted AS mec_output_interface_is_deleted, "
	        + "tb.mec_output_no_buffers_to_segment_gso - ts.mec_output_no_buffers_to_segment_gso AS mec_output_no_buffers_to_segment_gso, "
	        + "tb.mec_tx_dpdk_tx_function_returned_an_error - ts.mec_tx_dpdk_tx_function_returned_an_error AS mec_tx_dpdk_tx_function_returned_an_error, "
	        + "tb.mec_tx_tx_packet_drops__dpdk_tx_failure - ts.mec_tx_tx_packet_drops__dpdk_tx_failure AS mec_tx_tx_packet_drops__dpdk_tx_failure, "
	        + "ts.agent_id, ts.nf_name, ts.nf_type "
	        + "FROM upg_errordelta_data ts "
	        + "JOIN upg_error_data tb ON ts.id = tb.id "
	        + "WHERE ts.nf_name = ?1 AND ts.nf_type = ?2", nativeQuery = true)
	public List<UpgErrorDeltaModel> subtractDaata(String nfName, String nfType);

}
