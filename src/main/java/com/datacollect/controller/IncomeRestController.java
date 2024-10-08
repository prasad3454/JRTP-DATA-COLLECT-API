package com.datacollect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.datacollect.binding.DCIncome;
import com.datacollect.service.DCService;

@RestController
public class IncomeRestController {
	
	@Autowired
	private DCService service;
	
	@PostMapping("/income")
	public ResponseEntity<Long> saveIncome(@RequestBody DCIncome income) {
		Long caseNum = service.saveIncomeData(income);
		return new ResponseEntity<>(caseNum, HttpStatus.CREATED);
	}
}
