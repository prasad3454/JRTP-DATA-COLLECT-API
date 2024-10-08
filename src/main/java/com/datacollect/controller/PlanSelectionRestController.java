package com.datacollect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.datacollect.binding.DCPlanSelection;
import com.datacollect.service.DCService;

@RestController
public class PlanSelectionRestController {
	
	@Autowired
	private DCService service;
	
	@PostMapping("/plansel")
	public ResponseEntity<Long> planSelection(@RequestBody DCPlanSelection planSelection) {
		Long caseNum = service.savePlanSelection(planSelection);
		return new ResponseEntity<>(caseNum, HttpStatus.CREATED);
	}
}
