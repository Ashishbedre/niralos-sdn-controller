package com.other.app.NiralosFiveGCore.Dto.LiveData.Frontend.graph;

import java.util.List;

public class RootUpgUpfErrorDto {
	 private List<UpfErrorGraphDto> upfErrors;
	    private List<UpgErrorGraphDto> upgErrors;
		public List<UpfErrorGraphDto> getUpfErrors() {
			return upfErrors;
		}
		public void setUpfErrors(List<UpfErrorGraphDto> upfErrors) {
			this.upfErrors = upfErrors;
		}
		public List<UpgErrorGraphDto> getUpgErrors() {
			return upgErrors;
		}
		public void setUpgErrors(List<UpgErrorGraphDto> upgErrors) {
			this.upgErrors = upgErrors;
		}
		public RootUpgUpfErrorDto(List<UpfErrorGraphDto> upfErrors, List<UpgErrorGraphDto> upgErrors) {
			super();
			this.upfErrors = upfErrors;
			this.upgErrors = upgErrors;
		}
		public RootUpgUpfErrorDto() {
			super();
		}
	    
	    
}
