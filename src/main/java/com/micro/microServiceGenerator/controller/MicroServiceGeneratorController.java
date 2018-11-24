package com.micro.microServiceGenerator.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.micro.microServiceGenerator.model.AutoGenerateRequest;
import com.micro.microServiceGenerator.service.AutoGeneratorService;

@RestController
@RequestMapping("/autoGen")
public class MicroServiceGeneratorController {
	
	@Autowired
	AutoGeneratorService autoGeneratorService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody String createUC() {
		return "home";
	}
	
	@RequestMapping(value = "/newService", method = RequestMethod.POST, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public String createUC(@RequestBody AutoGenerateRequest autoGenerateRequest, HttpServletResponse response) {
		autoGeneratorService.generateService(autoGenerateRequest, response);
		return "success";
	}

}
