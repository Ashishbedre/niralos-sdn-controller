package com.other.app.niralos_edge.Model.graph;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="edge_memory_graph")
public class MemoryGraphModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="id")
	private Long id;
	@Column(name="edge_client_id")
	private String edgeClientId;
	@Column(name="memory_value")
	private Double memoryValue;
	@Column(name="date")
	private LocalDateTime date;
	
	public String getEdgeClientId() {
		return edgeClientId;
	}
	public void setEdgeClientId(String edgeClientId) {
		this.edgeClientId = edgeClientId;
	}
	public Double getMemoryValue() {
		return memoryValue;
	}
	public void setMemoryValue(Double memoryValue) {
		this.memoryValue = memoryValue;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
	
	public MemoryGraphModel(String edgeClientId, Double memoryValue, LocalDateTime date) {
		super();
		this.edgeClientId = edgeClientId;
		this.memoryValue = memoryValue;
		this.date = date;
	}
	
	
	public MemoryGraphModel() {
		super();
	}
	
	
	
	
}
