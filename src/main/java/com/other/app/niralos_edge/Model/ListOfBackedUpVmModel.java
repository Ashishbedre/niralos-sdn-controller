package com.other.app.niralos_edge.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "list_of_vm_backup")
public class ListOfBackedUpVmModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "ctime")
	private String ctime;
	
	@Column(name = "size")
	private String size;
	
	@Column(name = "vmId")
	private String vmId;
	
	@Column(name = "volId")
	private String volId;
	

	public ListOfBackedUpVmModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ListOfBackedUpVmModel(String ctime, String size, String vmId, String volId) {
		super();
		this.ctime = ctime;
		this.size = size;
		this.vmId = vmId;
		this.volId = volId;
	}

	public String getCtime() {
		return ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getVmId() {
		return vmId;
	}

	public void setVmId(String vmId) {
		this.vmId = vmId;
	}

	public String getVolId() {
		return volId;
	}

	public void setVolId(String volId) {
		this.volId = volId;
	}
	
	

}
