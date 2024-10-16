package com.other.app.NiralosFiveGCore.model.Slice;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.ArrayList;

@Document(collection = "SliceConfigration")
public class SliceConfigrationModel {
    @MongoId
    String _id;
    @Indexed(unique = true)
    String sliceId;
    @Indexed(unique = true)
    Integer sst;
    String sd;
    Boolean default_indicator;
    ArrayList<Session> session;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Integer getSst() {
        return sst;
    }

    public void setSst(Integer sst) {
        this.sst = sst;
    }

    public String getSd() {
        return sd;
    }

    public void setSd(String sd) {
        this.sd = sd;
    }

    public Boolean getDefault_indicator() {
        return default_indicator;
    }

    public void setDefault_indicator(Boolean default_indicator) {
        this.default_indicator = default_indicator;
    }

    public ArrayList<Session> getSession() {
        return session;
    }

    public void setSession(ArrayList<Session> session) {
        this.session = session;
    }



	public String getSliceId() {
		return sliceId;
	}

	public void setSliceId(String sliceId) {
		this.sliceId = sliceId;
	}

	

	public SliceConfigrationModel(String sliceId, Integer sst, String sd, Boolean default_indicator,
			ArrayList<Session> session) {
		super();
		this.sliceId = sliceId;
		this.sst = sst;
		this.sd = sd;
		this.default_indicator = default_indicator;
		this.session = session;
	}

	public SliceConfigrationModel() {
		super();
		// TODO Auto-generated constructor stub
	}
    
}
