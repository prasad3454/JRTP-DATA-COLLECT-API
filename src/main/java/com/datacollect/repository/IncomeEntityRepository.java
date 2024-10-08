package com.datacollect.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.datacollect.entity.DCIncomeEntity;

public interface IncomeEntityRepository extends JpaRepository<DCIncomeEntity, Serializable>{
	
	public DCIncomeEntity findByCaseNum(Long caseNum);
}
