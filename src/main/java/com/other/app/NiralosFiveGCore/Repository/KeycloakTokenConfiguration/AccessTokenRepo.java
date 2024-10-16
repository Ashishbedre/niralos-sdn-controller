package com.other.app.NiralosFiveGCore.Repository.KeycloakTokenConfiguration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.other.app.NiralosFiveGCore.model.GetAccessTokenModel;


public interface AccessTokenRepo extends JpaRepository<GetAccessTokenModel, Integer> {
	
	@Query("SELECT accessToken FROM GetAccessTokenModel")
	public String getAccessTokenFromDb();

}
