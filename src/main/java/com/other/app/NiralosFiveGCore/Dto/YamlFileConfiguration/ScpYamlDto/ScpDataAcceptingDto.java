package com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.ScpYamlDto;

import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.AmfYamlDto.SbiData;
import com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.AmfYamlDto.t3512;

public class ScpDataAcceptingDto {

	private String db_uri;
	private SbiData sbi;
	private SbiDataScpDto scp;
	private NrfSbiDto nrf;
	private String parameter;
	private String max;
	private t3512 time;
	public ScpDataAcceptingDto(String db_uri, SbiData sbi, SbiDataScpDto scp, NrfSbiDto nrf, String parameter,
			String max, t3512 time) {
		super();
		this.db_uri = db_uri;
		this.sbi = sbi;
		this.scp = scp;
		this.nrf = nrf;
		this.parameter = parameter;
		this.max = max;
		this.time = time;
	}
	public ScpDataAcceptingDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getDb_uri() {
		return db_uri;
	}
	public void setDb_uri(String db_uri) {
		this.db_uri = db_uri;
	}
	public SbiData getSbi() {
		return sbi;
	}
	public void setSbi(SbiData sbi) {
		this.sbi = sbi;
	}
	public SbiDataScpDto getScp() {
		return scp;
	}
	public void setScp(SbiDataScpDto scp) {
		this.scp = scp;
	}
	public NrfSbiDto getNrf() {
		return nrf;
	}
	public void setNrf(NrfSbiDto nrf) {
		this.nrf = nrf;
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
	public t3512 getTime() {
		return time;
	}
	public void setTime(t3512 time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "ScpDataAcceptingDto [db_uri=" + db_uri + ", sbi=" + sbi + ", scp=" + scp + ", nrf=" + nrf
				+ ", parameter=" + parameter + ", max=" + max + ", time=" + time + "]";
	}
	
	
	

	
}
