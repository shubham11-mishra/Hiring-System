package com.lexisnexis.hiring.service;

import com.lexisnexis.hiring.dto.CandidateDTO;
import com.lexisnexis.hiring.entity.Candidate;
import com.lexisnexis.hiring.entity.CandidateRequest;
import com.lexisnexis.hiring.exception.CandidateAlreadyExistException;

import java.io.IOException;
import java.util.List;

public interface CandidateService {

     Candidate saveCandidate(CandidateRequest candidateRequest) throws CandidateAlreadyExistException,IOException;
     Candidate updateCandidate(CandidateRequest candidateRequest)  throws IOException;
     CandidateDTO getByCandidateId(int candidateId);
     List<CandidateDTO> getAllCandidate();
     void deleteByCandidateId(int candidateId);

    List<CandidateDTO> getPendingScreenCandidates(Integer managerId);

     List<CandidateDTO> getLevelOneCandidates(Integer managerId);

    List<CandidateDTO> getLevelTwoCandidates(Integer managerId);

    List<CandidateDTO> getFinalSelectedCandidates(Integer managerId);

    CandidateDTO selectCandidate(Integer candidateId);

    List<CandidateDTO> rejectedCandidateHistory(int managerId);
    List<CandidateDTO> getListOfCandidatesWhoAreNotShortlisted(int employeeId);

    List<CandidateDTO> getListOfCandidatesWhoAreShortlisted(int employeeId);

    List<CandidateDTO> getResultOfCandidates(int employeeId);
    	 
     
}
