package com.other.app.NiralosFiveGCore.Dto.PfcpInfoDto;

import java.util.ArrayList;
import java.util.List;

public class PfcpInfoRootDto {
	private Integer total_cp_assoc_req;
	private Integer total_cp_assoc_resp;
	private Integer totalEstReq;
	private Integer sucessEstReq;
	private Integer totalEstRsp;
	private Integer sucessEstRsp;
	private Integer failEstRsp;
	
	private List<String> sessionEstablishmentFailReasonList  = new ArrayList<String>();
	private Integer totalModReq;
	private Integer sucessModReq;
	private Integer totalModRsp;
	private Integer sucessModRsp;
	private Integer failModRsp;
    private List<String> sessionModificationFailReasonList = new ArrayList<String>();
	private Integer totalDelReq;
	private Integer sucessDelReq;
	private Integer totalDelRsp;
	private Integer sucessDelRsp;
	private Integer failDelRsp;
    private List<String> sessionDeletionFailReasonList = new ArrayList<String>();

	public Integer getTotal_cp_assoc_req() {
		return total_cp_assoc_req;
	}

	public void setTotal_cp_assoc_req(Integer total_cp_assoc_req) {
		this.total_cp_assoc_req = total_cp_assoc_req;
	}

	public Integer getTotal_cp_assoc_resp() {
		return total_cp_assoc_resp;
	}

	public void setTotal_cp_assoc_resp(Integer total_cp_assoc_resp) {
		this.total_cp_assoc_resp = total_cp_assoc_resp;
	}

	public Integer getTotalEstReq() {
		return totalEstReq;
	}

	public void setTotalEstReq(Integer totalEstReq) {
		this.totalEstReq = totalEstReq;
	}

	public Integer getSucessEstReq() {
		return sucessEstReq;
	}

	public void setSucessEstReq(Integer sucessEstReq) {
		this.sucessEstReq = sucessEstReq;
	}

	public Integer getTotalEstRsp() {
		return totalEstRsp;
	}

	public void setTotalEstRsp(Integer totalEstRsp) {
		this.totalEstRsp = totalEstRsp;
	}

	public Integer getSucessEstRsp() {
		return sucessEstRsp;
	}

	public void setSucessEstRsp(Integer sucessEstRsp) {
		this.sucessEstRsp = sucessEstRsp;
	}

	public Integer getFailEstRsp() {
		return failEstRsp;
	}

	public void setFailEstRsp(Integer failEstRsp) {
		this.failEstRsp = failEstRsp;
	}

	public Integer getTotalModReq() {
		return totalModReq;
	}

	public void setTotalModReq(Integer totalModReq) {
		this.totalModReq = totalModReq;
	}

	public Integer getSucessModReq() {
		return sucessModReq;
	}

	public void setSucessModReq(Integer sucessModReq) {
		this.sucessModReq = sucessModReq;
	}

	public Integer getTotalModRsp() {
		return totalModRsp;
	}

	public void setTotalModRsp(Integer totalModRsp) {
		this.totalModRsp = totalModRsp;
	}

	public Integer getSucessModRsp() {
		return sucessModRsp;
	}

	public void setSucessModRsp(Integer sucessModRsp) {
		this.sucessModRsp = sucessModRsp;
	}

	public Integer getFailModRsp() {
		return failModRsp;
	}

	public void setFailModRsp(Integer failModRsp) {
		this.failModRsp = failModRsp;
	}

	public Integer getTotalDelReq() {
		return totalDelReq;
	}

	public void setTotalDelReq(Integer totalDelReq) {
		this.totalDelReq = totalDelReq;
	}

	public Integer getSucessDelReq() {
		return sucessDelReq;
	}

	public void setSucessDelReq(Integer sucessDelReq) {
		this.sucessDelReq = sucessDelReq;
	}

	public Integer getTotalDelRsp() {
		return totalDelRsp;
	}

	public void setTotalDelRsp(Integer totalDelRsp) {
		this.totalDelRsp = totalDelRsp;
	}

	public Integer getSucessDelRsp() {
		return sucessDelRsp;
	}

	public void setSucessDelRsp(Integer sucessDelRsp) {
		this.sucessDelRsp = sucessDelRsp;
	}

	public Integer getFailDelRsp() {
		return failDelRsp;
	}

	public void setFailDelRsp(Integer failDelRsp) {
		this.failDelRsp = failDelRsp;
	}

	

	public List<String> getSessionEstablishmentFailReasonList() {
		return sessionEstablishmentFailReasonList;
	}

	public void setSessionEstablishmentFailReasonList(List<String> sessionEstablishmentFailReasonList) {
		this.sessionEstablishmentFailReasonList = sessionEstablishmentFailReasonList;
	}

	

	

	public List<String> getSessionModificationFailReasonList() {
		return sessionModificationFailReasonList;
	}

	public void setSessionModificationFailReasonList(List<String> sessionModificationFailReasonList) {
		this.sessionModificationFailReasonList = sessionModificationFailReasonList;
	}

	public List<String> getSessionDeletionFailReasonList() {
		return sessionDeletionFailReasonList;
	}

	public void setSessionDeletionFailReasonList(List<String> sessionDeletionFailReasonList) {
		this.sessionDeletionFailReasonList = sessionDeletionFailReasonList;
	}

	public PfcpInfoRootDto() {
		super();
	}

	

}
