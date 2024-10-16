package com.other.app.NiralosFiveGCore.Dto.PfcpInfoDto.Frontend;

import java.util.List;

public class PfcpInfoFrontendDto {
	private List<PfcpFailReasonFrontendDto> sessionEstablishmentFailReasonList;
    private List<PfcpFailReasonFrontendDto> sessionModificationFailreasonList;
    private List<PfcpFailReasonFrontendDto> sessionDeletionFailreasonList;

	public List<PfcpFailReasonFrontendDto> getSessionEstablishmentFailReasonList() {
		return sessionEstablishmentFailReasonList;
	}
	public void setSessionEstablishmentFailReasonList(List<PfcpFailReasonFrontendDto> sessionEstablishmentFailReasonList) {
		this.sessionEstablishmentFailReasonList = sessionEstablishmentFailReasonList;
	}
	public List<PfcpFailReasonFrontendDto> getSessionModificationFailreasonList() {
		return sessionModificationFailreasonList;
	}
	public void setSessionModificationFailreasonList(List<PfcpFailReasonFrontendDto> sessionModificationFailreasonList) {
		this.sessionModificationFailreasonList = sessionModificationFailreasonList;
	}
	public List<PfcpFailReasonFrontendDto> getSessionDeletionFailreasonList() {
		return sessionDeletionFailreasonList;
	}
	public void setSessionDeletionFailreasonList(List<PfcpFailReasonFrontendDto> sessionDeletionFailreasonList) {
		this.sessionDeletionFailreasonList = sessionDeletionFailreasonList;
	}
	
}
