package com.micro.microServiceGenerator.config;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.micro.microServiceGenerator.MicroServiceGeneratorApplication;

public class ServletInit extends SpringBootServletInitializer {
 
	@Override
	public SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(MicroServiceGeneratorApplication.class);
	}
}
