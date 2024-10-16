package com.other.app.NiralosFiveGCore.Dto.PfcpInfoDto.Frontend;

public class PfcpFailReasonFrontendDto {
	private String causename;
    private Integer repeat;
	public String getCausename() {
		return causename;
	}
	public void setCausename(String causename) {
		this.causename = causename;
	}
	public Integer getRepeat() {
		return repeat;
	}
	public void setRepeat(Integer repeat) {
		this.repeat = repeat;
	}
	public PfcpFailReasonFrontendDto(String causename, Integer repeat) {
		super();
		this.causename = causename;
		this.repeat = repeat;
	}
	
}
