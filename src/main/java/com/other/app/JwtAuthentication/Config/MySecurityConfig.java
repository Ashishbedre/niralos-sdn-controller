package com.other.app.JwtAuthentication.Config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import com.other.app.JwtAuthentication.Service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class MySecurityConfig {
//	implements Filter

	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private JwtAuthenticationFilter authenticationFilter;
	
	
	@Autowired
	private JwtAuthenticationEntryPoint authenticationEntryPoint;
	
	

	
	
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// which api want to auth..
    	 CorsConfiguration corsConfiguration = new CorsConfiguration();
         corsConfiguration.setAllowedHeaders(List.of("*"));
         corsConfiguration.setAllowedOriginPatterns(List.of("*"));
         corsConfiguration.setAllowedMethods(List.of("*"));
         corsConfiguration.setAllowCredentials(true);
         corsConfiguration.setExposedHeaders(List.of("Authorization"));

		http
		.csrf().disable()
		.cors().configurationSource(request -> corsConfiguration).and()
		.authorizeRequests()
		  .antMatchers("/css/**", "/fonts/**", "/images/**","/js/**","/scss/**").permitAll() // Permit access to static resources
		
		.antMatchers("/register").permitAll()
		.antMatchers("/token").permitAll()
		.antMatchers("/refreshtoken").permitAll()
		.antMatchers("/index/**").permitAll()
		.antMatchers("/settings/**").permitAll()
		.antMatchers("/").permitAll()
		//Enabling feature for Development purpose Not for Production
//		.antMatchers("/swagger-ui/**").permitAll()
//		.antMatchers("/v3/api-docs/**").permitAll()
		
		.antMatchers("/edge/**").hasRole("NORMAL")
		.antMatchers("/userdetails/**").hasRole("NORMAL")
		.antMatchers("/change-password/**").hasRole("NORMAL")
		.antMatchers("/alerts_manager/**").hasRole("NORMAL")
		.antMatchers("/5gcoredata/**").hasRole("NORMAL")
		.antMatchers("/5gcore/**").hasRole("NORMAL")
		.antMatchers("/logData/**").hasRole("NORMAL")
		.antMatchers("/5gcore/**").hasRole("NORMAL")
		.antMatchers("/5gcore/**").hasRole("NORMAL")
		.antMatchers("/5gcoredeployment/**").hasRole("NORMAL")
		
		.antMatchers("/admin/**").hasRole("ADMIN")
		.anyRequest().authenticated()
		
		
		//if any exception occurs
		.and().exceptionHandling()
		.authenticationEntryPoint(authenticationEntryPoint)
		
//		.authenticationEntryPoint(unauthorizedHandler)
		
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class); 
		return http.build();
	}
 
    
	
	 @Bean
	    public AuthenticationManager authenticationManagerBean(HttpSecurity http) throws Exception {
	        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
	        authenticationManagerBuilder.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
	        return authenticationManagerBuilder.build();
	    }
    
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder(10);
		
	}




	
	

}
