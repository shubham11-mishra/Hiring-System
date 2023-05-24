package com.lexisnexis.hiring.controller;

import java.io.IOException;
import java.util.List;

import com.lexisnexis.hiring.dto.CandidateDashboarddto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lexisnexis.hiring.entity.CandidateRequest;
import com.lexisnexis.hiring.entity.Candidate;
import com.lexisnexis.hiring.exception.CandidateAlreadyExistException;
import com.lexisnexis.hiring.service.CandidateService;

@RestController
@RequestMapping("/candidate")
public class CandidateController {
    @Autowired
    private CandidateService candidateService;
   
    @PostMapping(value = "/savecandidate",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Candidate> saveCandidate(@ModelAttribute CandidateRequest candidateRequest) throws CandidateAlreadyExistException, IOException{
        return new ResponseEntity<Candidate>(candidateService.saveCandidate(candidateRequest), HttpStatus.OK);
    }
    
    @PutMapping(value = "/updatecandidate",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Candidate> updateCandidate(@ModelAttribute CandidateRequest candidateRequest) throws CandidateAlreadyExistException, IOException{
        return new ResponseEntity<Candidate>(candidateService.updateCandidate(candidateRequest), HttpStatus.OK);
    }
    @GetMapping(value = "/getcandidate/{candidateId}")
    public ResponseEntity<Candidate> getCandidateById(@PathVariable Integer candidateId){
        return new ResponseEntity<Candidate>(candidateService.getByCandidateId(candidateId), HttpStatus.OK);
    }
    
    @GetMapping(value = "/getAllcandidate")
    public ResponseEntity<List<Candidate>> getAllCandidate() {
        return new ResponseEntity<List<Candidate>>(candidateService.getAllCandidate(), HttpStatus.OK);
 }
    @DeleteMapping(value = "/deletecandidate/{candidateId}")
    public ResponseEntity<String> deleteCandidateById(@PathVariable Integer candidateId){
    	candidateService.deleteByCandidateId(candidateId);
        return new ResponseEntity<String>("Candidate are " +candidateId+" deleted ", HttpStatus.OK);
    }
    @GetMapping(value = "/getPendingScreenCandidates/{managerId}")
    public ResponseEntity<List<Candidate>> getPendingScreenCandidates(@PathVariable Integer managerId) {
        return new ResponseEntity<List<Candidate>>(candidateService.getPendingScreenCandidates(managerId), HttpStatus.OK);
    }

    @GetMapping(value = "/getLevelOneCandidates/{managerId}")
    public ResponseEntity<List<Candidate>> getLevelOneCandidates(@PathVariable Integer managerId) {
        return new ResponseEntity<List<Candidate>>(candidateService.getLevelOneCandidates(managerId), HttpStatus.OK);
    }
    @GetMapping(value = "/getLevelTwoCandidates/{managerId}")
    public ResponseEntity<List<Candidate>> getLevelTwoCandidates(@PathVariable Integer managerId) {
        return new ResponseEntity<List<Candidate>>(candidateService.getLevelTwoCandidates(managerId), HttpStatus.OK);
    }
    @GetMapping(value = "/getFinalSelectedCandidates/{managerId}")
    public ResponseEntity<List<Candidate>> getFinalSelectedCandidates(@PathVariable Integer managerId) {
        return new ResponseEntity<List<Candidate>>(candidateService.getFinalSelectedCandidates(managerId), HttpStatus.OK);
    }
    @GetMapping(value = "/selectCandidate/{candidateId}")
    public ResponseEntity<Candidate> selectCandidate(@PathVariable Integer candidateId) {
        return new ResponseEntity<Candidate>(candidateService.selectCandidate(candidateId), HttpStatus.OK);
    }

    @GetMapping("/viewCandidatehistory/{id}")
    public ResponseEntity<CandidateDashboarddto> viewCandidatehistory(@PathVariable int id){
        return new ResponseEntity<CandidateDashboarddto>(candidateService.viewCandidatehistory(id),HttpStatus.OK);
    }

    @GetMapping("/rejectedCandidatehistory/{managerId}")
    public ResponseEntity<List<Candidate>> rejectedCandidateHistory(@PathVariable int managerId){
        return new ResponseEntity<List<Candidate>>(candidateService.rejectedCandidateHistory(managerId),HttpStatus.OK);
    }


    @GetMapping("/not-shortlisted-candidates/{hrId}")
    public ResponseEntity<List<Candidate>> getListOfCandidatesWhoAreNotYetShortlisted(@PathVariable int hrId) {
        List<Candidate> nonShortListedCandidates = candidateService.getListOfCandidatesWhoAreNotShortlisted(hrId);
        return ResponseEntity.ok(nonShortListedCandidates);
    }

    @GetMapping("/shortlisted-candidates/{hrId}")
    public ResponseEntity<List<Candidate>> getListOfCandidatesWhoAreShortlisted(@PathVariable int hrId) {
        List<Candidate> shortListedCandidates = candidateService.getListOfCandidatesWhoAreShortlisted(hrId);
        return ResponseEntity.ok(shortListedCandidates);
    }

    @GetMapping("/results/{hrId}")
    public ResponseEntity<List<Candidate>> getResultOfCandidates(@PathVariable int hrId) {
        List<Candidate> candidates = candidateService.getResultOfCandidates(hrId);
        return ResponseEntity.ok(candidates);
    }

}
