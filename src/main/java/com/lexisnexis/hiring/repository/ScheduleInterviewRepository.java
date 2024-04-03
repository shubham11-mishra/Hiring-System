package com.lexisnexis.hiring.repository;

import com.lexisnexis.hiring.entity.Candidate;
import com.lexisnexis.hiring.entity.ScheduleInterview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleInterviewRepository extends JpaRepository<ScheduleInterview,Integer> {
    ScheduleInterview findByInterviewId(int interviewId);
    List<ScheduleInterview> findByLevelOfInterview(String levelOfInterview);
    List<ScheduleInterview> getAllByCandidate(Candidate candidate);
    @Query(value = "SELECT * FROM schedule_interview_table  WHERE interview_id IN (SELECT interview_id FROM interview_table WHERE employee_id = ?1)",nativeQuery = true)
    List<ScheduleInterview> getScheduleInterviewsByPanelId(int employeeId);
    @Query(value = "SELECT * FROM schedule_interview_table  WHERE human_resource_employee_id =?1 ",nativeQuery = true)
    List<ScheduleInterview> getScheduleInterviewsByHrId(int hrId);

    @Query(value = "SELECT * FROM schedule_interview_table  WHERE candidate_candidate_id = ?1",nativeQuery = true)
    Optional<ScheduleInterview> findByCandidateId(int id);
}
