package com.lexisnexis.hiring.dto;

import lombok.Data;
@Data
public class RequisitionDTO {
    private int jobId;
    private String jobProfile;
    private String jobDescription;
    private String projectName;
    private String partnerName;
    private String status;
    private String timeslot;
    private int noPanels;
    private String specificPanelName;
    private String additionalCondition;
    private int managerId;

}
