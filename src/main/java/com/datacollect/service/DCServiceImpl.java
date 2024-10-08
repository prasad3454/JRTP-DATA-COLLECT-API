package com.datacollect.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datacollect.binding.ChildRequest;
import com.datacollect.binding.DCChildren;
import com.datacollect.binding.DCEducation;
import com.datacollect.binding.DCIncome;
import com.datacollect.binding.DCPlanSelection;
import com.datacollect.binding.DCSummary;
import com.datacollect.entity.CitizenAppEntity;
import com.datacollect.entity.DCCaseEntity;
import com.datacollect.entity.DCChildrenEntity;
import com.datacollect.entity.DCEducationEntity;
import com.datacollect.entity.DCIncomeEntity;
import com.datacollect.entity.Plan;
import com.datacollect.repository.CaseEntityRepository;
import com.datacollect.repository.ChildrenEntityRepository;
import com.datacollect.repository.CitizenAppRepository;
import com.datacollect.repository.EducationEntityRepository;
import com.datacollect.repository.IncomeEntityRepository;
import com.datacollect.repository.PlanRepo;

@Service
public class DCServiceImpl implements DCService{
	
	@Autowired
	private CitizenAppRepository citizenAppRepository;
	
	@Autowired
	private CaseEntityRepository caseEntityRepository;
	
	@Autowired
	private PlanRepo planRepo;
	
	@Autowired
	private IncomeEntityRepository incomeEntityRepository;
	
	@Autowired
	private EducationEntityRepository educationEntityRepository;
	
	@Autowired
	private ChildrenEntityRepository childrenEntityRepository;
	
	@Override
	public Long loadCaseNumber(Integer appId) {
		
		Optional<CitizenAppEntity> app = citizenAppRepository.findById(appId);
		if(app.isPresent()) {
			DCCaseEntity caseEntity = new DCCaseEntity();
			caseEntity.setAppId(appId);
			
			caseEntity = caseEntityRepository.save(caseEntity);
			return caseEntity.getCaseNo();
		}
		return 0L;
	}

	@Override
	public Map<Integer, String> getPlanNames() {
		
		List<Plan> findAll = planRepo.findAll();
		
		Map<Integer, String> plansMap = new HashMap<>();
		
		for(Plan entity : findAll) {
			plansMap.put(entity.getPlanId(), entity.getPlanName());
		}
		return plansMap;
	}

	@Override
	public Long savePlanSelection(DCPlanSelection planSelection) {
		
		Optional<DCCaseEntity> caseEntity = caseEntityRepository.findById(planSelection.getCaseNum());
		
		if(caseEntity.isPresent()) {
			DCCaseEntity entity = caseEntity.get();
			entity.setPlanId(planSelection.getPlanId());
			
			caseEntityRepository.save(entity);
			
			return planSelection.getCaseNum();
		}
		
		return null;
	}

	@Override
	public Long saveIncomeData(DCIncome income) {
		
		DCIncomeEntity incomeEntity = new DCIncomeEntity();
		BeanUtils.copyProperties(income, incomeEntity);
		incomeEntityRepository.save(incomeEntity);
		return income.getCaseNum();
		
	}

	@Override
	public Long saveEducation(DCEducation education) {
		
		DCEducationEntity educationEntity = new DCEducationEntity();
		BeanUtils.copyProperties(education, educationEntity);
		educationEntityRepository.save(educationEntity);
		return education.getCaseNum();
		
	}

	@Override
	public Long saveChildrens(ChildRequest request) {
		
		List<DCChildren> children = request.getChilds();
		Long caseNum = request.getCaseNum();
		
		for(DCChildren c : children) {
			
			DCChildrenEntity childrenEntity = new DCChildrenEntity();
			BeanUtils.copyProperties(c, childrenEntity);
			childrenEntity.setCaseNum(caseNum);
			childrenEntityRepository.save(childrenEntity);
			
		}
		
		return request.getCaseNum();
	}

	@Override
	public DCSummary getSummary(Long caseNumber) {
		
		String planName = "";
		
		DCIncomeEntity incomeEntity = incomeEntityRepository.findByCaseNum(caseNumber);
		DCEducationEntity educationEntity = educationEntityRepository.findByCaseNum(caseNumber);
		List<DCChildrenEntity> childrenEntity = childrenEntityRepository.findByCaseNum(caseNumber);
		
		Optional<DCCaseEntity> caseEntity = caseEntityRepository.findById(caseNumber);
		if(caseEntity.isPresent()) {
			Integer planId = caseEntity.get().getPlanId();
			Optional<Plan> plan = planRepo.findById(planId);
			if(plan.isPresent()) {
				planName = plan.get().getPlanName();
			}
		}
		
		DCSummary summary = new DCSummary();
		summary.setPlanName(planName);
		
		DCIncome income = new DCIncome();
		BeanUtils.copyProperties(incomeEntity, income);
		summary.setDcIncome(income);
		
		DCEducation education = new DCEducation();
		BeanUtils.copyProperties(educationEntity, education);
		summary.setDcEducation(education);
		
		List<DCChildren> childrens = new ArrayList<>();
		for(DCChildrenEntity entity: childrenEntity) {
			DCChildren ch = new DCChildren();
			BeanUtils.copyProperties(entity, ch);
			childrens.add(ch);
		}
		summary.setDcChildrens(childrens);
		
		return summary;
	}

}
