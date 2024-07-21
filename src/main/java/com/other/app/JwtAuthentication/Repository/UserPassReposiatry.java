package com.other.app.JwtAuthentication.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.other.app.JwtAuthentication.Model.JwtRequest;


public interface UserPassReposiatry extends JpaRepository<JwtRequest, String>{
	public JwtRequest findByUsername(String username);

	@Query(value="SELECT * FROM jwt_request",nativeQuery = true)
	public List<JwtRequest> findUser();

	@Modifying
	@Transactional
	@Query(value="DELETE FROM jwt_request WHERE username=?1 AND email=?2",nativeQuery = true)
	public void deleteuser(String username, String email);
	
	@Query(value="SELECT COUNT(*) FROM  jwt_request WHERE username= 'niral'",nativeQuery = true)
	public Integer findUserInitialService();
}
