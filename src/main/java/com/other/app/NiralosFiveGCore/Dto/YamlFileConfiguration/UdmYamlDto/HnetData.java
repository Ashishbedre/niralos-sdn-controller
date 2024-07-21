package com.other.app.NiralosFiveGCore.Dto.YamlFileConfiguration.UdmYamlDto;

public class HnetData {

	public Integer id;
	public Integer scheme;
	public String key;
	public HnetData(Integer id, Integer scheme, String key) {
		super();
		this.id = id;
		this.scheme = scheme;
		this.key = key;
	}
	public HnetData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getScheme() {
		return scheme;
	}
	public void setScheme(Integer scheme) {
		this.scheme = scheme;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	@Override
	public String toString() {
		return "HnetData [id=" + id + ", scheme=" + scheme + ", key=" + key + "]";
	}
	
	

}
