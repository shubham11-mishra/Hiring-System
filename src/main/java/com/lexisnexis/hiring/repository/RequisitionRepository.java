package com.lexisnexis.hiring.repository;

import com.lexisnexis.hiring.entity.Employee;
import com.lexisnexis.hiring.entity.Requisition;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RequisitionRepository extends JpaRepository<Requisition ,Integer> {
	public List<Requisition> findByManager(Employee byid);
	Requisition findByJobId(int jobId);

	@Query(value = "SELECT * FROM requisition_table  WHERE manager_employee_id = ?1 AND status IN ('open','freeze')", nativeQuery = true)
	public List<Requisition> findByManagerOrStatus(int managerId);
}
