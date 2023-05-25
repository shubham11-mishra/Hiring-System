package com.lexisnexis.hiring.controller;

import com.lexisnexis.hiring.dto.CandidateDTO;
import com.lexisnexis.hiring.entity.Candidate;
import com.lexisnexis.hiring.entity.CandidateRequest;
import com.lexisnexis.hiring.exception.CandidateAlreadyExistException;
import com.lexisnexis.hiring.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/candidate")
public class CandidateController {
    @Autowired
    private CandidateService candidateService;
   
    @PostMapping(value = "/savecandidate",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> saveCandidate(@ModelAttribute CandidateRequest candidateRequest) throws CandidateAlreadyExistException, IOException{
       Candidate savedCandidate =candidateService.saveCandidate(candidateRequest);
        return new ResponseEntity<String>("Candidate Saved Successfully with ID "+savedCandidate.getCandidateId(), HttpStatus.OK);
    }
    
    @PutMapping(value = "/updatecandidate",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateCandidate(@ModelAttribute CandidateRequest candidateRequest) throws CandidateAlreadyExistException, IOException{
        Candidate updatedCandidate =candidateService.updateCandidate(candidateRequest);
        return new ResponseEntity<String>("Candidate Updated Successfully with ID "+updatedCandidate.getCandidateId(), HttpStatus.OK);
    }
    @GetMapping(value = "/getcandidate/{candidateId}")
    public ResponseEntity<CandidateDTO> getCandidateById(@PathVariable Integer candidateId){
        return new ResponseEntity<CandidateDTO>(candidateService.getByCandidateId(candidateId), HttpStatus.OK);
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
    public ResponseEntity<List<CandidateDTO>> getPendingScreenCandidates(@PathVariable Integer managerId) {
        return new ResponseEntity<List<CandidateDTO>>(candidateService.getPendingScreenCandidates(managerId), HttpStatus.OK);
    }

    @GetMapping(value = "/getLevelOneCandidates/{managerId}")
    public ResponseEntity<List<CandidateDTO>> getLevelOneCandidates(@PathVariable Integer managerId) {
        return new ResponseEntity<List<CandidateDTO>>(candidateService.getLevelOneCandidates(managerId), HttpStatus.OK);
    }
    @GetMapping(value = "/getLevelTwoCandidates/{managerId}")
    public ResponseEntity<List<CandidateDTO>> getLevelTwoCandidates(@PathVariable Integer managerId) {
        return new ResponseEntity<List<CandidateDTO>>(candidateService.getLevelTwoCandidates(managerId), HttpStatus.OK);
    }
    @GetMapping(value = "/getFinalSelectedCandidates/{managerId}")
    public ResponseEntity<List<CandidateDTO>> getFinalSelectedCandidates(@PathVariable Integer managerId) {
        return new ResponseEntity<List<CandidateDTO>>(candidateService.getFinalSelectedCandidates(managerId), HttpStatus.OK);
    }
    @PostMapping(value = "/selectCandidate/{candidateId}")
    public ResponseEntity<CandidateDTO> selectCandidate(@PathVariable Integer candidateId) {
        return new ResponseEntity<CandidateDTO>(candidateService.selectCandidate(candidateId), HttpStatus.OK);
    }
    @GetMapping("/rejectedCandidatehistory/{managerId}")
    public ResponseEntity<List<CandidateDTO>> rejectedCandidateHistory(@PathVariable int managerId){
        return new ResponseEntity<List<CandidateDTO>>(candidateService.rejectedCandidateHistory(managerId),HttpStatus.OK);
    }
    @GetMapping("/notshortlistedcandidates/{hrId}")
    public ResponseEntity<List<CandidateDTO>>  getListOfCandidatesWhoAreNotYetShortlisted(@PathVariable int hrId) {
        List<CandidateDTO> nonShortListedCandidates = candidateService.getListOfCandidatesWhoAreNotShortlisted(hrId);
        return new ResponseEntity<List<CandidateDTO>>(nonShortListedCandidates,HttpStatus.OK);
    }

    @GetMapping("/shortlistedcandidates/{hrId}")
    public ResponseEntity<List<CandidateDTO>>  getListOfCandidatesWhoAreShortlisted(@PathVariable int hrId) {
        List<CandidateDTO> shortListedCandidates = candidateService.getListOfCandidatesWhoAreShortlisted(hrId);
        return new ResponseEntity<List<CandidateDTO>> (shortListedCandidates,HttpStatus.OK);
    }

    @GetMapping("/results/{hrId}")
    public ResponseEntity<List<CandidateDTO>> getResultOfCandidates(@PathVariable int hrId) {
        List<CandidateDTO> resultOfCandidates = candidateService.getResultOfCandidates(hrId);
        return new ResponseEntity<List<CandidateDTO>> (resultOfCandidates,HttpStatus.OK);
    }
}
