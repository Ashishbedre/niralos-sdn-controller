package com.other.app.NiralosFiveGCore.Dto.PfcpInfoDto;

import java.util.ArrayList;
import java.util.List;

public class PfcpFailReasonListDto {
	 private List<String> sessionEstablishmentFailreasonList = new ArrayList<String>();
	    private List<String> sessionModificationFailreasonList = new ArrayList<String>();
	    private List<String> sessionDeletionFailreasonList = new ArrayList<String>();
		private int repeat;

		

		public int getRepeat() {
			return repeat;
		}

		public void setRepeat(int repeat) {
			this.repeat = repeat;
		}

		public List<String> getSessionEstablishmentFailreasonList() {
			return sessionEstablishmentFailreasonList;
		}

		public void setSessionEstablishmentFailreasonList(List<String> sessionEstablishmentFailreasonList) {
			this.sessionEstablishmentFailreasonList = sessionEstablishmentFailreasonList;
		}

		public List<String> getSessionModificationFailreasonList() {
			return sessionModificationFailreasonList;
		}

		public void setSessionModificationFailreasonList(List<String> sessionModificationFailreasonList) {
			this.sessionModificationFailreasonList = sessionModificationFailreasonList;
		}

		public List<String> getSessionDeletionFailreasonList() {
			return sessionDeletionFailreasonList;
		}

		public void setSessionDeletionFailreasonList(List<String> sessionDeletionFailreasonList) {
			this.sessionDeletionFailreasonList = sessionDeletionFailreasonList;
		}

		public PfcpFailReasonListDto() {
			super();
		}
	    
}
