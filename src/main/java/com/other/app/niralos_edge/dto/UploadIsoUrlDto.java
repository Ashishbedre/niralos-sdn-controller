package com.other.app.niralos_edge.dto;

public class UploadIsoUrlDto {
	
	private String url;
	
	private String filename;
	
	private String content;

	public UploadIsoUrlDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UploadIsoUrlDto(String url, String filename, String content) {
		super();
		this.url = url;
		this.filename = filename;
		this.content = content;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFileName() {
		return filename;
	}

	public void setFileName(String fileName) {
		this.filename = fileName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	

}
