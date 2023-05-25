package com.lexisnexis.hiring.service.impl;

import com.lexisnexis.hiring.dto.CandidateDTO;
import com.lexisnexis.hiring.entity.Candidate;
import com.lexisnexis.hiring.entity.Comments;
import com.lexisnexis.hiring.entity.Employee;
import com.lexisnexis.hiring.entity.ScheduleInterview;
import com.lexisnexis.hiring.exception.InterviewAlreadyScheduleException;
import com.lexisnexis.hiring.exception.InterviewNotFoundException;
import com.lexisnexis.hiring.repository.CandidateRepository;
import com.lexisnexis.hiring.repository.CommentsRepository;
import com.lexisnexis.hiring.repository.EmployeeRepository;
import com.lexisnexis.hiring.repository.ScheduleInterviewRepository;
import com.lexisnexis.hiring.service.ScheduleInterviewService;
import com.lexisnexis.hiring.util.DTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ScheduleInterviewImpl implements ScheduleInterviewService {
    @Autowired
    ScheduleInterviewRepository scheduleInterviewRepository;
    @Autowired
    CandidateRepository candidateRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    CommentsRepository commentsRepository;
    @Autowired
    DTOConverter dtoConverter;

    @Override
    public ScheduleInterview addInterview(ScheduleInterview scheduleInterview) throws InterviewAlreadyScheduleException {
        if (scheduleInterviewRepository.findById(scheduleInterview.getInterviewId()).isPresent()) {
            throw new InterviewAlreadyScheduleException("Interview already schedule");
        } else {
            ScheduleInterview interview=null;
            Optional<ScheduleInterview> existingInterview=scheduleInterviewRepository.findByCandidateId(scheduleInterview.getCandidate().getCandidateId());
            if(existingInterview.isEmpty()) {
                Set<Employee> employeeList = new HashSet<>();
                for (Employee employee : scheduleInterview.getPanels()) {
                    Employee employee1 = employeeRepository.findById(employee.getEmployeeId()).get();
                    employeeList.add(employee1);
                }
                int candidateId = scheduleInterview.getCandidate().getCandidateId();
                Candidate candidate = candidateRepository.getById(candidateId);
                candidate.setResult(scheduleInterview.getLevelOfInterview());
                candidateRepository.save(candidate);
                scheduleInterview.setPanels(employeeList);
                interview= scheduleInterviewRepository.save(scheduleInterview);
            }
            else {
                ScheduleInterview newExistingInterview = existingInterview.get();
                Set<Employee> employeeList = new HashSet<>();
                for (Employee employee : newExistingInterview.getPanels()) {
                    Employee employee1 = employeeRepository.findById(employee.getEmployeeId()).get();
                    employeeList.add(employee1);
                }

                Candidate candidate = candidateRepository.getById(newExistingInterview.getCandidate().getCandidateId());
                newExistingInterview.setLevelOfInterview(scheduleInterview.getLevelOfInterview());
                candidate.setResult(scheduleInterview.getLevelOfInterview());
                candidateRepository.save(candidate);
                newExistingInterview.setPanels(employeeList);
                interview= scheduleInterviewRepository.save(newExistingInterview);
            }
            return interview;
        }
    }
    @Override
    public String deleteInterview(int interviewId) throws InterviewNotFoundException {
        if (scheduleInterviewRepository.findByInterviewId(interviewId) == null) {
            throw new InterviewNotFoundException("Interview not found for interviewId: " + interviewId);
        }
        scheduleInterviewRepository.deleteById(interviewId);
        return ("Interview Deleted for interviewId: " + interviewId);
    }

    @Override
    public ScheduleInterview updateInterview(ScheduleInterview scheduleInterview) throws InterviewNotFoundException {
        if (scheduleInterviewRepository.findById(scheduleInterview.getInterviewId()).isEmpty()) {
            throw new InterviewNotFoundException("Interview not found");
        } else {
            ScheduleInterview interview = scheduleInterviewRepository.findById(scheduleInterview.getInterviewId()).get();
            if (interview != null) {
                if (scheduleInterview.getInterviewTime() != null) {
                    interview.setInterviewTime(scheduleInterview.getInterviewTime());
                }
                if (scheduleInterview.getLevelOfInterview() != null) {
                    interview.setLevelOfInterview(scheduleInterview.getLevelOfInterview());
                }
                if (scheduleInterview.getCandidate() != null) {
                    interview.setCandidate(scheduleInterview.getCandidate());
                }
                if (scheduleInterview.getHumanResource() != null) {
                    interview.setHumanResource(scheduleInterview.getHumanResource());
                }
                if (scheduleInterview.getManager() != null) {
                    interview.setManager(scheduleInterview.getManager());
                }
            }

            Set<Employee> employeeList = new HashSet<>();
            for (Employee employee : scheduleInterview.getPanels()) {
                Employee employee1 = employeeRepository.findById(employee.getEmployeeId()).get();
                employeeList.add(employee1);
            }
            if (scheduleInterview.getPanels() != null) {
                interview.setPanels(employeeList);
            }
            scheduleInterviewRepository.save(interview);
            return interview;
        }
    }

    @Override
    public List<ScheduleInterview> getAllInterviews() {
        List<ScheduleInterview> interviews = scheduleInterviewRepository.findAll();
        return interviews;
    }

    @Override
    public ScheduleInterview getInterviewByInterviewId(int interviewId) throws InterviewNotFoundException {
        if (scheduleInterviewRepository.findByInterviewId(interviewId) == null) {
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
        if (candidate == null) {
            throw new InterviewNotFoundException("Interview Not Found For Id: " + candidateId);
        } else {
            if (scheduleInterviewRepository.getAllByCandidate(candidate).isEmpty()) {
                throw new InterviewNotFoundException("No Interview Found");
            } else {
                List<ScheduleInterview> scheduleInterviewList = scheduleInterviewRepository.getAllByCandidate(candidate);
                return scheduleInterviewList;
            }
        }
    }

    @Override
    public List<CandidateDTO> getScheduleInterviewsByPanelId(int employeeId) {
        List<ScheduleInterview> scheduleInterviewListByPanelId = scheduleInterviewRepository.getScheduleInterviewsByPanelId(employeeId);
        List<CandidateDTO> rejectedCandidateList = new ArrayList<>();
        for (ScheduleInterview scheduleInterview : scheduleInterviewListByPanelId) {
            Candidate candidate = scheduleInterview.getCandidate();
            if (candidate != null) {
                if (candidate.getResult().equalsIgnoreCase("L1Scheduled") || candidate.getResult().equalsIgnoreCase("L1ReScheduled") || candidate.getResult().equalsIgnoreCase("L2Scheduled") || candidate.getResult().equalsIgnoreCase("L2ReScheduled")) {
                    rejectedCandidateList.add(dtoConverter.candidateDTOConverter(candidate));
                }
//                for(CandidateDTO candidateDTO:rejectedCandidateList)
//                {
//                    if(candidateDTO.getCandidateId() == candidate.getCandidateId()){
//                        if(candidate.getResult().equalsIgnoreCase("L1Scheduled") || candidate.getResult().equalsIgnoreCase("L1ReScheduled") )
//                        {
//                            rejectedCandidateList.add(dtoConverter.candidateDTOConverter(candidate));
//                        }
//                    }
//                }
            }
        }
        return rejectedCandidateList;
    }

    @Override
    public List<CandidateDTO> getInterviewsTakenByPanelId(int employeeId) {
        List<Comments> takenInterviewListByPanelId = commentsRepository.listOfTakenInterviewsByPanelId(employeeId);
        List<CandidateDTO> rejectedCandidateList = new ArrayList<>();
        for (Comments comments : takenInterviewListByPanelId) {
            Candidate candidate = comments.getCandidate();
            if (candidate != null) {
                rejectedCandidateList.add(dtoConverter.candidateDTOConverter(candidate));
            }
        }
        return rejectedCandidateList;
    }
}
