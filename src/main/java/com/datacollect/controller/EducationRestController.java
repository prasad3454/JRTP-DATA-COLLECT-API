package com.datacollect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.datacollect.binding.DCEducation;
import com.datacollect.service.DCService;

@RestController
public class EducationRestController {
	
	@Autowired
	private DCService service;
	
	@PostMapping("/education")
	public ResponseEntity<Long> saveEducation(@RequestBody DCEducation education) {
		Long caseNum = service.saveEducation(education);
		return new ResponseEntity<>(caseNum, HttpStatus.CREATED);
	}
}
