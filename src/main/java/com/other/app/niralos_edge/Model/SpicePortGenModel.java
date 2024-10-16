package com.other.app.niralos_edge.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "spice_port_auto")
public class SpicePortGenModel {

    @Id
	@Column(name = "id")
    private Long id;
    
    @Column(name = "number")
    private int number;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public SpicePortGenModel(Long id, int number) {
		super();
		this.id = id;
		this.number = number;
	}

	public SpicePortGenModel() {
		super();
	}

    
}

