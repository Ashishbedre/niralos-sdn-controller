package com.other.app.NiralosFiveGCore.Dto.upgDto;

public class UpgUpfNodeDto {
	private Integer nat44_ed_out2in_slowpath_no_translation;
	private Integer dpdk_input_no_error;
	private Integer ip4_input_Multicast_RPF_check_failed;
	private Integer ip4_local_ip4_source_lookup_miss;
	private Integer ip4_icmp_input_unknown_type;
	private Integer igmp_input_IGMP_not_enabled_on_this_interface;
	private Integer arp_reply_ARP_replies_sent;
	private Integer arp_input_IP4_destination_address_is_unset;
	private Integer upf_ip4_flow_process_packets_with_an_existing_flow;
	private Integer upf_ip4_flow_process_packets_gone_through;
	private Integer upf_ip4_flow_process_packets_which_created_a_new_flow;
	private Integer upf_gtpu4_input_good_packets_decapsulated;
	private Integer upf4_encap_good_packets_encapsulated;
	private Integer session_queue_Packets_transmitted;
	private Integer udp4_input_Packets_enqueued;
	private String siteId;
	private String tenentId;
	
	public String getSiteId() {
		return siteId;
	}
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
	public String getTenentId() {
		return tenentId;
	}
	public void setTenentId(String tenentId) {
		this.tenentId = tenentId;
	}
	public UpgUpfNodeDto(Integer nat44_ed_out2in_slowpath_no_translation, Integer dpdk_input_no_error,
			Integer ip4_input_Multicast_RPF_check_failed, Integer ip4_local_ip4_source_lookup_miss,
			Integer ip4_icmp_input_unknown_type, Integer igmp_input_IGMP_not_enabled_on_this_interface,
			Integer arp_reply_ARP_replies_sent, Integer arp_input_IP4_destination_address_is_unset,
			Integer upf_ip4_flow_process_packets_with_an_existing_flow,
			Integer upf_ip4_flow_process_packets_gone_through,
			Integer upf_ip4_flow_process_packets_which_created_a_new_flow,
			Integer upf_gtpu4_input_good_packets_decapsulated, Integer upf4_encap_good_packets_encapsulated,
			Integer session_queue_Packets_transmitted, Integer udp4_input_Packets_enqueued) {
		super();
		this.nat44_ed_out2in_slowpath_no_translation = nat44_ed_out2in_slowpath_no_translation;
		this.dpdk_input_no_error = dpdk_input_no_error;
		this.ip4_input_Multicast_RPF_check_failed = ip4_input_Multicast_RPF_check_failed;
		this.ip4_local_ip4_source_lookup_miss = ip4_local_ip4_source_lookup_miss;
		this.ip4_icmp_input_unknown_type = ip4_icmp_input_unknown_type;
		this.igmp_input_IGMP_not_enabled_on_this_interface = igmp_input_IGMP_not_enabled_on_this_interface;
		this.arp_reply_ARP_replies_sent = arp_reply_ARP_replies_sent;
		this.arp_input_IP4_destination_address_is_unset = arp_input_IP4_destination_address_is_unset;
		this.upf_ip4_flow_process_packets_with_an_existing_flow = upf_ip4_flow_process_packets_with_an_existing_flow;
		this.upf_ip4_flow_process_packets_gone_through = upf_ip4_flow_process_packets_gone_through;
		this.upf_ip4_flow_process_packets_which_created_a_new_flow = upf_ip4_flow_process_packets_which_created_a_new_flow;
		this.upf_gtpu4_input_good_packets_decapsulated = upf_gtpu4_input_good_packets_decapsulated;
		this.upf4_encap_good_packets_encapsulated = upf4_encap_good_packets_encapsulated;
		this.session_queue_Packets_transmitted = session_queue_Packets_transmitted;
		this.udp4_input_Packets_enqueued = udp4_input_Packets_enqueued;
	}
	public Integer getNat44_ed_out2in_slowpath_no_translation() {
		return nat44_ed_out2in_slowpath_no_translation;
	}
	public void setNat44_ed_out2in_slowpath_no_translation(Integer nat44_ed_out2in_slowpath_no_translation) {
		this.nat44_ed_out2in_slowpath_no_translation = nat44_ed_out2in_slowpath_no_translation;
	}
	public Integer getDpdk_input_no_error() {
		return dpdk_input_no_error;
	}
	public void setDpdk_input_no_error(Integer dpdk_input_no_error) {
		this.dpdk_input_no_error = dpdk_input_no_error;
	}
	public Integer getIp4_input_Multicast_RPF_check_failed() {
		return ip4_input_Multicast_RPF_check_failed;
	}
	public void setIp4_input_Multicast_RPF_check_failed(Integer ip4_input_Multicast_RPF_check_failed) {
		this.ip4_input_Multicast_RPF_check_failed = ip4_input_Multicast_RPF_check_failed;
	}
	public Integer getIp4_local_ip4_source_lookup_miss() {
		return ip4_local_ip4_source_lookup_miss;
	}
	public void setIp4_local_ip4_source_lookup_miss(Integer ip4_local_ip4_source_lookup_miss) {
		this.ip4_local_ip4_source_lookup_miss = ip4_local_ip4_source_lookup_miss;
	}
	public Integer getIp4_icmp_input_unknown_type() {
		return ip4_icmp_input_unknown_type;
	}
	public void setIp4_icmp_input_unknown_type(Integer ip4_icmp_input_unknown_type) {
		this.ip4_icmp_input_unknown_type = ip4_icmp_input_unknown_type;
	}
	public Integer getIgmp_input_IGMP_not_enabled_on_this_interface() {
		return igmp_input_IGMP_not_enabled_on_this_interface;
	}
	public void setIgmp_input_IGMP_not_enabled_on_this_interface(Integer igmp_input_IGMP_not_enabled_on_this_interface) {
		this.igmp_input_IGMP_not_enabled_on_this_interface = igmp_input_IGMP_not_enabled_on_this_interface;
	}
	public Integer getArp_reply_ARP_replies_sent() {
		return arp_reply_ARP_replies_sent;
	}
	public void setArp_reply_ARP_replies_sent(Integer arp_reply_ARP_replies_sent) {
		this.arp_reply_ARP_replies_sent = arp_reply_ARP_replies_sent;
	}
	public Integer getArp_input_IP4_destination_address_is_unset() {
		return arp_input_IP4_destination_address_is_unset;
	}
	public void setArp_input_IP4_destination_address_is_unset(Integer arp_input_IP4_destination_address_is_unset) {
		this.arp_input_IP4_destination_address_is_unset = arp_input_IP4_destination_address_is_unset;
	}
	public Integer getUpf_ip4_flow_process_packets_with_an_existing_flow() {
		return upf_ip4_flow_process_packets_with_an_existing_flow;
	}
	public void setUpf_ip4_flow_process_packets_with_an_existing_flow(
			Integer upf_ip4_flow_process_packets_with_an_existing_flow) {
		this.upf_ip4_flow_process_packets_with_an_existing_flow = upf_ip4_flow_process_packets_with_an_existing_flow;
	}
	public Integer getUpf_ip4_flow_process_packets_gone_through() {
		return upf_ip4_flow_process_packets_gone_through;
	}
	public void setUpf_ip4_flow_process_packets_gone_through(Integer upf_ip4_flow_process_packets_gone_through) {
		this.upf_ip4_flow_process_packets_gone_through = upf_ip4_flow_process_packets_gone_through;
	}
	public Integer getUpf_ip4_flow_process_packets_which_created_a_new_flow() {
		return upf_ip4_flow_process_packets_which_created_a_new_flow;
	}
	public void setUpf_ip4_flow_process_packets_which_created_a_new_flow(
			Integer upf_ip4_flow_process_packets_which_created_a_new_flow) {
		this.upf_ip4_flow_process_packets_which_created_a_new_flow = upf_ip4_flow_process_packets_which_created_a_new_flow;
	}
	public Integer getUpf_gtpu4_input_good_packets_decapsulated() {
		return upf_gtpu4_input_good_packets_decapsulated;
	}
	public void setUpf_gtpu4_input_good_packets_decapsulated(Integer upf_gtpu4_input_good_packets_decapsulated) {
		this.upf_gtpu4_input_good_packets_decapsulated = upf_gtpu4_input_good_packets_decapsulated;
	}
	public Integer getUpf4_encap_good_packets_encapsulated() {
		return upf4_encap_good_packets_encapsulated;
	}
	public void setUpf4_encap_good_packets_encapsulated(Integer upf4_encap_good_packets_encapsulated) {
		this.upf4_encap_good_packets_encapsulated = upf4_encap_good_packets_encapsulated;
	}
	public Integer getSession_queue_Packets_transmitted() {
		return session_queue_Packets_transmitted;
	}
	public void setSession_queue_Packets_transmitted(Integer session_queue_Packets_transmitted) {
		this.session_queue_Packets_transmitted = session_queue_Packets_transmitted;
	}
	public Integer getUdp4_input_Packets_enqueued() {
		return udp4_input_Packets_enqueued;
	}
	public void setUdp4_input_Packets_enqueued(Integer udp4_input_Packets_enqueued) {
		this.udp4_input_Packets_enqueued = udp4_input_Packets_enqueued;
	}
	public UpgUpfNodeDto() {
		super();
	}
	
}
