package com.lexisnexis.hiring.service;

import java.io.IOException;
import java.util.List;

import com.lexisnexis.hiring.dto.CandidateRequest;
import com.lexisnexis.hiring.entity.Candidate;
import com.lexisnexis.hiring.exception.CandidateAlreadyExistException;

public interface CandidateService {

     Candidate saveCandidate(CandidateRequest candidateRequest) throws CandidateAlreadyExistException,IOException;
     Candidate updateCandidate(CandidateRequest candidateRequest)  throws IOException;
     Candidate getByCandidateId(int candidateId);
     List<Candidate> getAllCandidate();
     void deleteByCandidateId(int candidateId);
     
    // Candidate candidateSave(MultipartFile file,Candidate candidate); 
    	 
     
}
