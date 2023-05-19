package com.lexisnexis.hiring.service.impl;

import com.lexisnexis.hiring.entity.Candidate;
import com.lexisnexis.hiring.entity.Employee;
import com.lexisnexis.hiring.entity.ScheduleInterview;
import com.lexisnexis.hiring.exception.InterviewAlreadyScheduleException;
import com.lexisnexis.hiring.exception.InterviewNotFoundException;
import com.lexisnexis.hiring.repository.CandidateRepository;
import com.lexisnexis.hiring.repository.ScheduleInterviewRepository;
import com.lexisnexis.hiring.service.ScheduleInterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleInterviewImpl implements ScheduleInterviewService {

    @Autowired
    ScheduleInterviewRepository scheduleInterviewRepository;

    @Autowired
    CandidateRepository candidateRepository;


    @Override
    public ScheduleInterview addInterview(ScheduleInterview scheduleInterview) throws InterviewAlreadyScheduleException {
        if(scheduleInterviewRepository.findById(scheduleInterview.getInterviewId()).isPresent()){
            throw new InterviewAlreadyScheduleException("Interview already schedule");
        }else{
            ScheduleInterview interview = scheduleInterviewRepository.save(scheduleInterview);
            return interview;
        }
    }

    @Override
    public String deleteInterview(int interviewId) throws InterviewNotFoundException {
        if(scheduleInterviewRepository.findByInterviewId(interviewId).isDeleted()) {
            throw new InterviewNotFoundException("Interview not found for interviewId: " + interviewId);
        }
            scheduleInterviewRepository.deleteById(interviewId);
            return ("Interview Deleted for interviewId: " + interviewId);
    }

    @Override
    public ScheduleInterview updateInterview(ScheduleInterview scheduleInterview) throws InterviewNotFoundException {
        if(scheduleInterviewRepository.findById(scheduleInterview.getInterviewId()).isEmpty()){
            throw new InterviewNotFoundException("Interview not found");
        }else{
            ScheduleInterview interview = scheduleInterviewRepository.findById(scheduleInterview.getInterviewId()).get();
            interview.setInterviewId(scheduleInterview.getInterviewId());
            interview.setInterviewTime(scheduleInterview.getInterviewTime());
            interview.setLevelOfInterview(scheduleInterview.getLevelOfInterview());
            interview.setCandidate(scheduleInterview.getCandidate());
            interview.setHumanResource(scheduleInterview.getHumanResource());
            interview.setManager(scheduleInterview.getManager());
            interview.setPanels(scheduleInterview.getPanels());
            ScheduleInterview updatedInterview = scheduleInterviewRepository.save(interview);
            return updatedInterview;
        }
    }

    @Override
    public List<ScheduleInterview> getAllInterviews() {
        List<ScheduleInterview> interviews = scheduleInterviewRepository.findAll();
        return interviews;
    }

    @Override
    public ScheduleInterview getInterviewByInterviewId(int interviewId) throws InterviewNotFoundException {
        if(scheduleInterviewRepository.findByInterviewId(interviewId).isDeleted()){
            throw new InterviewNotFoundException("Interview Not Found For Id: " + interviewId);
        }
        ScheduleInterview interview = scheduleInterviewRepository.findByInterviewId(interviewId);
        return interview;
    }

    @Override
    public List<ScheduleInterview> getAllInterviewsByLevelOfInterview(String levelOfInterview) {
        List<ScheduleInterview> interviewsByLevel = scheduleInterviewRepository.findByLevelOfInterview(levelOfInterview);
        return interviewsByLevel;
    }

    @Override
    public List<ScheduleInterview> getAllInterviewsByCandidateId(int candidateId) throws InterviewNotFoundException {
        Candidate candidate = candidateRepository.findById(candidateId).get();
        if(candidate == null)
        {
            throw new InterviewNotFoundException("Interview Not Found For Id: " + candidateId);
        }
        else {

            if (scheduleInterviewRepository.getAllByCandidate(candidate).isEmpty()) {
                throw new InterviewNotFoundException("No Interview Found");
            } else {
                List<ScheduleInterview> scheduleInterviewList = scheduleInterviewRepository.getAllByCandidate(candidate);
                return scheduleInterviewList;
            }
        }
    }

}
