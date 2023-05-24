package com.lexisnexis.hiring.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

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
	private Employee humanResource;
	private Requisition requisitionName;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
}
