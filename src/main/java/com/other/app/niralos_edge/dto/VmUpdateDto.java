package com.other.app.niralos_edge.dto;

import java.util.List;

public class VmUpdateDto {

	private String memory;
    private String cores;
    private String sockets;
    private Long vmId;
    private List<PorxmoxInterfaceBody> deleteInterfaces;
	private List<AddInterfaceDto> addInterfaces;
    
    
   
    
    

	
	public List<PorxmoxInterfaceBody> getDeleteInterfaces() {
		return deleteInterfaces;
	}
	public void setDeleteInterfaces(List<PorxmoxInterfaceBody> deleteInterfaces) {
		this.deleteInterfaces = deleteInterfaces;
	}
	public List<AddInterfaceDto> getAddInterfaces() {
		return addInterfaces;
	}
	public void setAddInterfaces(List<AddInterfaceDto> addInterfaces) {
		this.addInterfaces = addInterfaces;
	}
	public String getMemory() {
		return memory;
	}
	public void setMemory(String memory) {
		this.memory = memory;
	}
	public String getCores() {
		return cores;
	}
	public void setCores(String cores) {
		this.cores = cores;
	}
	public String getSockets() {
		return sockets;
	}
	public void setSockets(String sockets) {
		this.sockets = sockets;
	}
	public Long getVmId() {
		return vmId;
	}
	public void setVmId(Long vmId) {
		this.vmId = vmId;
	}
	
	
	public VmUpdateDto(String memory, String cores, String sockets, Long vmId) {
		super();
		this.memory = memory;
		this.cores = cores;
		this.sockets = sockets;
		this.vmId = vmId;
	}
	
	
	public VmUpdateDto() {
		super();
	}
    
     
}
