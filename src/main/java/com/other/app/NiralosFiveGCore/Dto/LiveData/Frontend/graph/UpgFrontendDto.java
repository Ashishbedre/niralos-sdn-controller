package com.other.app.NiralosFiveGCore.Dto.LiveData.Frontend.graph;

import java.time.LocalDateTime;

public class UpgFrontendDto {
	
	private LocalDateTime localDateTime;
	private String interfaceName;
	private Integer rx_packets;
	private Integer rxBytes;
	private Integer tx_packets;
	private Integer txBytes;
	private Integer drops;
	
	public UpgFrontendDto(LocalDateTime localDateTime, String interfaceName, Integer rx_packets, Integer rxBytes,
			Integer tx_packets, Integer txBytes, Integer drops) {
		super();
		this.localDateTime = localDateTime;
		this.interfaceName = interfaceName;
		this.rx_packets = rx_packets;
		this.rxBytes = rxBytes;
		this.tx_packets = tx_packets;
		this.txBytes = txBytes;
		this.drops = drops;
	}
	public UpgFrontendDto() {
		super();
	}
	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}
	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}
	public String getInterfaceName() {
		return interfaceName;
	}
	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}
	public Integer getRx_packets() {
		return rx_packets;
	}
	public void setRx_packets(Integer rx_packets) {
		this.rx_packets = rx_packets;
	}
	public Integer getRxBytes() {
		return rxBytes;
	}
	public void setRxBytes(Integer rxBytes) {
		this.rxBytes = rxBytes;
	}
	public Integer getTx_packets() {
		return tx_packets;
	}
	public void setTx_packets(Integer tx_packets) {
		this.tx_packets = tx_packets;
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
