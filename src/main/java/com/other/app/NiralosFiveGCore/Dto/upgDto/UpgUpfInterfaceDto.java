package com.other.app.NiralosFiveGCore.Dto.upgDto;

public class UpgUpfInterfaceDto {
	private Integer tun_write_failure_upf;
	private Integer invalid_eth_type_upf;
	private Integer invalid_gtpu_version_upf;
	private Integer small_gtpu_packet_upf;
	private Integer no_outer_header_creation_upf;
	private Integer tun_open_failure_upf;
	private Integer far_not_found_upf;
	private Integer gtpu_packet_decode_failure_upf;
	private Integer igmp_input_IGMP_not_enabled_on_this_interface;
	private Integer local0_output_interface_is_down;
	private Integer local0_output_interface_is_deleted;
	private Integer local0_output_no_buffers_to_segment_GSO;
	private Integer N3_GNB_output_interface_is_down;
	private Integer N3_GNB_output_interface_is_deleted;
	private Integer N3_GNB_output_no_buffers_to_segment_GSO;
	private Integer N3_GNB_tx_DPDK_tx_function_returned_an_error;
	private Integer N3_GNB_tx_Tx_packet_drops__dpdk_tx_failure;
	private Integer N4_SMF_output_interface_is_down;
	private Integer N4_SMF_output_interface_is_deleted;
	private Integer N4_SMF_output_no_buffers_to_segment_GSO;
	private Integer N4_SMF_tx_DPDK_tx_function_returned_an_error;
	private Integer N4_SMF_tx_Tx_packet_drops__dpdk_tx_failure;
	private Integer N6_DN_output_interface_is_down;
	private Integer N6_DN_output_interface_is_deleted;
	private Integer N6_DN_output_no_buffers_to_segment_GSO;
	private Integer N6_DN_tx_DPDK_tx_function_returned_an_error;
	private Integer N6_DN_tx_Tx_packet_drops__dpdk_tx_failure;
	private Integer MEC_output_interface_is_deleted;
	private Integer MEC_output_no_buffers_to_segment_GSO;
	private Integer MEC_tx_DPDK_tx_function_returned_an_error;
	private Integer MEC_tx_Tx_packet_drops__dpdk_tx_failure;
	private Integer MEC_output_interface_is_down;
	private Long thread;
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
	public Integer getMEC_output_interface_is_down() {
		return MEC_output_interface_is_down;
	}
	public void setMEC_output_interface_is_down(Integer mEC_output_interface_is_down) {
		MEC_output_interface_is_down = mEC_output_interface_is_down;
	}
	public Long getThread() {
		return thread;
	}
	public void setThread(Long thread) {
		this.thread = thread;
	}
	public Integer getTun_write_failure_upf() {
		return tun_write_failure_upf;
	}
	public void setTun_write_failure_upf(Integer tun_write_failure_upf) {
		this.tun_write_failure_upf = tun_write_failure_upf;
	}
	public Integer getInvalid_eth_type_upf() {
		return invalid_eth_type_upf;
	}
	public void setInvalid_eth_type_upf(Integer invalid_eth_type_upf) {
		this.invalid_eth_type_upf = invalid_eth_type_upf;
	}
	public Integer getInvalid_gtpu_version_upf() {
		return invalid_gtpu_version_upf;
	}
	public void setInvalid_gtpu_version_upf(Integer invalid_gtpu_version_upf) {
		this.invalid_gtpu_version_upf = invalid_gtpu_version_upf;
	}
	public Integer getSmall_gtpu_packet_upf() {
		return small_gtpu_packet_upf;
	}
	public void setSmall_gtpu_packet_upf(Integer small_gtpu_packet_upf) {
		this.small_gtpu_packet_upf = small_gtpu_packet_upf;
	}
	public Integer getNo_outer_header_creation_upf() {
		return no_outer_header_creation_upf;
	}
	public void setNo_outer_header_creation_upf(Integer no_outer_header_creation_upf) {
		this.no_outer_header_creation_upf = no_outer_header_creation_upf;
	}
	public Integer getTun_open_failure_upf() {
		return tun_open_failure_upf;
	}
	public void setTun_open_failure_upf(Integer tun_open_failure_upf) {
		this.tun_open_failure_upf = tun_open_failure_upf;
	}
	public Integer getFar_not_found_upf() {
		return far_not_found_upf;
	}
	public void setFar_not_found_upf(Integer far_not_found_upf) {
		this.far_not_found_upf = far_not_found_upf;
	}
	public Integer getGtpu_packet_decode_failure_upf() {
		return gtpu_packet_decode_failure_upf;
	}
	public void setGtpu_packet_decode_failure_upf(Integer gtpu_packet_decode_failure_upf) {
		this.gtpu_packet_decode_failure_upf = gtpu_packet_decode_failure_upf;
	}
	public Integer getIgmp_input_IGMP_not_enabled_on_this_interface() {
		return igmp_input_IGMP_not_enabled_on_this_interface;
	}
	public void setIgmp_input_IGMP_not_enabled_on_this_interface(Integer igmp_input_IGMP_not_enabled_on_this_interface) {
		this.igmp_input_IGMP_not_enabled_on_this_interface = igmp_input_IGMP_not_enabled_on_this_interface;
	}
	public Integer getLocal0_output_interface_is_down() {
		return local0_output_interface_is_down;
	}
	public void setLocal0_output_interface_is_down(Integer local0_output_interface_is_down) {
		this.local0_output_interface_is_down = local0_output_interface_is_down;
	}
	public Integer getLocal0_output_interface_is_deleted() {
		return local0_output_interface_is_deleted;
	}
	public void setLocal0_output_interface_is_deleted(Integer local0_output_interface_is_deleted) {
		this.local0_output_interface_is_deleted = local0_output_interface_is_deleted;
	}
	public Integer getLocal0_output_no_buffers_to_segment_GSO() {
		return local0_output_no_buffers_to_segment_GSO;
	}
	public void setLocal0_output_no_buffers_to_segment_GSO(Integer local0_output_no_buffers_to_segment_GSO) {
		this.local0_output_no_buffers_to_segment_GSO = local0_output_no_buffers_to_segment_GSO;
	}
	public Integer getN3_GNB_output_interface_is_down() {
		return N3_GNB_output_interface_is_down;
	}
	public void setN3_GNB_output_interface_is_down(Integer n3_GNB_output_interface_is_down) {
		N3_GNB_output_interface_is_down = n3_GNB_output_interface_is_down;
	}
	public Integer getN3_GNB_output_interface_is_deleted() {
		return N3_GNB_output_interface_is_deleted;
	}
	public void setN3_GNB_output_interface_is_deleted(Integer n3_GNB_output_interface_is_deleted) {
		N3_GNB_output_interface_is_deleted = n3_GNB_output_interface_is_deleted;
	}
	public Integer getN3_GNB_output_no_buffers_to_segment_GSO() {
		return N3_GNB_output_no_buffers_to_segment_GSO;
	}
	public void setN3_GNB_output_no_buffers_to_segment_GSO(Integer n3_GNB_output_no_buffers_to_segment_GSO) {
		N3_GNB_output_no_buffers_to_segment_GSO = n3_GNB_output_no_buffers_to_segment_GSO;
	}
	public Integer getN3_GNB_tx_DPDK_tx_function_returned_an_error() {
		return N3_GNB_tx_DPDK_tx_function_returned_an_error;
	}
	public void setN3_GNB_tx_DPDK_tx_function_returned_an_error(Integer n3_GNB_tx_DPDK_tx_function_returned_an_error) {
		N3_GNB_tx_DPDK_tx_function_returned_an_error = n3_GNB_tx_DPDK_tx_function_returned_an_error;
	}
	public Integer getN3_GNB_tx_Tx_packet_drops__dpdk_tx_failure() {
		return N3_GNB_tx_Tx_packet_drops__dpdk_tx_failure;
	}
	public void setN3_GNB_tx_Tx_packet_drops__dpdk_tx_failure(Integer n3_GNB_tx_Tx_packet_drops__dpdk_tx_failure) {
		N3_GNB_tx_Tx_packet_drops__dpdk_tx_failure = n3_GNB_tx_Tx_packet_drops__dpdk_tx_failure;
	}
	public Integer getN4_SMF_output_interface_is_down() {
		return N4_SMF_output_interface_is_down;
	}
	public void setN4_SMF_output_interface_is_down(Integer n4_SMF_output_interface_is_down) {
		N4_SMF_output_interface_is_down = n4_SMF_output_interface_is_down;
	}
	public Integer getN4_SMF_output_interface_is_deleted() {
		return N4_SMF_output_interface_is_deleted;
	}
	public void setN4_SMF_output_interface_is_deleted(Integer n4_SMF_output_interface_is_deleted) {
		N4_SMF_output_interface_is_deleted = n4_SMF_output_interface_is_deleted;
	}
	public Integer getN4_SMF_output_no_buffers_to_segment_GSO() {
		return N4_SMF_output_no_buffers_to_segment_GSO;
	}
	public void setN4_SMF_output_no_buffers_to_segment_GSO(Integer n4_SMF_output_no_buffers_to_segment_GSO) {
		N4_SMF_output_no_buffers_to_segment_GSO = n4_SMF_output_no_buffers_to_segment_GSO;
	}
	public Integer getN4_SMF_tx_DPDK_tx_function_returned_an_error() {
		return N4_SMF_tx_DPDK_tx_function_returned_an_error;
	}
	public void setN4_SMF_tx_DPDK_tx_function_returned_an_error(Integer n4_SMF_tx_DPDK_tx_function_returned_an_error) {
		N4_SMF_tx_DPDK_tx_function_returned_an_error = n4_SMF_tx_DPDK_tx_function_returned_an_error;
	}
	public Integer getN4_SMF_tx_Tx_packet_drops__dpdk_tx_failure() {
		return N4_SMF_tx_Tx_packet_drops__dpdk_tx_failure;
	}
	public void setN4_SMF_tx_Tx_packet_drops__dpdk_tx_failure(Integer n4_SMF_tx_Tx_packet_drops__dpdk_tx_failure) {
		N4_SMF_tx_Tx_packet_drops__dpdk_tx_failure = n4_SMF_tx_Tx_packet_drops__dpdk_tx_failure;
	}
	public Integer getN6_DN_output_interface_is_down() {
		return N6_DN_output_interface_is_down;
	}
	public void setN6_DN_output_interface_is_down(Integer n6_DN_output_interface_is_down) {
		N6_DN_output_interface_is_down = n6_DN_output_interface_is_down;
	}
	public Integer getN6_DN_output_interface_is_deleted() {
		return N6_DN_output_interface_is_deleted;
	}
	public void setN6_DN_output_interface_is_deleted(Integer n6_DN_output_interface_is_deleted) {
		N6_DN_output_interface_is_deleted = n6_DN_output_interface_is_deleted;
	}
	public Integer getN6_DN_output_no_buffers_to_segment_GSO() {
		return N6_DN_output_no_buffers_to_segment_GSO;
	}
	public void setN6_DN_output_no_buffers_to_segment_GSO(Integer n6_DN_output_no_buffers_to_segment_GSO) {
		N6_DN_output_no_buffers_to_segment_GSO = n6_DN_output_no_buffers_to_segment_GSO;
	}
	public Integer getN6_DN_tx_DPDK_tx_function_returned_an_error() {
		return N6_DN_tx_DPDK_tx_function_returned_an_error;
	}
	public void setN6_DN_tx_DPDK_tx_function_returned_an_error(Integer n6_DN_tx_DPDK_tx_function_returned_an_error) {
		N6_DN_tx_DPDK_tx_function_returned_an_error = n6_DN_tx_DPDK_tx_function_returned_an_error;
	}
	public Integer getN6_DN_tx_Tx_packet_drops__dpdk_tx_failure() {
		return N6_DN_tx_Tx_packet_drops__dpdk_tx_failure;
	}
	public void setN6_DN_tx_Tx_packet_drops__dpdk_tx_failure(Integer n6_DN_tx_Tx_packet_drops__dpdk_tx_failure) {
		N6_DN_tx_Tx_packet_drops__dpdk_tx_failure = n6_DN_tx_Tx_packet_drops__dpdk_tx_failure;
	}
	public Integer getMEC_output_interface_is_deleted() {
		return MEC_output_interface_is_deleted;
	}
	public void setMEC_output_interface_is_deleted(Integer mEC_output_interface_is_deleted) {
		MEC_output_interface_is_deleted = mEC_output_interface_is_deleted;
	}
	public Integer getMEC_output_no_buffers_to_segment_GSO() {
		return MEC_output_no_buffers_to_segment_GSO;
	}
	public void setMEC_output_no_buffers_to_segment_GSO(Integer mEC_output_no_buffers_to_segment_GSO) {
		MEC_output_no_buffers_to_segment_GSO = mEC_output_no_buffers_to_segment_GSO;
	}
	public Integer getMEC_tx_DPDK_tx_function_returned_an_error() {
		return MEC_tx_DPDK_tx_function_returned_an_error;
	}
	public void setMEC_tx_DPDK_tx_function_returned_an_error(Integer mEC_tx_DPDK_tx_function_returned_an_error) {
		MEC_tx_DPDK_tx_function_returned_an_error = mEC_tx_DPDK_tx_function_returned_an_error;
	}
	public Integer getMEC_tx_Tx_packet_drops__dpdk_tx_failure() {
		return MEC_tx_Tx_packet_drops__dpdk_tx_failure;
	}
	public void setMEC_tx_Tx_packet_drops__dpdk_tx_failure(Integer mEC_tx_Tx_packet_drops__dpdk_tx_failure) {
		MEC_tx_Tx_packet_drops__dpdk_tx_failure = mEC_tx_Tx_packet_drops__dpdk_tx_failure;
	}
	public UpgUpfInterfaceDto(Integer tun_write_failure_upf, Integer invalid_eth_type_upf,
			Integer invalid_gtpu_version_upf, Integer small_gtpu_packet_upf, Integer no_outer_header_creation_upf,
			Integer tun_open_failure_upf, Integer far_not_found_upf, Integer gtpu_packet_decode_failure_upf,
			Integer igmp_input_IGMP_not_enabled_on_this_interface, Integer local0_output_interface_is_down,
			Integer local0_output_interface_is_deleted, Integer local0_output_no_buffers_to_segment_GSO,
			Integer n3_GNB_output_interface_is_down, Integer n3_GNB_output_interface_is_deleted,
			Integer n3_GNB_output_no_buffers_to_segment_GSO, Integer n3_GNB_tx_DPDK_tx_function_returned_an_error,
			Integer n3_GNB_tx_Tx_packet_drops__dpdk_tx_failure, Integer n4_SMF_output_interface_is_down,
			Integer n4_SMF_output_interface_is_deleted, Integer n4_SMF_output_no_buffers_to_segment_GSO,
			Integer n4_SMF_tx_DPDK_tx_function_returned_an_error, Integer n4_SMF_tx_Tx_packet_drops__dpdk_tx_failure,
			Integer n6_DN_output_interface_is_down, Integer n6_DN_output_interface_is_deleted,
			Integer n6_DN_output_no_buffers_to_segment_GSO, Integer n6_DN_tx_DPDK_tx_function_returned_an_error,
			Integer n6_DN_tx_Tx_packet_drops__dpdk_tx_failure, Integer mEC_output_interface_is_deleted,
			Integer mEC_output_no_buffers_to_segment_GSO, Integer mEC_tx_DPDK_tx_function_returned_an_error,
			Integer mEC_tx_Tx_packet_drops__dpdk_tx_failure) {
		super();
		this.tun_write_failure_upf = tun_write_failure_upf;
		this.invalid_eth_type_upf = invalid_eth_type_upf;
		this.invalid_gtpu_version_upf = invalid_gtpu_version_upf;
		this.small_gtpu_packet_upf = small_gtpu_packet_upf;
		this.no_outer_header_creation_upf = no_outer_header_creation_upf;
		this.tun_open_failure_upf = tun_open_failure_upf;
		this.far_not_found_upf = far_not_found_upf;
		this.gtpu_packet_decode_failure_upf = gtpu_packet_decode_failure_upf;
		this.igmp_input_IGMP_not_enabled_on_this_interface = igmp_input_IGMP_not_enabled_on_this_interface;
		this.local0_output_interface_is_down = local0_output_interface_is_down;
		this.local0_output_interface_is_deleted = local0_output_interface_is_deleted;
		this.local0_output_no_buffers_to_segment_GSO = local0_output_no_buffers_to_segment_GSO;
		this.N3_GNB_output_interface_is_down = n3_GNB_output_interface_is_down;
		this.N3_GNB_output_interface_is_deleted = n3_GNB_output_interface_is_deleted;
		this.N3_GNB_output_no_buffers_to_segment_GSO = n3_GNB_output_no_buffers_to_segment_GSO;
		this.N3_GNB_tx_DPDK_tx_function_returned_an_error = n3_GNB_tx_DPDK_tx_function_returned_an_error;
		this.N3_GNB_tx_Tx_packet_drops__dpdk_tx_failure = n3_GNB_tx_Tx_packet_drops__dpdk_tx_failure;
		this.N4_SMF_output_interface_is_down = n4_SMF_output_interface_is_down;
		this.N4_SMF_output_interface_is_deleted = n4_SMF_output_interface_is_deleted;
		this.N4_SMF_output_no_buffers_to_segment_GSO = n4_SMF_output_no_buffers_to_segment_GSO;
		this.N4_SMF_tx_DPDK_tx_function_returned_an_error = n4_SMF_tx_DPDK_tx_function_returned_an_error;
		this.N4_SMF_tx_Tx_packet_drops__dpdk_tx_failure = n4_SMF_tx_Tx_packet_drops__dpdk_tx_failure;
		this.N6_DN_output_interface_is_down = n6_DN_output_interface_is_down;
		this.N6_DN_output_interface_is_deleted = n6_DN_output_interface_is_deleted;
		this.N6_DN_output_no_buffers_to_segment_GSO = n6_DN_output_no_buffers_to_segment_GSO;
		this.N6_DN_tx_DPDK_tx_function_returned_an_error = n6_DN_tx_DPDK_tx_function_returned_an_error;
		this.N6_DN_tx_Tx_packet_drops__dpdk_tx_failure = n6_DN_tx_Tx_packet_drops__dpdk_tx_failure;
		this.MEC_output_interface_is_deleted = mEC_output_interface_is_deleted;
		this.MEC_output_no_buffers_to_segment_GSO = mEC_output_no_buffers_to_segment_GSO;
		this.MEC_tx_DPDK_tx_function_returned_an_error = mEC_tx_DPDK_tx_function_returned_an_error;
		this.MEC_tx_Tx_packet_drops__dpdk_tx_failure = mEC_tx_Tx_packet_drops__dpdk_tx_failure;
	}
	public UpgUpfInterfaceDto() {
		super();
	}
	
}
