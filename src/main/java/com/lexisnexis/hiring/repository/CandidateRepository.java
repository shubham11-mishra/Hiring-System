package com.lexisnexis.hiring.repository;

import com.lexisnexis.hiring.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Integer> {
    @Query(value = "SELECT * FROM candidate_table " +
            " WHERE requisition_name_job_id IN (SELECT job_id FROM requisition_table WHERE manager_employee_id = ?1)  AND selection_date IS NULL", nativeQuery = true)
    List<Candidate> getPendingScreenCandidates(Integer managerId);

    @Query(value = "SELECT * FROM candidate_table " +
            " WHERE requisition_name_job_id IN (SELECT job_id FROM requisition_table WHERE manager_employee_id = ?1)  AND selection_date IS NOT NULL AND result = 'shortlisted' ", nativeQuery = true)
    List<Candidate> getLevelOneCandidates(Integer managerId);

    @Query(value = "SELECT * FROM candidate_table " +
            " WHERE requisition_name_job_id IN (SELECT job_id FROM requisition_table WHERE manager_employee_id = ?1)  AND level1date IS NOT NULL AND result = 'leveloneselected' ", nativeQuery = true)
    List<Candidate> getLevelTwoCandidates(Integer managerId);

    @Query(value = "SELECT * FROM candidate_table " +
            " WHERE requisition_name_job_id IN (SELECT job_id FROM requisition_table WHERE manager_employee_id = ?1)  AND result = 'leveltwoselected'", nativeQuery = true)
    List<Candidate> getFinalSelectedCandidates(Integer managerId);

    @Query(value = "SELECT * FROM candidate_table WHERE requisition_name_job_id IN (SELECT job_id FROM requisition_table WHERE manager_employee_id = ?1)  AND result IN ('notshortlisted'  ,'levelonerejected' ,'leveltworejected')", nativeQuery = true)
    public List<Candidate> findRejectedCandidateByManagerID(int managerId);

    @Query("SELECT c FROM Candidate c WHERE c.selectionDate IS NULL")
    List<Candidate> getListOfCandidatesWhoAreNotShortlisted();

    @Query("SELECT c FROM Candidate c WHERE c.selectionDate IS NOT NULL AND (c.Result = 'shortlisted' OR c.Result = 'L1')")
    List<Candidate> getListOfCandidatesWhoAreShortlisted();

    @Query("SELECT c FROM Candidate c WHERE c.humanResource.id = :employeeId")
    List<Candidate> getResultOfCandidates(@Param("employeeId") int employeeId);

}
