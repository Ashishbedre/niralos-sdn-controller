package com.other.app.niralos_edge.Service.NodeBoot;

import com.other.app.niralos_edge.Model.NodeUpdateDto;

public interface NodeBootInterface {

	public void nodeRebootandShutdown(NodeUpdateDto payload,String edgeClientId);
	
}
