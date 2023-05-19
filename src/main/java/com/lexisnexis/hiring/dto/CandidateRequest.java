package com.lexisnexis.hiring.dto;

import java.time.LocalDateTime;

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
	
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime appliedDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime selectionDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime level1Date;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime level2Date;
	private Employee hiringManager;
	private Requisition requisitionName;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
}
