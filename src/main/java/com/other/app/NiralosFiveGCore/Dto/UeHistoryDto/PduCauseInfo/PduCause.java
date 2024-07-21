package com.other.app.NiralosFiveGCore.Dto.UeHistoryDto.PduCauseInfo;



public class PduCause {
	 private Long id;

	    private String pducauseName;
	    private int repeated;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getPducauseName() {
			return pducauseName;
		}
		public void setPducauseName(String pducauseName) {
			this.pducauseName = pducauseName;
		}
		public int getRepeated() {
			return repeated;
		}
		public void setRepeated(int repeated) {
			this.repeated = repeated;
		}
	    
}
