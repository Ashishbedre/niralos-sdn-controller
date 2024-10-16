package com.other.app.niralos_edge.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "network_interface")
public class VmNetworkStatsModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Long id;

	@Column(name="vm_id")
	private Long vmId;
	
	@Column(name="vm_name")
	private String vmName;
	
	@Column(name="net0")
	private String net0;
	
	@Column(name="net1")
	private String net1;
	
	@Column(name="net2")
	private String net2;
	
	@Column(name="net3")
	private String net3;
	
	@Column(name="net4")
	private String net4;
	
	@Column(name="net5")
	private String net5;
	
	@Column(name="net6")
	private String net6;
	
	@Column(name="net7")
	private String net7;
	
	@Column(name="net8")
	private String net8;
	
	@Column(name="net9")
	private String net9;
	
	@Column(name="net10")
	private String net10;
	
	@Column(name="edge_client_id")
	private String edgeClientId;

	public Long getVmId() {
		return vmId;
	}

	public void setVmId(Long vmId) {
		this.vmId = vmId;
	}

	public String getVmName() {
		return vmName;
	}

	public void setVmName(String vmName) {
		this.vmName = vmName;
	}

	public String getNet0() {
		return net0;
	}

	public void setNet0(String net0) {
		this.net0 = net0;
	}

	public String getNet1() {
		return net1;
	}

	public void setNet1(String net1) {
		this.net1 = net1;
	}

	public String getNet2() {
		return net2;
	}

	public void setNet2(String net2) {
		this.net2 = net2;
	}

	public String getNet3() {
		return net3;
	}

	public void setNet3(String net3) {
		this.net3 = net3;
	}

	public String getNet4() {
		return net4;
	}

	public void setNet4(String net4) {
		this.net4 = net4;
	}

	public String getNet5() {
		return net5;
	}

	public void setNet5(String net5) {
		this.net5 = net5;
	}

	public String getNet6() {
		return net6;
	}

	public void setNet6(String net6) {
		this.net6 = net6;
	}

	public String getNet7() {
		return net7;
	}

	public void setNet7(String net7) {
		this.net7 = net7;
	}

	public String getNet8() {
		return net8;
	}

	public void setNet8(String net8) {
		this.net8 = net8;
	}

	public String getNet9() {
		return net9;
	}

	public void setNet9(String net9) {
		this.net9 = net9;
	}

	public String getNet10() {
		return net10;
	}

	public void setNet10(String net10) {
		this.net10 = net10;
	}

	public String getEdgeClientId() {
		return edgeClientId;
	}

	public void setEdgeClientId(String edgeClientId) {
		this.edgeClientId = edgeClientId;
	}

	public VmNetworkStatsModel(Long vmId, String vmName, String net0, String net1, String net2, String net3,
			String net4, String net5, String net6, String net7, String net8, String net9, String net10,
			String edgeClientId) {
		super();
		this.vmId = vmId;
		this.vmName = vmName;
		this.net0 = net0;
		this.net1 = net1;
		this.net2 = net2;
		this.net3 = net3;
		this.net4 = net4;
		this.net5 = net5;
		this.net6 = net6;
		this.net7 = net7;
		this.net8 = net8;
		this.net9 = net9;
		this.net10 = net10;
		this.edgeClientId = edgeClientId;
	}

	public VmNetworkStatsModel() {
		super();
	}

	
	
	
	

	

	

	

	

	
	
}
