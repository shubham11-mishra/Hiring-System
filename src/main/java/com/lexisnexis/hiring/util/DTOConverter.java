package com.lexisnexis.hiring.util;

import com.lexisnexis.hiring.dto.CandidateDTO;
import com.lexisnexis.hiring.dto.RequisitionDTO;
import com.lexisnexis.hiring.entity.Candidate;
import com.lexisnexis.hiring.entity.Requisition;
import org.springframework.stereotype.Component;

@Component
public class DTOConverter {
    public CandidateDTO candidateDTOConverter(Candidate candidate)
    {
        CandidateDTO candidateDTO = new CandidateDTO();
        candidateDTO.setCandidateId(candidate.getCandidateId());
        candidateDTO.setCandidateName(candidate.getCandidateName());
        candidateDTO.setCandidateResume(candidate.getCandidateResume());
        candidateDTO.setCandidateResult(candidate.getResult());
        candidateDTO.setAppliedPosition(candidate.getRequisitionName().getJobProfile());
        candidateDTO.setMangerId(candidate.getRequisitionName().getManager().getEmployeeId());
        candidateDTO.setManagerName(candidate.getRequisitionName().getManager().getEmployeeName());
        candidateDTO.setHumanResourceId(candidate.getHumanResource().getEmployeeId());
        return  candidateDTO;
    }
    public RequisitionDTO requisitionDTOConverter(Requisition requisition)
    {
        RequisitionDTO requisitionDTO = new RequisitionDTO();
        requisitionDTO.setJobId(requisition.getJobId());
        requisitionDTO.setJobProfile(requisition.getJobProfile());
        requisitionDTO.setJobDescription(requisition.getJobDescription());
        requisitionDTO.setStatus(requisition.getStatus());
        requisitionDTO.setTimeslot(requisition.getTimeslot());
        requisitionDTO.setManagerId(requisition.getManager().getEmployeeId());
        requisitionDTO.setNoPanels(requisition.getNoPanels());
        requisitionDTO.setProjectName(requisition.getProjectName());
        requisitionDTO.setPartnerName(requisition.getPartnerName());
        requisitionDTO.setAdditionalCondition(requisition.getAdditionalCondition());
        requisitionDTO.setSpecificPanelName(requisition.getSpecificPanel().getEmployeeName());
        return  requisitionDTO;
    }
}
