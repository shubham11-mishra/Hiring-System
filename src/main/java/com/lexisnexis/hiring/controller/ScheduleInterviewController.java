package com.lexisnexis.hiring.controller;

import com.lexisnexis.hiring.entity.Candidate;
import com.lexisnexis.hiring.entity.ScheduleInterview;
import com.lexisnexis.hiring.exception.InterviewAlreadyScheduleException;
import com.lexisnexis.hiring.exception.InterviewNotFoundException;
import com.lexisnexis.hiring.service.impl.ScheduleInterviewImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scheduleInterview")
public class ScheduleInterviewController {

    @Autowired
    ScheduleInterviewImpl scheduleInterviewImpl;

    @PostMapping("/addInterview")
    public ResponseEntity<ScheduleInterview> addInterview(@RequestBody ScheduleInterview scheduleInterview) throws InterviewAlreadyScheduleException {
        ScheduleInterview interview = scheduleInterviewImpl.addInterview(scheduleInterview);
        return ResponseEntity.ok(interview);
    }

    @GetMapping("/getAllInterviews")
    public ResponseEntity<List<ScheduleInterview>> getAllInterviews(){
        List<ScheduleInterview> interviews = scheduleInterviewImpl.getAllInterviews();
        return ResponseEntity.ok(interviews);
    }

    @GetMapping("/getInterview/{interviewId}")
    public ResponseEntity<ScheduleInterview> getInterviewById(@PathVariable int interviewId) throws InterviewNotFoundException {
        ScheduleInterview interview = scheduleInterviewImpl.getInterviewByInterviewId(interviewId);
        return ResponseEntity.ok(interview);
    }

    @PostMapping("/updateInterview")
    public ResponseEntity<ScheduleInterview> updateInterview(@RequestBody ScheduleInterview scheduleInterview) throws InterviewNotFoundException {
        ScheduleInterview scheduleInterview1 = scheduleInterviewImpl.updateInterview(scheduleInterview);
        return ResponseEntity.ok(scheduleInterview1);
    }

    @DeleteMapping("/deleteInterview/{interviewId}")
    public ResponseEntity<String> deleteInterview(@PathVariable int interviewId) throws InterviewNotFoundException {
        scheduleInterviewImpl.deleteInterview(interviewId);
        return ResponseEntity.ok("Interview Deleted Successfully!!!");
    }

    @GetMapping("/getAllInterviews/{levelOfInterview}")
    public ResponseEntity<List<ScheduleInterview>> getAllInterviewsByLevelOfInterviews(@PathVariable String levelOfInterview){
        List<ScheduleInterview> interviews = scheduleInterviewImpl.getAllInterviewsByLevelOfInterview(levelOfInterview);
        return ResponseEntity.ok(interviews);
    }
    @GetMapping("/getAllInterviewsByCandidateId/{candidateId}")
    public ResponseEntity<List<ScheduleInterview>> getAllInterviewsByCandidateId(@PathVariable int candidateId) throws InterviewNotFoundException {
        List<ScheduleInterview> interviews = scheduleInterviewImpl.getAllInterviewsByCandidateId(candidateId);
        return ResponseEntity.ok(interviews);
    }
}
