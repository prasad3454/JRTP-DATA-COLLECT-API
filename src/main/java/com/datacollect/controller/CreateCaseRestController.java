package com.datacollect.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.datacollect.binding.CreateCaseResponse;
import com.datacollect.service.DCService;

@RestController
public class CreateCaseRestController {
	
	@Autowired
	private DCService service;
	
	@GetMapping("/case/{appId}")
	public ResponseEntity<CreateCaseResponse> createCase(@PathVariable Integer appId) {
		
		Long caseNum = service.loadCaseNumber(appId);
		
		Map<Integer, String> plansMap = service.getPlanNames();
		
		CreateCaseResponse caseResponse = new CreateCaseResponse();
		caseResponse.setCaseNum(caseNum);
		caseResponse.setPlanNames(plansMap);
		
		return new ResponseEntity<>(caseResponse, HttpStatus.OK);
	}
}
