package com.other.app.niralos_edge.dto.hypervisorcookiedto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Vms {

	@JsonProperty("VM.Config.CDROM")
	private Integer vmConfigCdrom;
	
	@JsonProperty("VM.Config.HWType")
	private Integer vmConfigHwType;
	@JsonProperty("VM.Migrate")
	private Integer vmMigrate;
	
	public Integer getVmConfigCdrom() {
		return vmConfigCdrom;
	}
	public void setVmConfigCdrom(Integer vmConfigCdrom) {
		this.vmConfigCdrom = vmConfigCdrom;
	}
	public Integer getVmConfigHwType() {
		return vmConfigHwType;
	}
	public void setVmConfigHwType(Integer vmConfigHwType) {
		this.vmConfigHwType = vmConfigHwType;
	}
	public Integer getVmMigrate() {
		return vmMigrate;
	}
	public void setVmMigrate(Integer vmMigrate) {
		this.vmMigrate = vmMigrate;
	}
	
	
}
