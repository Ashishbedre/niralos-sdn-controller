package com.other.app.NiralosFiveGCore.Dto.UeStacDto;

import java.util.List;


public class UeFileListDto {
	
	 private List<FileListNameDto> fileList;
	 private Integer no_of_remaining_files;
	private Boolean files_remaining_flag;

	public Integer getNo_of_remaining_files() {
		return no_of_remaining_files;
	}

	public void setNo_of_remaining_files(Integer no_of_remaining_files) {
		this.no_of_remaining_files = no_of_remaining_files;
	}

	public Boolean getFiles_remaining_flag() {
		return files_remaining_flag;
	}

	public void setFiles_remaining_flag(Boolean files_remaining_flag) {
		this.files_remaining_flag = files_remaining_flag;
	}

	public List<FileListNameDto> getFileList() {
		return fileList;
	}

	public void setFileList(List<FileListNameDto> fileList) {
		this.fileList = fileList;
	}


}
