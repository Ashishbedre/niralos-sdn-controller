package com.other.app.NiralosFiveGCore.Dto.UeHistoryDto;
import java.util.List;

import com.other.app.NiralosFiveGCore.Dto.UeStacDto.FileListNameDto;

public class FileRecordApi {
	
	 private List<FileListNameDto> fileList;

	    public List<FileListNameDto> getFileList() {
	        return fileList;
	    }

	    public void setFileList(List<FileListNameDto> fileList) {
	        this.fileList = fileList;
	    }
}
