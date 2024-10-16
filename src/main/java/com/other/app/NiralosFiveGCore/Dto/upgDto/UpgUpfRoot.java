package com.other.app.NiralosFiveGCore.Dto.upgDto;

import java.util.List;



public class UpgUpfRoot {
	 private List<UpgUpfInterfaceDto> intrface;
	    private List<UpgUpfNodeDto> node;
		public List<UpgUpfInterfaceDto> getIntrface() {
			return intrface;
		}
		public void setIntrface(List<UpgUpfInterfaceDto> intrface) {
			this.intrface = intrface;
		}
		public List<UpgUpfNodeDto> getNode() {
			return node;
		}
		public void setNode(List<UpgUpfNodeDto> node) {
			this.node = node;
		}
		public UpgUpfRoot(List<UpgUpfInterfaceDto> intrface, List<UpgUpfNodeDto> node) {
			super();
			this.intrface = intrface;
			this.node = node;
		}
		public UpgUpfRoot() {
			super();
		}
		
	    
}
