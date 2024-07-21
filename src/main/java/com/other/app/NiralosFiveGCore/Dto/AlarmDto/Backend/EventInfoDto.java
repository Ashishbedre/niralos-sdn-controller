package com.other.app.NiralosFiveGCore.Dto.AlarmDto.Backend;

public class EventInfoDto {
	 public String subeventId;
	    public String srcDescription;
	    public String srcType;
	    public String srcIP;
	    public String dateTime;
	    public String destType;
	    public String warningMsg;
		public String getSubeventId() {
			return subeventId;
		}
		public void setSubeventId(String subeventId) {
			this.subeventId = subeventId;
		}
		public String getSrcDescription() {
			return srcDescription;
		}
		public void setSrcDescription(String srcDescription) {
			this.srcDescription = srcDescription;
		}
		public String getSrcType() {
			return srcType;
		}
		public void setSrcType(String srcType) {
			this.srcType = srcType;
		}
		public String getSrcIP() {
			return srcIP;
		}
		public void setSrcIP(String srcIP) {
			this.srcIP = srcIP;
		}
		public String getDateTime() {
			return dateTime;
		}
		public void setDateTime(String dateTime) {
			this.dateTime = dateTime;
		}
		public String getDestType() {
			return destType;
		}
		public void setDestType(String destType) {
			this.destType = destType;
		}
		public String getWarningMsg() {
			return warningMsg;
		}
		public void setWarningMsg(String warningMsg) {
			this.warningMsg = warningMsg;
		}
	    
	    
}
