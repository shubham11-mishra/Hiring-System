package com.lexisnexis.hiring.controller;

import com.lexisnexis.hiring.dto.CandidateDTO;
import com.lexisnexis.hiring.entity.Candidate;
import com.lexisnexis.hiring.entity.Comments;
import com.lexisnexis.hiring.entity.ScheduleInterview;
import com.lexisnexis.hiring.exception.InterviewAlreadyScheduleException;
import com.lexisnexis.hiring.exception.InterviewNotFoundException;
import com.lexisnexis.hiring.service.ScheduleInterviewService;
import com.lexisnexis.hiring.service.impl.ScheduleInterviewImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scheduleInterview")
public class ScheduleInterviewController {
    @Autowired
    ScheduleInterviewService scheduleInterviewService;

    @PostMapping("/addInterview")
    public ResponseEntity<String> addInterview(@RequestBody ScheduleInterview scheduleInterview) throws InterviewAlreadyScheduleException {
        ScheduleInterview interview = scheduleInterviewService.addInterview(scheduleInterview);
        return new ResponseEntity<>("Interview Scheduled Successfully on "+interview.getInterviewTime(), HttpStatus.OK);
    }

    @GetMapping("/getAllInterviews")
    public ResponseEntity<List<ScheduleInterview>> getAllInterviews() {
        List<ScheduleInterview> interviews = scheduleInterviewService.getAllInterviews();
        return ResponseEntity.ok(interviews);
    }

    @GetMapping("/getInterview/{interviewId}")
    public ResponseEntity<ScheduleInterview> getInterviewById(@PathVariable int interviewId) throws InterviewNotFoundException {
        ScheduleInterview interview = scheduleInterviewService.getInterviewByInterviewId(interviewId);
        return ResponseEntity.ok(interview);
    }

    @PostMapping("/updateInterview")
    public ResponseEntity<String> updateInterview(@RequestBody ScheduleInterview scheduleInterview) throws InterviewNotFoundException {
        ScheduleInterview scheduleInterview1 = scheduleInterviewService.updateInterview(scheduleInterview);
        return new ResponseEntity<>("Interview Updated Successfully on "+scheduleInterview1.getInterviewTime(), HttpStatus.OK);
    }

    @DeleteMapping("/deleteInterview/{interviewId}")
    public ResponseEntity<String> deleteInterview(@PathVariable int interviewId) throws InterviewNotFoundException {
        scheduleInterviewService.deleteInterview(interviewId);
        return ResponseEntity.ok("Interview Deleted Successfully!!!");
    }

    @GetMapping("/getAllInterviews/{levelOfInterview}")
    public ResponseEntity<List<ScheduleInterview>> getAllInterviewsByLevelOfInterviews(@PathVariable String levelOfInterview) {
        List<ScheduleInterview> interviews = scheduleInterviewService.getAllInterviewsByLevelOfInterview(levelOfInterview);
        return ResponseEntity.ok(interviews);
    }

    @GetMapping("/getAllInterviewsByCandidateId/{candidateId}")
    public ResponseEntity<List<ScheduleInterview>> getAllInterviewsByCandidateId(@PathVariable int candidateId) throws InterviewNotFoundException {
        List<ScheduleInterview> interviews = scheduleInterviewService.getAllInterviewsByCandidateId(candidateId);
        return ResponseEntity.ok(interviews);
    }

    @GetMapping("/interviewlist/{panelId}")
    public ResponseEntity<List<CandidateDTO>> getScheduleInterviewsByPanelId(@PathVariable int panelId) {
        List<CandidateDTO> listOfInterviews = scheduleInterviewService.getScheduleInterviewsByPanelId(panelId);
        return ResponseEntity.ok(listOfInterviews);
    }

    @GetMapping("/interviewstakenlist/{panelId}")
    public ResponseEntity<List<CandidateDTO>> getInterviewsTakenByPanelId(@PathVariable int panelId) {
        List<CandidateDTO> listOfInterviewsTaken = scheduleInterviewService.getInterviewsTakenByPanelId(panelId);
        return ResponseEntity.ok(listOfInterviewsTaken);
    }
}
