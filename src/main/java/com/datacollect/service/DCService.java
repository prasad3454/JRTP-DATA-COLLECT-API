package com.datacollect.service;

import java.util.Map;

import com.datacollect.binding.ChildRequest;
import com.datacollect.binding.DCEducation;
import com.datacollect.binding.DCIncome;
import com.datacollect.binding.DCPlanSelection;
import com.datacollect.binding.DCSummary;

public interface DCService {
	
	public Long loadCaseNumber(Integer appId);
	
	public Map<Integer, String> getPlanNames();
	
	public Long savePlanSelection(DCPlanSelection planSelection);
	
	public Long saveIncomeData(DCIncome income);
	
	public Long saveEducation(DCEducation education);
	
	public Long saveChildrens(ChildRequest request);
	
	public DCSummary getSummary(Long caseNumber);
	
}
