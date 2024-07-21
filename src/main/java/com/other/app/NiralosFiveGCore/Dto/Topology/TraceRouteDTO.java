package com.other.app.NiralosFiveGCore.Dto.Topology;

import com.other.app.NiralosFiveGCore.model.Topology.DeploymentModel;
import com.other.app.NiralosFiveGCore.model.Topology.DeviceModel;
import com.other.app.NiralosFiveGCore.model.Topology.SubZoneModel;
import com.other.app.NiralosFiveGCore.model.Topology.ZoneModel;

public class TraceRouteDTO {

	
	ZoneModel zoneModel;
	SubZoneModel subZoneModel;
	DeploymentModel deploymentModel;
	DeviceModel deviceModel;
//	GnBModel gnBModel;

	public TraceRouteDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TraceRouteDTO(ZoneModel zoneModel) {
		super();
		this.zoneModel = zoneModel;
	}

	public TraceRouteDTO(ZoneModel zoneModel, SubZoneModel subZoneModel) {
		super();
		this.zoneModel = zoneModel;
		this.subZoneModel = subZoneModel;
	}

	public TraceRouteDTO(ZoneModel zoneModel, SubZoneModel subZoneModel, DeploymentModel deploymentModel) {
		super();
		this.zoneModel = zoneModel;
		this.subZoneModel = subZoneModel;
		this.deploymentModel = deploymentModel;
	}

	public TraceRouteDTO(ZoneModel zoneModel, SubZoneModel subZoneModel, DeploymentModel deploymentModel,
			DeviceModel deviceModel) {
		super();
		this.zoneModel = zoneModel;
		this.subZoneModel = subZoneModel;
		this.deploymentModel = deploymentModel;
		this.deviceModel = deviceModel;
	}

	public ZoneModel getZoneModel() {
		return zoneModel;
	}

	public void setZoneModel(ZoneModel zoneModel) {
		this.zoneModel = zoneModel;
	}

	public SubZoneModel getSubZoneModel() {
		return subZoneModel;
	}

	public void setSubZoneModel(SubZoneModel subZoneModel) {
		this.subZoneModel = subZoneModel;
	}

	public DeploymentModel getDeploymentModel() {
		return deploymentModel;
	}

	public void setDeploymentModel(DeploymentModel deploymentModel) {
		this.deploymentModel = deploymentModel;
	}

	public DeviceModel getDeviceModel() {
		return deviceModel;
	}

	public void setDeviceModel(DeviceModel deviceModel) {
		this.deviceModel = deviceModel;
	}

//	public GnBModel getGnBModel() {
//		return gnBModel;
//	}

//	public void setGnBModel(GnBModel gnBModel) {
//		this.gnBModel = gnBModel;
//	}

}
