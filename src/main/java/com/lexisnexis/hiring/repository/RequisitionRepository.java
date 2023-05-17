package com.lexisnexis.hiring.repository;

import com.lexisnexis.hiring.entity.Requisition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequisitionRepository extends JpaRepository<Requisition ,Integer> {
}
