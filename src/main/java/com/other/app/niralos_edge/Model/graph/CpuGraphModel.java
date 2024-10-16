package com.other.app.niralos_edge.Model.graph;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="edge_cpu_graph")
public class CpuGraphModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="id")
	private Long id;
	@Column(name="edge_client_id")
	private String edgeClientid;
	@Column(name="cpu_value")
	private Double cpuValue;
	@Column(name="date_time")
	private LocalDateTime date;
	
	public String getEdgeClientid() {
		return edgeClientid;
	}
	public void setEdgeClientid(String edgeClientid) {
		this.edgeClientid = edgeClientid;
	}
	public Double getCpuValue() {
		return cpuValue;
	}
	public void setCpuValue(Double cpuValue) {
		this.cpuValue = cpuValue;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
	public CpuGraphModel(String edgeClientid, Double cpuValue, LocalDateTime date) {
		super();
		this.edgeClientid = edgeClientid;
		this.cpuValue = cpuValue;
		this.date = date;
	}
	public CpuGraphModel() {
		super();
	}
	
	
	
}
