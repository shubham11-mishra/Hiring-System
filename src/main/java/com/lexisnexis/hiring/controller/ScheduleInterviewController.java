package com.lexisnexis.hiring.controller;

import com.lexisnexis.hiring.dto.CandidateDTO;
import com.lexisnexis.hiring.dto.RequisitionDTO;
import com.lexisnexis.hiring.entity.Requisition;
import com.lexisnexis.hiring.entity.ScheduleInterview;
import com.lexisnexis.hiring.exception.InterviewAlreadyScheduleException;
import com.lexisnexis.hiring.exception.InterviewNotFoundException;
import com.lexisnexis.hiring.service.CandidateService;
import com.lexisnexis.hiring.service.EmployeeService;
import com.lexisnexis.hiring.service.RequisitionService;
import com.lexisnexis.hiring.service.ScheduleInterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/scheduleInterview")
public class ScheduleInterviewController {
    @Autowired
    ScheduleInterviewService scheduleInterviewService;

    @Autowired
    EmployeeService employeeService;
    @Autowired
    RequisitionService requisitionService;
    @Autowired
    CandidateService candidateService;

    @GetMapping("/saveInterview/{hrId}/{managerId}/{candidateId}")
    public String getScheduleInterviewPage(Model model,@PathVariable int hrId,@PathVariable int managerId,@PathVariable int candidateId)
    {
        model.addAttribute("hrId",hrId);
        model.addAttribute("candidateId",candidateId);
        model.addAttribute("managerId",managerId);
        for (RequisitionDTO requisition:
        requisitionService.getRequisitionByManagerId(managerId)) {
            if(candidateService.getByCandidateId(candidateId).getAppliedPosition().equalsIgnoreCase(requisition.getJobProfile()))
            {
                model.addAttribute("requisitionDetails",requisition);
            }
        }
        model.addAttribute("listOfPanels",employeeService.getAllEmployeesByDesignation("panel"));
        return "scheduleInterview";
    }

    @PostMapping("/addInterview")
    public String addInterview(@ModelAttribute ScheduleInterview scheduleInterview) throws InterviewAlreadyScheduleException {
       System.out.println(scheduleInterview.toString());
        ScheduleInterview interview = scheduleInterviewService.addInterview(scheduleInterview);
        return "redirect:/hrDashboard/"+interview.getHumanResource().getEmployeeId();
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
        return new ResponseEntity<>("Interview Updated Successfully on " + scheduleInterview1.getInterviewTime(), HttpStatus.OK);
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

    @GetMapping("/interviewList/{panelId}")
    public String getScheduleInterviewsByPanelId(Model model, @PathVariable int panelId) {
        List<CandidateDTO> listOfInterviews = scheduleInterviewService.getScheduleInterviewsByPanelId(panelId);
        model.addAttribute("panelId",panelId);
        model.addAttribute("candidateList", listOfInterviews);
        model.addAttribute("size",listOfInterviews.size());
        return "listOfInterview";
    }

    @GetMapping("/hrInterviewList/{hrId}")
    public String getScheduleInterviewsByHrId(Model model,@PathVariable int hrId) {
        List<CandidateDTO> listOfInterviews = scheduleInterviewService.getScheduleInterviewsByHrId(hrId);
       model.addAttribute("interviews",listOfInterviews);
       model.addAttribute("hrId",hrId);
       model.addAttribute("size",listOfInterviews.size());
        return "listOfHRScheduleInterview";
    }


    @GetMapping("/interviewsTakenList/{panelId}")
    public String getInterviewsTakenByPanelId(Model model, @PathVariable int panelId) {
        List<CandidateDTO> listOfInterviewsTaken = scheduleInterviewService.getInterviewsTakenByPanelId(panelId);
        model.addAttribute("listOfInterviewsTaken", listOfInterviewsTaken);
        model.addAttribute("panelId",panelId);
        model.addAttribute("size",listOfInterviewsTaken.size());
        return "listOfTakenInterviews";
    }
}
