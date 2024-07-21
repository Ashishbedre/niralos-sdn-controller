package com.other.app.niralos_edge.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hypervisor_log")
public class HypervisorLogModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "format")
    private String log;
	
	@Column(name="edge_client_id")
	private String edgeClientId;

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	public String getEdgeClientId() {
		return edgeClientId;
	}

	public void setEdgeClientId(String edgeClientId) {
		this.edgeClientId = edgeClientId;
	}

	public HypervisorLogModel(String log, String edgeClientId) {
		super();
		this.log = log;
		this.edgeClientId = edgeClientId;
	}

	public HypervisorLogModel() {
		super();
	}

	
	
	

}
