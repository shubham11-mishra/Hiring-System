package com.lexisnexis.hiring.repository;

import com.lexisnexis.hiring.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface CommentsRepository extends JpaRepository<Comments,Integer> {
	@Query(value = "SELECT * FROM comments_table c WHERE c.employee_employee_Id = ?1", nativeQuery = true)
	public List<Comments> findByEmpId(int employeeId);

	@Query(value = "SELECT * FROM comments_table c WHERE c.candidate_candidate_Id = ?1", nativeQuery = true)
	public List<Comments> findByCandidId(int candidateId);
	@Query(value = "SELECT * FROM comments_table c WHERE c.candidate_id = ?1 AND  result NOT IN ('notshortlisted', 'leveloneselected' ,'leveltwoselected' ,'levelonerejected' ,'leveltworejected')", nativeQuery = true)
	Comments findCandidateByCandidateIdAndShortlisted(int candidateId);
	@Query(value = "SELECT * FROM comments_table c WHERE c.candidate_id = ?1 AND  result NOT IN ('notshortlisted', 'shortlisted' ,'leveltwoselected' ,'levelonerejected' ,'leveltworejected')", nativeQuery = true)
	Comments findCandidateByCandidateIdAndLevelOneSelected(int candidateId);
}