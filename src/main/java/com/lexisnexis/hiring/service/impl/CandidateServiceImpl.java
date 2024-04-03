package com.lexisnexis.hiring.service.impl;

import com.lexisnexis.hiring.dto.CandidateDTO;
import com.lexisnexis.hiring.dto.GenerateReport;
import com.lexisnexis.hiring.entity.Candidate;
import com.lexisnexis.hiring.entity.CandidateRequest;
import com.lexisnexis.hiring.entity.Comments;
import com.lexisnexis.hiring.exception.CandidateAlreadyExistException;
import com.lexisnexis.hiring.exception.CandidateDoesNotExistException;
import com.lexisnexis.hiring.repository.CandidateRepository;
import com.lexisnexis.hiring.repository.CommentsRepository;
import com.lexisnexis.hiring.service.CandidateService;
import com.lexisnexis.hiring.util.DTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author PalPre01
 */
@Service
public class CandidateServiceImpl implements CandidateService {
    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private CommentsRepository commentsRepository;
    @Autowired
    private DTOConverter dtoConverter;

    private final String FOLDER_PATH = "C:/FurnitureApplication/HIRING_APPLICATION/hiring/src/main/resources/myfile/";

    @SuppressWarnings("resource")
    @Override
    public Candidate saveCandidate(CandidateRequest candidateRequest)
            throws CandidateAlreadyExistException, IOException {
        Candidate newCandidate = new Candidate();
        if (candidateRepository.findById(candidateRequest.getCandidateId()).isPresent()) {
            throw new CandidateAlreadyExistException("Candidate Already Exist With Name " + candidateRequest.getCandidateName());
        }
        newCandidate.setCandidateId(candidateRequest.getCandidateId());
        newCandidate.setCandidateName(candidateRequest.getCandidateName());
        newCandidate.setAppliedDate(LocalDateTime.now());
        newCandidate.setCreatedDate(candidateRequest.getCreatedDate());
        newCandidate.setHumanResource(candidateRequest.getHumanResource());
        newCandidate.setLevel1Date(candidateRequest.getLevel1Date());
        newCandidate.setLevel2Date(candidateRequest.getLevel2Date());
        newCandidate.setRequisitionName(candidateRequest.getRequisitionName());
        newCandidate.setSelectionDate(candidateRequest.getSelectionDate());
        newCandidate.setUpdatedDate(candidateRequest.getUpdatedDate());
        newCandidate.setResult("profileadded");
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
    public CandidateDTO getByCandidateId(int candidateId) {
        if (candidateRepository.findById(candidateId).isEmpty()) {
            throw new CandidateDoesNotExistException("Candidate Does not Exist");
        }
        return dtoConverter.candidateDTOConverter(candidateRepository.findById(candidateId).get());
    }

    @Override
    public List<CandidateDTO> getAllCandidate() {
        List<Candidate> findAll = candidateRepository.findAll();
//        if (findAll.size() > 0) {
            List<Candidate> pendingScreenCandidates = findAll;
            List<CandidateDTO> candidateDTOList = new ArrayList<>();
            for (Candidate candidate : pendingScreenCandidates) {
                if (candidate != null) {
                    candidateDTOList.add(dtoConverter.candidateDTOConverter(candidate));
                }
            }
            return candidateDTOList;
//        } else {
//            throw new CandidateDoesNotExistException("Candidate Does not Exist");
//        }
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
    public List<CandidateDTO> getPendingScreenCandidates(Integer managerId) {
        List<Candidate> pendingScreenCandidates = candidateRepository.getPendingScreenCandidates(managerId);
//        if (pendingScreenCandidates.size() > 0) {
            List<CandidateDTO> candidateDTOList = new ArrayList<>();
            for (Candidate candidate : pendingScreenCandidates) {
                if (candidate != null) {
                    candidateDTOList.add(dtoConverter.candidateDTOConverter(candidate));
                }
            }
            return candidateDTOList;
//        } else {
//            throw new CandidateDoesNotExistException("Candidate Does not Exist");
//        }
    }

    @Override
    public List<CandidateDTO> getLevelOneCandidates(Integer managerId) {
        List<Candidate> levelOneAllCandidates = candidateRepository.getLevelOneCandidates(managerId);
//        if (levelOneAllCandidates.isEmpty()) {
//            throw new CandidateDoesNotExistException("Candidate Does not found");
//        } else {
            List<CandidateDTO> levelOneCandidates = new ArrayList<>();
            for (Candidate candidate : levelOneAllCandidates) {
                if (commentsRepository.findCandidateByCandidateIdAndShortlisted(candidate.getCandidateId()) == null) {
                    levelOneCandidates.add(dtoConverter.candidateDTOConverter(candidate));
                }
            }
            return levelOneCandidates;
//        }
    }

    @Override
    public List<CandidateDTO> getLevelTwoCandidates(Integer managerId) {
        List<Candidate> levelAllTwoCandidates = candidateRepository.getLevelTwoCandidates(managerId);
//        if (levelAllTwoCandidates.isEmpty()) {
//            throw new CandidateDoesNotExistException("Candidate Does not Exist");
//        }
        List<CandidateDTO> levelTwoCandidates = new ArrayList<>();
        for (Candidate candidate : levelAllTwoCandidates) {
            System.out.println(candidate.toString());
            if (commentsRepository.findCandidateByCandidateIdAndLevelOneSelected(candidate.getCandidateId()) != null) {
                levelTwoCandidates.add(dtoConverter.candidateDTOConverter(candidate));
            }
        }
        return levelTwoCandidates;
    }

    @Override
    public List<CandidateDTO> getFinalSelectedCandidates(Integer managerId) {
        List<Candidate> finalSelectedCandidates = candidateRepository.getFinalSelectedCandidates(managerId);
//        if (finalSelectedCandidates.isEmpty()) {
//            throw new CandidateDoesNotExistException("Candidate Does not Exist");
//        }
        List<CandidateDTO> candidateDTOList = new ArrayList<>();
        for (Candidate candidate : finalSelectedCandidates) {
            if (candidate != null) {
                candidateDTOList.add(dtoConverter.candidateDTOConverter(candidate));
            }
        }
        return candidateDTOList;
    }

    @Override
    public CandidateDTO selectCandidate(Integer candidateId) {
//        if (candidateRepository.findById(candidateId).isEmpty()) {
//            throw new CandidateDoesNotExistException("Candidate Does not Exist");
//        }
        Candidate findByCandidateId = candidateRepository.findById(candidateId).get();
        findByCandidateId.setResult("shortlisted");
        findByCandidateId.setSelectionDate(LocalDateTime.now());
        Candidate candidate = candidateRepository.save(findByCandidateId);
        return dtoConverter.candidateDTOConverter(candidate);
    }

    @Override
    public List<CandidateDTO> rejectedCandidateHistory(int managerId) {
//        if (candidateRepository.findRejectedCandidateByManagerID(managerId).isEmpty()) {
//            throw new CandidateDoesNotExistException("Candidate Does not Exist");
//        }
        List<CandidateDTO> rejectedCandidateList = new ArrayList<>();
        for (Candidate candidate : candidateRepository.findRejectedCandidateByManagerID(managerId)) {
            if (candidate != null) {
                rejectedCandidateList.add(dtoConverter.candidateDTOConverter(candidate));
            }
        }
        return rejectedCandidateList;
    }

    @Override
    public List<CandidateDTO> getListOfCandidatesWhoAreNotShortlisted(int employeeId) {
//        if (candidateRepository.getListOfCandidatesWhoAreNotShortlisted(employeeId).isEmpty()) {
//            throw new CandidateDoesNotExistException("Candidate Does not Exist");
//        }
        List<CandidateDTO> nonShortlistedCandidates = new ArrayList<>();
        for (Candidate candidate : candidateRepository.getListOfCandidatesWhoAreNotShortlisted(employeeId)) {
            if (candidate != null) {
                nonShortlistedCandidates.add(dtoConverter.candidateDTOConverter(candidate));
            }
        }
        System.out.println("---"+nonShortlistedCandidates.size());
        return nonShortlistedCandidates;
    }

    @Override
    public List<CandidateDTO> getListOfCandidatesWhoAreShortlisted(int employeeId) {
//        if (candidateRepository.getListOfCandidatesWhoAreShortlisted(employeeId).isEmpty()) {
//            throw new CandidateDoesNotExistException("Candidate Does not Exist");
//        }
        List<CandidateDTO> ShortlistedCandidates = new ArrayList<>();
        for (Candidate candidate : candidateRepository.getListOfCandidatesWhoAreShortlisted(employeeId)) {
            if (candidate != null) {
                ShortlistedCandidates.add(dtoConverter.candidateDTOConverter(candidate));
            }
        }
        return ShortlistedCandidates;
    }

    @Override
    public List<CandidateDTO> getResultOfCandidates(int employeeId) {
        List<CandidateDTO> resultOfEveryCandidateByHRId = new ArrayList<>();
//        if (candidateRepository.getResultOfCandidates(employeeId).isEmpty()) {
//            throw new CandidateDoesNotExistException("Candidate Does not Exist");
//        }
        for (Candidate candidate : candidateRepository.getResultOfCandidates(employeeId)) {
            if (candidate != null) {
                resultOfEveryCandidateByHRId.add(dtoConverter.candidateDTOConverter(candidate));
            }
        }
        return resultOfEveryCandidateByHRId;
    }

    @Override
    public List<CandidateDTO> getAllCandidatesReport(GenerateReport generateReport) {
       List<CandidateDTO> candidateDTOList=new ArrayList<>();
        for (Candidate candidate:candidateRepository.getAllCandidatesReport(generateReport.getStartDate(),generateReport.getEndDate(),generateReport.getRequisitionId())) {
            if(candidate != null)
            {
                candidateDTOList.add(dtoConverter.candidateDTOConverter(candidate));
            }
        }
        return candidateDTOList;
    }
}