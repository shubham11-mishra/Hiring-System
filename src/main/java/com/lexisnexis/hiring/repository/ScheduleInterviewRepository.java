package com.lexisnexis.hiring.repository;

import com.lexisnexis.hiring.entity.Candidate;
import com.lexisnexis.hiring.entity.ScheduleInterview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleInterviewRepository extends JpaRepository<ScheduleInterview,Integer> {

    ScheduleInterview findByInterviewId(int interviewId);

    List<ScheduleInterview> findByLevelOfInterview(String levelOfInterview);

    List<ScheduleInterview> getAllByCandidate(Candidate candidate);
}
