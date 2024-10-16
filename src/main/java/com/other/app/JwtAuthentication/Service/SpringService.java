package com.other.app.JwtAuthentication.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.other.app.JwtAuthentication.Dto.ListofUser;
import com.other.app.JwtAuthentication.Model.JwtRequest;


public interface SpringService {
	public List<ListofUser> getAllUsers();
	public ResponseEntity<?>  deleteUsers(String username,String email, String password);
	public void registerService(JwtRequest jwtRequest);


	
}
