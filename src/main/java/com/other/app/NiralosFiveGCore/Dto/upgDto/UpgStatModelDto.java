package com.other.app.NiralosFiveGCore.Dto.upgDto;

public class UpgStatModelDto {

	  private String interfaceName;
	    private Integer index;
	    private Integer threadNumber;
	    private Integer rx_packets;
	    private Integer rx_bytes;
	    private Integer tx_packets;
	    private Integer tx_bytes;
	    private Integer punt;
	    private Integer drops;
	    private Integer ip4;
	    private Integer ip6;
	    
		public String getInterfaceName() {
			return interfaceName;
		}
		public void setInterfaceName(String interfaceName) {
			this.interfaceName = interfaceName;
		}
		public Integer getIndex() {
			return index;
		}
		public void setIndex(Integer index) {
			this.index = index;
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
		public Integer getRx_bytes() {
			return rx_bytes;
		}
		public void setRx_bytes(Integer rx_bytes) {
			this.rx_bytes = rx_bytes;
		}
		public Integer getTx_packets() {
			return tx_packets;
		}
		public void setTx_packets(Integer tx_packets) {
			this.tx_packets = tx_packets;
		}
		public Integer getTx_bytes() {
			return tx_bytes;
		}
		public void setTx_bytes(Integer tx_bytes) {
			this.tx_bytes = tx_bytes;
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
		
}
