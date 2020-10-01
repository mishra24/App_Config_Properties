package com.configclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigClientController {
	
	@Value("${app.name}")
	public String myaApp;
	
//	@GetMapping("/getinfo")
	@RequestMapping("/getinfo")
	public String getInfo() {
		
		return myaApp;
		
	}

}
