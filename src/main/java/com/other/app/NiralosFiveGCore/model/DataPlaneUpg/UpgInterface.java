package com.other.app.NiralosFiveGCore.model.DataPlaneUpg;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "upg_interface")
public class UpgInterface {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "serial_id")
	Long serialId;
	@Column(name = "tenant_id")
	Long tenantId;
	@Column(name = "interface_name")
	String interfaceName;
	@Column(name = "date_time")
	LocalDateTime localDateTime;
	@Column(name = "rx_packets")
	Integer rxPackets;
	@Column(name = "rx_bytes")
	Integer rxBytes;
	@Column(name = "tx_packets")
	Integer txPackets;
	@Column(name = "tx_bytes")
	Integer txBytes;
	@Column(name = "drops")
	Integer drops;

	
	
	public UpgInterface(Long tenantId, String interfaceName, LocalDateTime localDateTime, Integer rxPackets,
			Integer rxBytes, Integer txPackets, Integer txBytes, Integer drops) {
		super();
		this.tenantId = tenantId;
		this.interfaceName = interfaceName;
		this.localDateTime = localDateTime;
		this.rxPackets = rxPackets;
		this.rxBytes = rxBytes;
		this.txPackets = txPackets;
		this.txBytes = txBytes;
		this.drops = drops;
	}
	public UpgInterface() {
		super();
	}
	public Long getTenantId() {
		return tenantId;
	}
	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}
	public String getInterfaceName() {
		return interfaceName;
	}
	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}
	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}
	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}
	public Integer getRxPackets() {
		return rxPackets;
	}
	public void setRxPackets(Integer rxPackets) {
		this.rxPackets = rxPackets;
	}
	public Integer getRxBytes() {
		return rxBytes;
	}
	public void setRxBytes(Integer rxBytes) {
		this.rxBytes = rxBytes;
	}
	public Integer getTxPackets() {
		return txPackets;
	}
	public void setTxPackets(Integer txPackets) {
		this.txPackets = txPackets;
	}
	public Integer getTxBytes() {
		return txBytes;
	}
	public void setTxBytes(Integer txBytes) {
		this.txBytes = txBytes;
	}
	public Integer getDrops() {
		return drops;
	}
	public void setDrops(Integer drops) {
		this.drops = drops;
	}

}
