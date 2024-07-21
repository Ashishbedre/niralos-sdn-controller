package com.other.app.niralos_edge.Service.VmModification;

import org.springframework.http.ResponseEntity;

import com.other.app.niralos_edge.dto.VmUpdateDto;

public interface VmModificationService {

	public ResponseEntity<String> updateVm(VmUpdateDto updateDto,String edgeClientId);
}
