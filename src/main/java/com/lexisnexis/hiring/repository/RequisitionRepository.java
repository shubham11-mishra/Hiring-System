package com.lexisnexis.hiring.repository;

import com.lexisnexis.hiring.entity.Employee;
import com.lexisnexis.hiring.entity.Requisition;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequisitionRepository extends JpaRepository<Requisition ,Integer> {
	public List<Requisition> findByManager(Employee byid);
	Requisition findByJobId(int jobId);
}
