package com.lexisnexis.hiring.service;

import java.io.IOException;
import java.util.List;

import com.lexisnexis.hiring.entity.CandidateRequest;
import com.lexisnexis.hiring.entity.Candidate;
import com.lexisnexis.hiring.exception.CandidateAlreadyExistException;

public interface CandidateService {

     Candidate saveCandidate(CandidateRequest candidateRequest) throws CandidateAlreadyExistException,IOException;
     Candidate updateCandidate(CandidateRequest candidateRequest)  throws IOException;
     Candidate getByCandidateId(int candidateId);
     List<Candidate> getAllCandidate();
     void deleteByCandidateId(int candidateId);

    List<Candidate> getPendingScreenCandidates(Integer managerId);

     List<Candidate> getLevelOneCandidates(Integer managerId);

    List<Candidate> getLevelTwoCandidates(Integer managerId);

    List<Candidate> getFinalSelectedCandidates(Integer managerId);

    // Candidate candidateSave(MultipartFile file,Candidate candidate); 
    	 
     
}
