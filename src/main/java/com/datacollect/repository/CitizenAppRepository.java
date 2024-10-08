package com.datacollect.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.datacollect.entity.CitizenAppEntity;

public interface CitizenAppRepository extends JpaRepository<CitizenAppEntity, Serializable> {

}
