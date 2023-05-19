package com.lexisnexis.hiring.repository;

import com.lexisnexis.hiring.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate , Integer> {
	
	public Candidate findByCandidateId(int id);
}
