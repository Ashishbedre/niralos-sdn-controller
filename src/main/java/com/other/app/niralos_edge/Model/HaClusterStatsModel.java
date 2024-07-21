package com.other.app.niralos_edge.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ha_cluster_stats")
public class HaClusterStatsModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "sid")
	private String sid;
	
	@Column(name = "state")
	private String state;
	

	public HaClusterStatsModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HaClusterStatsModel(String sid, String state) {
		super();
		this.sid = sid;
		this.state = state;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	

}