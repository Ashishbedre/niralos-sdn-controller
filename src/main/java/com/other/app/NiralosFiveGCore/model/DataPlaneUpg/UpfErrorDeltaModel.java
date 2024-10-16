package com.other.app.NiralosFiveGCore.model.DataPlaneUpg;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "upf_errordelta_model")
public class UpfErrorDeltaModel {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")	
	@Column(name="id")
	private Long id;
	@Column(name="tun_write_failure_upf")
	private Integer tun_write_failure_upf;
	@Column(name="invalid_eth_type_upf")
	private Integer invalid_eth_type_upf;
	@Column(name="invalid_gtpu_version_upf")
	private Integer invalid_gtpu_version_upf;
	@Column(name="small_gtpu_packet_upf")
	private Integer small_gtpu_packet_upf;
	@Column(name="no_outer_header_creation_upf")
	private Integer no_outer_header_creation_upf;
	@Column(name="tun_open_failure_upf")
	private Integer tun_open_failure_upf;
	@Column(name="far_not_found_upf")
	private Integer far_not_found_upf;
	@Column(name="gtpu_packet_decode_failure_upf")
	private Integer gtpu_packet_decode_failure_upf;
	@Column(name = "nf_type")
	private String nfType;
	@Column(name = "nf_name")
	private String nfName;
	public UpfErrorDeltaModel(Integer tun_write_failure_upf, Integer invalid_eth_type_upf, Integer invalid_gtpu_version_upf,
			Integer small_gtpu_packet_upf, Integer no_outer_header_creation_upf, Integer tun_open_failure_upf,
			Integer far_not_found_upf, Integer gtpu_packet_decode_failure_upf, String nfType, String nfName) {
		super();
		this.tun_write_failure_upf = tun_write_failure_upf;
		this.invalid_eth_type_upf = invalid_eth_type_upf;
		this.invalid_gtpu_version_upf = invalid_gtpu_version_upf;
		this.small_gtpu_packet_upf = small_gtpu_packet_upf;
		this.no_outer_header_creation_upf = no_outer_header_creation_upf;
		this.tun_open_failure_upf = tun_open_failure_upf;
		this.far_not_found_upf = far_not_found_upf;
		this.gtpu_packet_decode_failure_upf = gtpu_packet_decode_failure_upf;
		this.nfType = nfType;
		this.nfName = nfName;
	}
	public UpfErrorDeltaModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getNfType() {
		return nfType;
	}
	public void setNfType(String nfType) {
		this.nfType = nfType;
	}
	public String getNfName() {
		return nfName;
	}
	public void setNfName(String nfName) {
		this.nfName = nfName;
	}
	
	
}
