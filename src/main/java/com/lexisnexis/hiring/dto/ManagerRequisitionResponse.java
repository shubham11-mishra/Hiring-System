package com.lexisnexis.hiring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManagerRequisitionResponse {
    private String jobProfile;
    private String jobDescription;
    private String projectName;
    private String projectPartner;
    private String projectAction;
}