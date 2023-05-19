package com.lexisnexis.hiring.repository;

import com.lexisnexis.hiring.entity.Employee;
import com.lexisnexis.hiring.entity.Requisition;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RequisitionRepository extends JpaRepository<Requisition ,Integer> {

	public Requisition findByjobId(int jobId);
	
	public List<Requisition> findByManager(Employee byid);
}
