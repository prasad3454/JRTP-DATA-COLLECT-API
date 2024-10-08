package com.datacollect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.datacollect.binding.ChildRequest;
import com.datacollect.binding.DCSummary;
import com.datacollect.service.DCService;

@RestController
public class ChildRestController {
	
	@Autowired
	private DCService service;
	
	@PostMapping("/childrens")
	public ResponseEntity<DCSummary> saveChildren(@RequestBody ChildRequest request) {
		
		Long caseNum = service.saveChildrens(request);
		DCSummary summary = service.getSummary(caseNum);
		return new ResponseEntity<>(summary, HttpStatus.OK);
		
	}
}
