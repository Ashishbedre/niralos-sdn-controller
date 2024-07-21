package com.other.app.niralos_edge.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="hypervisor_task_log")
public class HypervisorTaskLogModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="uid")
	private Long uId;
	@Column(name="user")
	private String user;
	@Column(name="starttime")
	private Long starttime;
	@Column(name="endtime")
	private Long endtime;
	@Column(name="status")
	private String status;
	@Column(name="type")
	private String type;
	@Column(name="tokenid")
	private String tokenid;
	@Column(name="upid")
	private String upid;
	@Column(name="node")
	private String node;
	@Column(name="saved")
	private String saved;
	@Column(name="id")
	private String id;
	@Column(name="edge_client_id")
	private String edgeClientId;
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public Long getStarttime() {
		return starttime;
	}
	public void setStarttime(Long starttime) {
		this.starttime = starttime;
	}
	public Long getEndtime() {
		return endtime;
	}
	public void setEndtime(Long endtime) {
		this.endtime = endtime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTokenid() {
		return tokenid;
	}
	public void setTokenid(String tokenid) {
		this.tokenid = tokenid;
	}
	public String getUpid() {
		return upid;
	}
	public void setUpid(String upid) {
		this.upid = upid;
	}
	public String getNode() {
		return node;
	}
	public void setNode(String node) {
		this.node = node;
	}
	public String getSaved() {
		return saved;
	}
	public void setSaved(String saved) {
		this.saved = saved;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEdgeClientId() {
		return edgeClientId;
	}
	public void setEdgeClientId(String edgeClientId) {
		this.edgeClientId = edgeClientId;
	}
	
	public HypervisorTaskLogModel(String user, Long starttime, Long endtime, String status, String type, String tokenid,
			String upid, String node, String saved, String id, String edgeClientId) {
		super();
		this.user = user;
		this.starttime = starttime;
		this.endtime = endtime;
		this.status = status;
		this.type = type;
		this.tokenid = tokenid;
		this.upid = upid;
		this.node = node;
		this.saved = saved;
		this.id = id;
		this.edgeClientId = edgeClientId;
	}
	public HypervisorTaskLogModel() {
		super();
	}
	
	
	
}
