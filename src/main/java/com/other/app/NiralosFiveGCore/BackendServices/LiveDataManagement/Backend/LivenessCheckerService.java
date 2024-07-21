package com.other.app.NiralosFiveGCore.BackendServices.LiveDataManagement.Backend;

//Interface
public interface LivenessCheckerService {
//	Used to check if the Gnb(s) are live or dead
	public void gngAndUeLivenessChecker();
//	Used to check the Liveness of the Niral 5G Core
	public void coreLivenessChecker();
	
	public void basicInformation();

}
