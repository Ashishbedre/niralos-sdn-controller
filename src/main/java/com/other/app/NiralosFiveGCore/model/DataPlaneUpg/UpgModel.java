package com.other.app.NiralosFiveGCore.model.DataPlaneUpg;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "upg_stats_data_frontend")
public class UpgModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="id")
	private Long id;
	@Column(name="interface_name")
    private String interfaceName;
    @Column(name="index_data")
    private Integer indexData;
    @Column(name="thread_number")
    private Integer threadNumber;
    @Column(name="rx_packets")
    private Integer rx_packets;
    @Column(name="previous_rx_packets")
    private Integer previous_rx_packets;
    @Column(name="rx_bytes")
    private Integer rx_bytes;
    @Column(name="previous_rx_bytes")
    private Integer previous_rx_bytes;
    @Column(name="tx_packets")
    private Integer tx_packets;
    @Column(name="previous_tx_packets")
    private Integer previous_tx_packets;
    @Column(name="tx_bytes")
    private Integer tx_bytes;
    @Column(name="previous_tx_bytes")
    private Integer previous_tx_bytes;
    @Column(name="punt")
    private Integer punt;
    @Column(name="drops")
    private Integer drops;
    @Column(name="ip4")
    private Integer ip4;
    @Column(name="ip6")
    private Integer ip6;
	@Column(name = "nf_type")
	private String nfType;
	@Column(name = "nf_name")
	private String nfName;
	public UpgModel(String interfaceName, Integer indexData, Integer threadNumber, Integer rx_packets,
			Integer previous_rx_packets, Integer rx_bytes, Integer previous_rx_bytes, Integer tx_packets,
			Integer previous_tx_packets, Integer tx_bytes, Integer previous_tx_bytes, Integer punt, Integer drops,
			Integer ip4, Integer ip6, String nfType, String nfName) {
		super();
		this.interfaceName = interfaceName;
		this.indexData = indexData;
		this.threadNumber = threadNumber;
		this.rx_packets = rx_packets;
		this.previous_rx_packets = previous_rx_packets;
		this.rx_bytes = rx_bytes;
		this.previous_rx_bytes = previous_rx_bytes;
		this.tx_packets = tx_packets;
		this.previous_tx_packets = previous_tx_packets;
		this.tx_bytes = tx_bytes;
		this.previous_tx_bytes = previous_tx_bytes;
		this.punt = punt;
		this.drops = drops;
		this.ip4 = ip4;
		this.ip6 = ip6;
		this.nfType = nfType;
		this.nfName = nfName;
	}
	public UpgModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getInterfaceName() {
		return interfaceName;
	}
	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}
	public Integer getIndexData() {
		return indexData;
	}
	public void setIndexData(Integer indexData) {
		this.indexData = indexData;
	}
	public Integer getThreadNumber() {
		return threadNumber;
	}
	public void setThreadNumber(Integer threadNumber) {
		this.threadNumber = threadNumber;
	}
	public Integer getRx_packets() {
		return rx_packets;
	}
	public void setRx_packets(Integer rx_packets) {
		this.rx_packets = rx_packets;
	}
	public Integer getPrevious_rx_packets() {
		return previous_rx_packets;
	}
	public void setPrevious_rx_packets(Integer previous_rx_packets) {
		this.previous_rx_packets = previous_rx_packets;
	}
	public Integer getRx_bytes() {
		return rx_bytes;
	}
	public void setRx_bytes(Integer rx_bytes) {
		this.rx_bytes = rx_bytes;
	}
	public Integer getPrevious_rx_bytes() {
		return previous_rx_bytes;
	}
	public void setPrevious_rx_bytes(Integer previous_rx_bytes) {
		this.previous_rx_bytes = previous_rx_bytes;
	}
	public Integer getTx_packets() {
		return tx_packets;
	}
	public void setTx_packets(Integer tx_packets) {
		this.tx_packets = tx_packets;
	}
	public Integer getPrevious_tx_packets() {
		return previous_tx_packets;
	}
	public void setPrevious_tx_packets(Integer previous_tx_packets) {
		this.previous_tx_packets = previous_tx_packets;
	}
	public Integer getTx_bytes() {
		return tx_bytes;
	}
	public void setTx_bytes(Integer tx_bytes) {
		this.tx_bytes = tx_bytes;
	}
	public Integer getPrevious_tx_bytes() {
		return previous_tx_bytes;
	}
	public void setPrevious_tx_bytes(Integer previous_tx_bytes) {
		this.previous_tx_bytes = previous_tx_bytes;
	}
	public Integer getPunt() {
		return punt;
	}
	public void setPunt(Integer punt) {
		this.punt = punt;
	}
	public Integer getDrops() {
		return drops;
	}
	public void setDrops(Integer drops) {
		this.drops = drops;
	}
	public Integer getIp4() {
		return ip4;
	}
	public void setIp4(Integer ip4) {
		this.ip4 = ip4;
	}
	public Integer getIp6() {
		return ip6;
	}
	public void setIp6(Integer ip6) {
		this.ip6 = ip6;
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
