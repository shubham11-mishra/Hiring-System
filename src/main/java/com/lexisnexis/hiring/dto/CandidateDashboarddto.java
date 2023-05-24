package com.lexisnexis.hiring.dto;

import lombok.Data;

import java.util.List;

@Data
public class CandidateDashboarddto {

    private String CandidateName;  //candiate table

    private String jobProfile;  // requisition table

    private List<String> levelName;
    private List<String> comments;

    private String Result;   // candidate tble



}
