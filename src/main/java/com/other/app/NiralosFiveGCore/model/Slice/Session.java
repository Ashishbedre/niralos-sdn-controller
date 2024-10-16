package com.other.app.NiralosFiveGCore.model.Slice;

import java.util.ArrayList;

import com.other.app.NiralosFiveGCore.Dto.Subscriber.Ambr;
import com.other.app.NiralosFiveGCore.Dto.Subscriber.Pcc_rule;
import com.other.app.NiralosFiveGCore.Dto.Subscriber.Session_qos;
import com.other.app.NiralosFiveGCore.Dto.Subscriber.Smf;
import com.other.app.NiralosFiveGCore.Dto.Subscriber.Ue;

public class Session {
	String name;
	String apn_id;
	Integer type;
	Session_qos qos;
	Ambr ambr;
	Ue ue;
	Smf smf;
	ArrayList<Pcc_rule> pcc_rule;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApn_id() {
        return apn_id;
    }

    public void setApn_id(String apn_id) {
        this.apn_id = apn_id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Session_qos getQos() {
        return qos;
    }

    public void setQos(Session_qos qos) {
        this.qos = qos;
    }

    public Ambr getAmbr() {
        return ambr;
    }

    public void setAmbr(Ambr ambr) {
        this.ambr = ambr;
    }

    public Ue getUe() {
        return ue;
    }

    public void setUe(Ue ue) {
        this.ue = ue;
    }

    public Smf getSmf() {
        return smf;
    }

    public void setSmf(Smf smf) {
        this.smf = smf;
    }

    public ArrayList<Pcc_rule> getPcc_rule() {
        return pcc_rule;
    }

    public void setPcc_rule(ArrayList<Pcc_rule> pcc_rule) {
        this.pcc_rule = pcc_rule;
    }
}
