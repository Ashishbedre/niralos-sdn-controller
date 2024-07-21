package com.other.app.NiralosFiveGCore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "api_token")
public class GetAccessTokenModel {
	
	@Id
	@Column(name="id")
	private int id;
	@Column(name="access_token")
	@Lob
	private String accessToken;

	public GetAccessTokenModel(int id, String accessToken) {
		super();
		this.id = id;
		this.accessToken = accessToken;
	}

	public GetAccessTokenModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

}
