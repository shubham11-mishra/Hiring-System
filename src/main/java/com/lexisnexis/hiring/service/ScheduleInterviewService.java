package com.lexisnexis.hiring.service;

import com.lexisnexis.hiring.entity.Candidate;
import com.lexisnexis.hiring.entity.Comments;
import com.lexisnexis.hiring.entity.ScheduleInterview;
import com.lexisnexis.hiring.exception.InterviewAlreadyScheduleException;
import com.lexisnexis.hiring.exception.InterviewNotFoundException;

import java.util.List;

public interface ScheduleInterviewService {

    public ScheduleInterview addInterview(ScheduleInterview scheduleInterview) throws InterviewAlreadyScheduleException;

    public String deleteInterview(int interviewId) throws InterviewNotFoundException;

    public ScheduleInterview updateInterview(ScheduleInterview scheduleInterview) throws InterviewNotFoundException;

    public List<ScheduleInterview> getAllInterviews();

    public ScheduleInterview getInterviewByInterviewId(int interviewId) throws InterviewNotFoundException;

    public List<ScheduleInterview> getAllInterviewsByLevelOfInterview(String levelOfInterview);

    List<ScheduleInterview> getAllInterviewsByCandidateId(int candidateId) throws InterviewNotFoundException;

    List<ScheduleInterview> getScheduleInterviewsByPanelId(int employeeId);

    List<Comments> getInterviewsTakenByPanelId(int employeeId);
}
