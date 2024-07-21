package com.other.app.NiralosFiveGCore.Dto.Topology;

import java.util.List;

public class MainDTO {
    private String name;
    private String level;
    private int status;
    private List<DeviceDTO> children;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public List<DeviceDTO> getChildren() {
		return children;
	}
	public void setChildren(List<DeviceDTO> children) {
		this.children = children;
	}
	
}
