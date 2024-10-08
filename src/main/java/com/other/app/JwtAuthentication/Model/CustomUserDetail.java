package com.other.app.JwtAuthentication.Model;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;




public class CustomUserDetail implements UserDetails {


	private JwtRequest model;

	public CustomUserDetail(JwtRequest model) {
		super();
		this.model = model;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		
		HashSet<SimpleGrantedAuthority> set = new HashSet<>();
		set.add(new SimpleGrantedAuthority(this.model.getRole()));
		
		return set;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.model.getPassword();
		
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.model.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
  
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
}
