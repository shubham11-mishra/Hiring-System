package com.lexisnexis.hiring.controller;

import com.lexisnexis.hiring.dto.CandidateDTO;
import com.lexisnexis.hiring.dto.CommentsDTO;
import com.lexisnexis.hiring.dto.GenerateReport;
import com.lexisnexis.hiring.dto.RequisitionDTO;
import com.lexisnexis.hiring.entity.Candidate;
import com.lexisnexis.hiring.entity.CandidateRequest;
import com.lexisnexis.hiring.entity.Employee;
import com.lexisnexis.hiring.exception.CandidateAlreadyExistException;
import com.lexisnexis.hiring.service.CandidateService;
import com.lexisnexis.hiring.service.CommentService;
import com.lexisnexis.hiring.service.RequisitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/candidate")
public class CandidateController {
    @Autowired
    private CandidateService candidateService;
    @Autowired
    private RequisitionService requisitionService;

    @Autowired
    private CommentService commentService;
    @GetMapping("/addCandidate/{hrID}")
    public String getAddCandidate(Model model,@PathVariable int hrID) {
        model.addAttribute("hrId",hrID);
        model.addAttribute("listOfReq",requisitionService.getRequisitionByStatusOpen(hrID));
        return "addCandidate";
    }
    @PostMapping(value = "/saveCandidate/{hrID}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String saveCandidate(Model model,@ModelAttribute CandidateRequest candidateRequest,@PathVariable int hrID)
            throws CandidateAlreadyExistException, IOException {
        Employee employee=new Employee();
        employee.setEmployeeId(hrID);
        candidateRequest.setHumanResource(employee);
        Candidate savedCandidate = candidateService.saveCandidate(candidateRequest);
        return "redirect:/hrDashboard/"+savedCandidate.getHumanResource().getEmployeeId();
    }
    
    @PutMapping(value = "/updatecandidate",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateCandidate(@ModelAttribute CandidateRequest candidateRequest) throws CandidateAlreadyExistException, IOException{
        Candidate updatedCandidate =candidateService.updateCandidate(candidateRequest);
        return new ResponseEntity<String>("Candidate Updated Successfully with ID "+updatedCandidate.getCandidateId(), HttpStatus.OK);
    }
    @GetMapping(value = "/getCandidate/{candidateId}")
    public String getCandidateById(Model model, @PathVariable Integer candidateId){
        CandidateDTO byCandidateId = candidateService.getByCandidateId(candidateId);
        List<CommentsDTO> commentsByCandidateId = commentService
                .getCommentsByCandidateId(byCandidateId.getCandidateId());
        model.addAttribute("candidateDetails", byCandidateId);
        model.addAttribute("commentsDetails", commentsByCandidateId);
        return  "candidateDashboard";
    }
    
    @GetMapping(value = "/getAllcandidate")
    public ResponseEntity<List<CandidateDTO>> getAllCandidate() {
        return new ResponseEntity<List<CandidateDTO>>(candidateService.getAllCandidate(), HttpStatus.OK);
 }
    @DeleteMapping(value = "/deletecandidate/{candidateId}")
    public ResponseEntity<String> deleteCandidateById(@PathVariable Integer candidateId){
    	candidateService.deleteByCandidateId(candidateId);
        return new ResponseEntity<String>("Candidate are " +candidateId+" deleted ", HttpStatus.OK);
    }
    @GetMapping(value = "/getPendingScreenCandidates/{managerId}")
    @RequestMapping(value ="/getPendingScreenCandidates/{managerId}", method = RequestMethod.GET)
    public String getPendingScreenCandidates( Model model,@PathVariable Integer managerId){
        List<CandidateDTO> getPendingScreenCandidates = candidateService.getPendingScreenCandidates(managerId);
        model.addAttribute("getPendingScreenCandidates", getPendingScreenCandidates);
        System.out.println(getPendingScreenCandidates.size());
        model.addAttribute("size",getPendingScreenCandidates.size());
        model.addAttribute("managerId", managerId);
        return "pendingScreenCandidate";
    }

    @GetMapping(value = "/getLevelOneCandidates/{managerId}")
    public String getLevelOneCandidates(Model model, @PathVariable Integer managerId) {
        List<CandidateDTO> levelOneCandidatesList = candidateService.getLevelOneCandidates(managerId);
        model.addAttribute("levelOneCandidatesList", levelOneCandidatesList);
        model.addAttribute("size", levelOneCandidatesList.size());
        return "level1PendingCandiates";
    }
    @GetMapping(value = "/getLevelTwoCandidates/{managerId}")
    public String getLevelTwoCandidates(Model model,@PathVariable Integer managerId) {
        List<CandidateDTO> levelTwoCandidates = candidateService.getLevelTwoCandidates(managerId);
        model.addAttribute("levelTwoCandidates", levelTwoCandidates);
        model.addAttribute("size", levelTwoCandidates.size());
        return "level2PendingCandidate";
    }
    @GetMapping("/finalSelectedCandidates/{managerId}")
    public String getFinalSelectedCandidates(Model model,@PathVariable Integer managerId) {
        List<CandidateDTO> finalSelectedCandidates = candidateService.getFinalSelectedCandidates(managerId);
        model.addAttribute("wow", finalSelectedCandidates);
        model.addAttribute("size",finalSelectedCandidates.size());
        return "finalSelectedCandidates";
    }
    @GetMapping(value = "/selectCandidate/{candidateId}")
    public ResponseEntity<CandidateDTO> selectCandidate(@PathVariable Integer candidateId) {
        return new ResponseEntity<CandidateDTO>(candidateService.selectCandidate(candidateId), HttpStatus.OK);
    }
    @GetMapping(value = "/selectCandidate/{managerId}/{candidateId}")
    public String selectCandidates(Model model,@PathVariable int managerId,@PathVariable int candidateId){
        CandidateDTO selectedCandidates = candidateService.selectCandidate(candidateId);
        model.addAttribute("selectedCandidates", selectedCandidates);
        model.addAttribute("candidateId", candidateId);
        return "redirect:/candidate/getPendingScreenCandidates/"+managerId;
    }
    @GetMapping("/rejectedCandidateHistory/{managerId}")
    public String rejectedCandidateHistory(Model model,@PathVariable int managerId){
        model.addAttribute("wow", candidateService.rejectedCandidateHistory(managerId));
        model.addAttribute("size",candidateService.rejectedCandidateHistory(managerId).size());
        return "rejectedCandidate";
    }

    @GetMapping("/notShortlistedCandidates/{hrId}")
    public String getListOfCandidatesWhoAreNotYetShortlisted(Model model, @PathVariable int hrId) {
        List<CandidateDTO> nonShortListedCandidates = candidateService.getListOfCandidatesWhoAreNotShortlisted(hrId);
        model.addAttribute("nonShortListedCandidates", nonShortListedCandidates);
        model.addAttribute("size",nonShortListedCandidates.size());
        return "listOfCandidates";
    }

    @GetMapping("/shortlistedCandidates/{hrId}")
    public String getListOfCandidatesWhoAreShortlisted(Model model,@PathVariable int hrId) {
        List<CandidateDTO> shortListedCandidates = candidateService.getListOfCandidatesWhoAreShortlisted(hrId);
        model.addAttribute("shortListedCandidates", shortListedCandidates);
        model.addAttribute("hrId",hrId);
        model.addAttribute("size",shortListedCandidates.size());
        return "shortlistedCandidates";
    }

    @GetMapping("/results/{hrId}")
    public String getResultOfCandidates(Model model,@PathVariable int hrId) {
        List<CandidateDTO> resultOfCandidates = candidateService.getResultOfCandidates(hrId);
        model.addAttribute("resultOfCandidates", resultOfCandidates);
        model.addAttribute("size",resultOfCandidates.size());
        return "resultOfCandidate";
    }
    @GetMapping("/generateReport/{managerID}")
    public String getGenerateReportPage(Model model,@PathVariable int managerID)
    {
        GenerateReport generateReport=new GenerateReport();
        generateReport.setStartDate(LocalDateTime.now().minusDays(-7));
        generateReport.setEndDate(LocalDateTime.now());
        model.addAttribute("product",generateReport);
        model.addAttribute("managerID",managerID);
        model.addAttribute("requisitionList", requisitionService.getRequisitionByManagerId(managerID));
        return "generateReport";
    }
    @PostMapping("/generateReport/{managerID}")
    public String getReportPage(Model model, @ModelAttribute GenerateReport generateReport, @PathVariable int managerID)
    {
        List<CandidateDTO> profileAddedCandidates=new ArrayList<>();
        List<CandidateDTO> shortlistedCandidates=new ArrayList<>();
        List<CandidateDTO> notShortlistedCandidates=new ArrayList<>();
        List<CandidateDTO> l1SelectedCandidates=new ArrayList<>();
        List<CandidateDTO> l1RejectedCandidates=new ArrayList<>();
        List<CandidateDTO> l1ScheduledCandidates=new ArrayList<>();
        List<CandidateDTO> l1RescheduleCandidates=new ArrayList<>();
        List<CandidateDTO> l2SelectedCandidates=new ArrayList<>();
        List<CandidateDTO> l2RejectedCandidates=new ArrayList<>();
        List<CandidateDTO> l2ScheduledCandidates=new ArrayList<>();
        List<CandidateDTO> l2RescheduleCandidates=new ArrayList<>();
        List<CandidateDTO> candidateList = candidateService.getAllCandidatesReport(generateReport);
        for (CandidateDTO candidate:candidateList ) {
            if(candidate.getCandidateResult().equals("ProfileAdded"))
            {
                profileAddedCandidates.add(candidate);
            }
            if(candidate.getCandidateResult().equals("Shortlisted"))
            {
                shortlistedCandidates.add(candidate);
            }
            if(candidate.getCandidateResult().equals("NotShortlisted"))
            {
                notShortlistedCandidates.add(candidate);
            }
            if(candidate.getCandidateResult().equals("L1Scheduled"))
            {
                l1ScheduledCandidates.add(candidate);
            }
            if(candidate.getCandidateResult().equals("L1ReScheduled"))
            {
                l1RescheduleCandidates.add(candidate);
            }
            if(candidate.getCandidateResult().equals("L1Selected"))
            {
                l1SelectedCandidates.add(candidate);
            }
            if(candidate.getCandidateResult().equals("L1Rejected"))
            {
                l1RejectedCandidates.add(candidate);
            }
            if(candidate.getCandidateResult().equals("L2Scheduled"))
            {
                l2ScheduledCandidates.add(candidate);
            }
            if(candidate.getCandidateResult().equals("L2ReScheduled"))
            {
                l2RescheduleCandidates.add(candidate);
            }  if(candidate.getCandidateResult().equals("L2Selected"))
            {
                l2SelectedCandidates.add(candidate);
            }
            if(candidate.getCandidateResult().equals("L2Rejected"))
            {
                l2RejectedCandidates.add(candidate);
            }
        }
        model.addAttribute("profileAddedCandidates",profileAddedCandidates.size());
        model.addAttribute("shortlistedCandidates",shortlistedCandidates.size());
        model.addAttribute("notShortlistedCandidates",notShortlistedCandidates.size());
        model.addAttribute("l1ScheduledCandidates",l1ScheduledCandidates.size());
        model.addAttribute("l1RescheduleCandidates",l1RescheduleCandidates.size());
        model.addAttribute("l1SelectedCandidates",l1SelectedCandidates.size());
        model.addAttribute("l1RejectedCandidates",l1RejectedCandidates.size());
        model.addAttribute("l2ScheduledCandidates",l2ScheduledCandidates.size());
        model.addAttribute("l2RescheduleCandidates",l2RescheduleCandidates.size());
        model.addAttribute("l2SelectedCandidates",l2SelectedCandidates.size());
        model.addAttribute("l2RejectedCandidates",l2RejectedCandidates.size());
        model.addAttribute("Job",requisitionService.getRequisition(generateReport.getRequisitionId()));
        model.addAttribute("generateReport",generateReport);
        return "generatedReport";
    }
}

