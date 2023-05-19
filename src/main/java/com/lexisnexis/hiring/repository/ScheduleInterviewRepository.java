package com.lexisnexis.hiring.repository;

import com.lexisnexis.hiring.entity.ScheduleInterview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleInterviewRepository extends JpaRepository<ScheduleInterview,Integer> {
}
