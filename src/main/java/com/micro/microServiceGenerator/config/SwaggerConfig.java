package com.micro.microServiceGenerator.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;




@Configuration
@EnableSwagger2
public class SwaggerConfig {                                    
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())  
          .select()                                  
          .apis(RequestHandlerSelectors.any())
          .paths(PathSelectors.any())                          
          .build().enable(true);                                           
    }
    
    private ApiInfo apiInfo() {
		return new ApiInfo("MicroServiceGenerator",
				"This application generates Spring boot micro services automatically.(BETA version)",
				"1.0.0", "TERMS OF SERVICE URL",
				new Contact("Pavan - For Support", " www.linkedin.com/in/pavansaragadam ", "pavan.saragadam@gmail.com"),
				"", "", Collections.emptyList()) ;
	}
}
