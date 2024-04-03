package com.lexisnexis.hiring.service;

import com.lexisnexis.hiring.dto.RequisitionDTO;
import com.lexisnexis.hiring.entity.Requisition;

import java.util.List;

public interface RequisitionService {
	Requisition addRequisition(Requisition requisition, int managerID) ;

	Requisition updateRequisition(Requisition requisition,int jobId) ;

	String deleteRequisition(int jobId) ;

	RequisitionDTO getRequisition(int jobId) ;

	List<RequisitionDTO> getAllRequisition(int managerId) ;

    List<RequisitionDTO> getRequisitionByManagerId(int managerId);

	List<RequisitionDTO> getRequisitionByStatusOpen(int hrID);

    Requisition updateRequisitionStatus(int requisitionId, String action);
}
