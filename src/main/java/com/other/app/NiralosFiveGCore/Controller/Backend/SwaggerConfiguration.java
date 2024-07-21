package com.other.app.NiralosFiveGCore.Controller.Backend;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	@Bean
	public Docket api() {
	return new Docket(DocumentationType.SWAGGER_2)
	.select()
	.apis(RequestHandlerSelectors.any())
	.paths(PathSelectors.any())
	.build();
	}
	 

//	   @Bean
//	    public Docket api() {
//			try {
//	        return new Docket(DocumentationType.SWAGGER_2)
//	                .select()
//	                .apis(RequestHandlerSelectors.basePackage("com.other.app.Controller")) // specify base package of your controllers
//	                .paths(PathSelectors.any())
//	                .build();
//	    }catch (Exception e) {
//			System.out.println(e);
//		}
//			return null;
//}
	   }
