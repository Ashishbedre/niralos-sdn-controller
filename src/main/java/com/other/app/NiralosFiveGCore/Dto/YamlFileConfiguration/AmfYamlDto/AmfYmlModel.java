package com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.AmfYamlDto;

public class AmfYmlModel {
	private file_stat logger;
	private SbiData sbi;
	private AmfAttributeDateModel amf;
	private SbiDataModel scp;
	private String parameter;
	private String max;
	private String usrsctp;
	private t3512 time;
	public AmfYmlModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AmfYmlModel(file_stat logger, SbiData sbi, AmfAttributeDateModel amf, SbiDataModel scp, String parameter,
			String max, String usrsctp, t3512 time) {
		super();
		this.logger = logger;
		this.sbi = sbi;
		this.amf = amf;
		this.scp = scp;
		this.parameter = parameter;
		this.max = max;
		this.usrsctp = usrsctp;
		this.time = time;
	}
	public file_stat getLogger() {
		return logger;
	}
	public void setLogger(file_stat logger) {
		this.logger = logger;
	}
	public SbiData getSbi() {
		return sbi;
	}
	public void setSbi(SbiData sbi) {
		this.sbi = sbi;
	}
	public AmfAttributeDateModel getAmf() {
		return amf;
	}
	public void setAmf(AmfAttributeDateModel amf) {
		this.amf = amf;
	}
	public SbiDataModel getScp() {
		return scp;
	}
	public void setScp(SbiDataModel scp) {
		this.scp = scp;
	}
	public String getParameter() {
		return parameter;
	}
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
	public String getMax() {
		return max;
	}
	public void setMax(String max) {
		this.max = max;
	}
	public String getUsrsctp() {
		return usrsctp;
	}
	public void setUsrsctp(String usrsctp) {
		this.usrsctp = usrsctp;
	}
	public t3512 getTime() {
		return time;
	}
	public void setTime(t3512 time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "AmfYmlModel [logger=" + logger + ", sbi=" + sbi + ", amf=" + amf + ", scp=" + scp + ", parameter="
				+ parameter + ", max=" + max + ", usrsctp=" + usrsctp + ", time=" + time + "]";
	}
	

}
