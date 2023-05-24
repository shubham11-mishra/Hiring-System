package com.lexisnexis.hiring.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.lexisnexis.hiring.dto.CandidateDashboarddto;
import com.lexisnexis.hiring.entity.Comments;
import com.lexisnexis.hiring.repository.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lexisnexis.hiring.entity.CandidateRequest;
import com.lexisnexis.hiring.entity.Candidate;
import com.lexisnexis.hiring.exception.CandidateAlreadyExistException;
import com.lexisnexis.hiring.exception.CandidateDoesNotExistException;
import com.lexisnexis.hiring.repository.CandidateRepository;
import com.lexisnexis.hiring.service.CandidateService;

/**
 * @author PalPre01
 */
@Service
public class CandidateServiceImpl implements CandidateService {
    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private CommentsRepository commentsRepository;

    private final String FOLDER_PATH = "C:/FurnitureApplication/HIRING_APPLICATION/hiring/src/main/resources/myfile/";

    @SuppressWarnings("resource")
    @Override
    public Candidate saveCandidate(CandidateRequest candidateRequest)
            throws CandidateAlreadyExistException, IOException {
        Candidate newCandidate = new Candidate();

        if (candidateRepository.findById(candidateRequest.getCandidateId()).isPresent()) {
            throw new CandidateAlreadyExistException("Candidate Already Exist With Name "+candidateRequest.getCandidateName());
        }

        newCandidate.setCandidateId(candidateRequest.getCandidateId());
        newCandidate.setCandidateName(candidateRequest.getCandidateName());
        newCandidate.setAppliedDate(candidateRequest.getAppliedDate());
        newCandidate.setCreatedDate(candidateRequest.getCreatedDate());
        newCandidate.setHumanResource(candidateRequest.getHumanResource());
        newCandidate.setLevel1Date(candidateRequest.getLevel1Date());
        newCandidate.setLevel2Date(candidateRequest.getLevel2Date());
        newCandidate.setRequisitionName(candidateRequest.getRequisitionName());
        newCandidate.setSelectionDate(candidateRequest.getSelectionDate());
        newCandidate.setUpdatedDate(candidateRequest.getUpdatedDate());
        String originalFilename = FOLDER_PATH + candidateRequest.getCandidateResume().getOriginalFilename();
        newCandidate.setCandidateResume(originalFilename);

        candidateRepository.save(newCandidate);
        Files.copy(candidateRequest.getCandidateResume().getInputStream(), Paths.get(originalFilename),
                StandardCopyOption.REPLACE_EXISTING);
        return newCandidate;
    }

    @Override
    public Candidate updateCandidate(CandidateRequest candidateRequest) throws IOException {

        if (candidateRepository.findById(candidateRequest.getCandidateId()).isEmpty()) {
            throw new CandidateDoesNotExistException("Candidate Does not Exist");
        }
        Candidate updateCandidate = candidateRepository.findById(candidateRequest.getCandidateId()).get();
        System.out.print(updateCandidate);
        updateCandidate.setCandidateId(candidateRequest.getCandidateId());

        if (candidateRequest.getCandidateName() != null) {
            updateCandidate.setCandidateName(candidateRequest.getCandidateName());
        }
        if (candidateRequest.getAppliedDate() != null) {
            updateCandidate.setAppliedDate(candidateRequest.getAppliedDate());
        }

        if (candidateRequest.getCreatedDate() != null) {
            updateCandidate.setCreatedDate(candidateRequest.getCreatedDate());
        }
        if (candidateRequest.getHumanResource() != null) {
            updateCandidate.setHumanResource(candidateRequest.getHumanResource());
        }
        if (candidateRequest.getLevel1Date() != null) {
            updateCandidate.setLevel1Date(candidateRequest.getLevel1Date());
        }
        if (candidateRequest.getLevel2Date() != null) {
            updateCandidate.setLevel2Date(candidateRequest.getLevel2Date());
        }
        if (candidateRequest.getRequisitionName() != null) {
            updateCandidate.setRequisitionName(candidateRequest.getRequisitionName());
        }
        if (candidateRequest.getSelectionDate() != null) {
            updateCandidate.setSelectionDate(candidateRequest.getSelectionDate());
        }
        if (candidateRequest.getUpdatedDate() != null) {
            updateCandidate.setUpdatedDate(candidateRequest.getUpdatedDate());
        }
        String originalFilename = FOLDER_PATH + candidateRequest.getCandidateResume().getOriginalFilename();

        Files.copy(candidateRequest.getCandidateResume().getInputStream(), Paths.get(originalFilename),
                StandardCopyOption.REPLACE_EXISTING);

        updateCandidate.setCandidateResume(originalFilename);
        candidateRepository.save(updateCandidate);
        return updateCandidate;
    }

    @Override
    public Candidate getByCandidateId(int candidateId) {
        if (candidateRepository.findById(candidateId).isEmpty()) {
            throw new CandidateDoesNotExistException("Candidate Does not Exist");
        }
        Candidate findByCandidateId = candidateRepository.findById(candidateId).get();
        return findByCandidateId;
    }

    @Override
    public List<Candidate> getAllCandidate() {
        List<Candidate> findAll = candidateRepository.findAll();
        int size = findAll.size();
        if (size > 0) {
            return findAll;
        } else {
            throw new CandidateDoesNotExistException("Candidate Does not Exist");
        }

    }

    @Override
    public void deleteByCandidateId(int candidateId) {
        if (candidateRepository.findById(candidateId).isEmpty()) {
            throw new CandidateDoesNotExistException("Candidate Does not Exist");
        } else {
            candidateRepository.deleteById(candidateId);
        }
    }

    @Override
    public List<Candidate> getPendingScreenCandidates(Integer managerId) {
        List<Candidate> pendingScreenCandidates = candidateRepository.getPendingScreenCandidates(managerId);
        if (pendingScreenCandidates.isEmpty()) {
            throw new CandidateDoesNotExistException("Candidate Does not Exist");
        }
        return pendingScreenCandidates;
    }

    @Override
    public List<Candidate> getLevelOneCandidates(Integer managerId) {
        List<Candidate> levelOneAllCandidates = candidateRepository.getLevelOneCandidates(managerId);
        if (levelOneAllCandidates.isEmpty()) {
            throw new CandidateDoesNotExistException("Candidate Does not found");
        }
        List<Candidate> levelOneCandidates = new ArrayList<>();
        for (Candidate candidate : levelOneAllCandidates) {
            if (commentsRepository.findCandidateByCandidateIdAndShortlisted(candidate.getCandidateId()) == null) {
                levelOneCandidates.add(candidate);
            }
        }
        return levelOneCandidates;
    }
    @Override
    public List<Candidate> getLevelTwoCandidates(Integer managerId) {
        List<Candidate> levelAllTwoCandidates = candidateRepository.getLevelTwoCandidates(managerId);
        if (levelAllTwoCandidates.isEmpty()) {
            throw new CandidateDoesNotExistException("Candidate Does not Exist");
        }
        List<Candidate> levelTwoCandidates = new ArrayList<>();
        for (Candidate candidate : levelAllTwoCandidates) {
            if (commentsRepository.findCandidateByCandidateIdAndLevelOneSelected(candidate.getCandidateId()) != null) {
                levelTwoCandidates.add(candidate);
            }
        }
        return levelTwoCandidates;
    }

    @Override
    public List<Candidate> getFinalSelectedCandidates(Integer managerId) {
        List<Candidate> finalSelectedCandidates = candidateRepository.getFinalSelectedCandidates(managerId);
        if (finalSelectedCandidates.isEmpty()) {
            throw new CandidateDoesNotExistException("Candidate Does not Exist");
        }
        return finalSelectedCandidates;
    }

    @Override
    public Candidate selectCandidate(Integer candidateId) {
        if (candidateRepository.findById(candidateId).isEmpty()) {
            throw new CandidateDoesNotExistException("Candidate Does not Exist");
        }
        Candidate findByCandidateId = candidateRepository.findById(candidateId).get();
        findByCandidateId.setResult("shortlisted");
        findByCandidateId.setSelectionDate(LocalDateTime.now());
        return candidateRepository.save(findByCandidateId);
    }
    @Override
    public CandidateDashboarddto viewCandidatehistory(int id) {
        Candidate findByCandidateId = candidateRepository.findById(id).get();
        List<Comments> findByCandidate = commentsRepository.findByCandidId(findByCandidateId.getCandidateId());
        CandidateDashboarddto d = new CandidateDashboarddto();
        List<String> levelNames = new ArrayList<>();
        List<String> comments = new ArrayList<>();
        for (Comments comment : findByCandidate) {
            levelNames.add(comment.getLevelName());
            comments.add(comment.getComments());
            d.setLevelName(levelNames);
            d.setComments(comments);
            if(comment.getLevelName().equalsIgnoreCase("L2") && comment.getResult().equalsIgnoreCase("leveltwoselected")) {
                findByCandidateId.setResult("leveltwoselected");
                d.setResult(findByCandidateId.getResult());
            }
        }
        d.setCandidateName(findByCandidateId.getCandidateName());
        d.setJobProfile(findByCandidateId.getRequisitionName().getJobProfile());
        candidateRepository.save(findByCandidateId);
        return d;
    }

    @Override
    public List<Candidate> rejectedCandidateHistory(int managerId) {
        return candidateRepository.findRejectedCandidateByManagerID(managerId);
    }

    @Override
    public List<Candidate> getListOfCandidatesWhoAreNotShortlisted(int employeeId) {
        List<Candidate> nonShortlistedCandidates = candidateRepository.getListOfCandidatesWhoAreNotShortlisted(employeeId);
        return nonShortlistedCandidates;
    }

    @Override
    public List<Candidate> getListOfCandidatesWhoAreShortlisted(int employeeId) {
        List<Candidate> ShortlistedCandidates = candidateRepository.getListOfCandidatesWhoAreShortlisted(employeeId);
        return ShortlistedCandidates;
    }

    @Override
    public List<Candidate> getResultOfCandidates(int employeeId) {
        return candidateRepository.getResultOfCandidates(employeeId);
    }
}