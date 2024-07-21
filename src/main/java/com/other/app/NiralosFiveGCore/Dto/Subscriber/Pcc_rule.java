package com.other.app.NiralosFiveGCore.Dto.Subscriber;

import java.util.ArrayList;

public class Pcc_rule {

	ArrayList<Flow> flow;
	Qos qos;

	public ArrayList<Flow> getFlow() {
		return flow;
	}

	public void setFlow(ArrayList<Flow> flow) {
		this.flow = flow;
	}

	public Qos getQos() {
		return qos;
	}

	public void setQos(Qos qos) {
		this.qos = qos;
	}

}
