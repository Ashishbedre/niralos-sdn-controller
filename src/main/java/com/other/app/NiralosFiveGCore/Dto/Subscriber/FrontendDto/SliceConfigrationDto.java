package com.other.app.NiralosFiveGCore.Dto.Subscriber.FrontendDto;

import com.other.app.NiralosFiveGCore.model.Slice.Session;
import com.other.app.NiralosFiveGCore.model.Slice.SliceConfigrationModel;

import java.util.ArrayList;

public class SliceConfigrationDto {
	   String sliceId;
        Integer sst;
        String sd;
        Boolean default_indicator;
        ArrayList<Session> session;

        public SliceConfigrationDto() {
			super();
			// TODO Auto-generated constructor stub
		}

        
		// Constructor to map from model to dto
        public SliceConfigrationDto(SliceConfigrationModel model) {
        	this.sliceId =model.getSliceId();
            this.sst = model.getSst();
            this.sd = model.getSd();
            this.default_indicator = model.getDefault_indicator();
            this.session = model.getSession();
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
        
    }