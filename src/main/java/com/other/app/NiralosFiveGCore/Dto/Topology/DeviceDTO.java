package com.other.app.NiralosFiveGCore.Dto.Topology;

import java.util.List;



public class DeviceDTO {
	
	 private String name;
	    private String level;
	    private int status;
	    private List<NrfInfoDto> children;
	    
		public List<NrfInfoDto> getChildren() {
			return children;
		}
		public void setChildren(List<NrfInfoDto> children) {
			this.children = children;
		}
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
		

	

}
