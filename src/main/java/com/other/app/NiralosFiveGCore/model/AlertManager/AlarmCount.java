package com.other.app.NiralosFiveGCore.model.AlertManager;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AlarmCount {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Long id;
    private long count;

	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}

	
    

}
