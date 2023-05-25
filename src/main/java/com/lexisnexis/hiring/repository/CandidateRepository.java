package com.lexisnexis.hiring.repository;

import com.lexisnexis.hiring.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Integer> {
    @Query(value = "SELECT * FROM candidate_table " +
            " WHERE requisition_name_job_id IN (SELECT job_id FROM requisition_table WHERE manager_employee_id = ?1)  AND selection_date IS NULL AND result = 'ProfileAdded' ", nativeQuery = true)
    List<Candidate> getPendingScreenCandidates(Integer managerId);

    @Query(value = "SELECT * FROM candidate_table " +
            " WHERE requisition_name_job_id IN (SELECT job_id FROM requisition_table WHERE manager_employee_id = ?1)  AND selection_date IS NOT NULL AND result IN ('L1Scheduled'  , 'L1ReScheduled') ", nativeQuery = true)
    List<Candidate> getLevelOneCandidates(Integer managerId);

    @Query(value = "SELECT * FROM candidate_table " +
            " WHERE requisition_name_job_id IN (SELECT job_id FROM requisition_table WHERE manager_employee_id = ?1)  AND level1date IS NOT NULL AND result IN ('L2Scheduled' , 'L2ReScheduled' )", nativeQuery = true)
    List<Candidate> getLevelTwoCandidates(Integer managerId);

    @Query(value = "SELECT * FROM candidate_table " +
            " WHERE requisition_name_job_id IN (SELECT job_id FROM requisition_table WHERE manager_employee_id = ?1)  AND result = 'L2Selected'", nativeQuery = true)
    List<Candidate> getFinalSelectedCandidates(Integer managerId);

    @Query(value = "SELECT * FROM candidate_table WHERE requisition_name_job_id IN (SELECT job_id FROM requisition_table WHERE manager_employee_id = ?1)  AND result IN ('NotShortlisted'  ,'L1Rejected' ,'L2Rejected')", nativeQuery = true)
    public List<Candidate> findRejectedCandidateByManagerID(int managerId);

    @Query(value = "SELECT * FROM candidate_table WHERE selection_date IS NULL AND human_resource_employee_id =?1 AND result = 'ProfileAdded' ", nativeQuery = true)
    public List<Candidate> getListOfCandidatesWhoAreNotShortlisted(int employeeId);

    @Query(value = "SELECT * FROM candidate_table  WHERE selection_date IS NOT NULL AND (result = 'Shortlisted' OR result = 'L1Selected') AND human_resource_employee_id =?1 ", nativeQuery = true)
    public List<Candidate> getListOfCandidatesWhoAreShortlisted(int employeeId);

    @Query(value = "SELECT * FROM candidate_table WHERE  human_resource_employee_id = ?1" , nativeQuery = true)
    public List<Candidate> getResultOfCandidates(int employeeId);

}
