package com.lexisnexis.hiring.service;

import java.util.List;

import com.lexisnexis.hiring.entity.Requisition;
import com.lexisnexis.hiring.exception.InvalidEmployeeID;

public interface RequisitionService {

	Requisition addRequisition(Requisition requisition, int managerID) throws InvalidEmployeeID;

	Requisition updateRequisition(Requisition requisition,int jobId) throws InvalidEmployeeID;

	Requisition getRequisition(int jobId) throws InvalidEmployeeID;

	String deleteRequisition(int jobId) throws InvalidEmployeeID;

	List<Requisition> getAllRequisition(int managerId) throws InvalidEmployeeID;

}
