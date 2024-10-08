package com.datacollect.binding;

import java.util.List;

import lombok.Data;

@Data
public class DCSummary {
	
	private DCIncome dcIncome;
	
	private List<DCChildren> dcChildrens;
	
	private DCEducation dcEducation;
	
	private String planName;
}
