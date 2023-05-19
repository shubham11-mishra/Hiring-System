package com.lexisnexis.hiring.repository;

import com.lexisnexis.hiring.entity.Requisition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequisitionRepository extends JpaRepository<Requisition ,Integer> {
}
