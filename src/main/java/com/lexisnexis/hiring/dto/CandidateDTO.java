package com.lexisnexis.hiring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CandidateDTO {
    private int candidateId;
    private String candidateName;
    private String candidateResume;
    private String candidateResult;
    private String appliedPosition;
    private int mangerId;
    private String ManagerName;
    private int humanResourceId;
}
