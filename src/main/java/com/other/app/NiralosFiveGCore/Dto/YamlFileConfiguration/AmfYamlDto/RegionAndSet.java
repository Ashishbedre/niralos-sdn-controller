package com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.AmfYamlDto;

public class RegionAndSet {


	public Integer region;
	public Integer set;
	public Integer getRegion() {
		return region;
	}
	public void setRegion(Integer region) {
		this.region = region;
	}
	public Integer getSet() {
		return set;
	}
	public void setSet(Integer set) {
		this.set = set;
	}
	public RegionAndSet(Integer region, Integer set) {
		super();
		this.region = region;
		this.set = set;
	}
	public RegionAndSet() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "RegionAndSet [region=" + region + ", set=" + set + "]";
	}
	
	
	
}
