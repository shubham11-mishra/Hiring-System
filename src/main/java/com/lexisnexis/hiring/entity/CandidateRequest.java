package com.lexisnexis.hiring.entity;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.lexisnexis.hiring.entity.Employee;
import com.lexisnexis.hiring.entity.Requisition;

import lombok.Data;

@Data
public class CandidateRequest {
	private int candidateId;
	private String candidateName;
	private MultipartFile candidateResume;
	private LocalDateTime appliedDate;
	private LocalDateTime selectionDate;
	private LocalDateTime level1Date;
	private LocalDateTime level2Date;
	private Employee hiringManager;
	private Requisition requisitionName;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
}
