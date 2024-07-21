package com.other.app.niralos_edge.Service.VMManagement;

public interface VmMangementService {

    public void startVm(Long vmId,String edgeClientId);
	
	public void stopVm(Long vmId,String edgeClientId);
	
	public void restartVm(Long vmId,String edgeClientId);
	
	public void deleteVm(Long vmId,String edgeClientId);
	
	public void checkDeletedVm();
	
	public void deleteGraphCpuAndMemory();
}
