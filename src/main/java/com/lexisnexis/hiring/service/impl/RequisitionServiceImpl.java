package com.lexisnexis.hiring.service.impl;

import com.lexisnexis.hiring.entity.Employee;
import com.lexisnexis.hiring.entity.Requisition;
import com.lexisnexis.hiring.exception.InvalidEmployeeID;
import com.lexisnexis.hiring.repository.EmployeeRepository;
import com.lexisnexis.hiring.repository.RequisitionRepository;
import com.lexisnexis.hiring.service.RequisitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RequisitionServiceImpl implements RequisitionService {

	@Autowired
	private RequisitionRepository requisitionRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Requisition addRequisition(Requisition requisition, int managerID) throws InvalidEmployeeID {
		Employee findByemployeeId = employeeRepository.findById(managerID).get();
		if(findByemployeeId!=null) {
			Employee findByemployeeId2 = employeeRepository.findById(requisition.getSpecificPanel().getEmployeeId()).get();
			requisition.setManager(findByemployeeId);
			requisition.setSpecificPanel(findByemployeeId2);
			requisition.setCreatedDate(LocalDateTime.now());
			requisition.setUpdatedDate(LocalDateTime.now());
			return requisitionRepository.save(requisition);
		}
		else {
			throw new InvalidEmployeeID("Manager ID is not valid");
		}		
	}

	@Override
	public Requisition updateRequisition(Requisition requisition,int jobId) throws InvalidEmployeeID {
		Requisition existingRequisition = requisitionRepository.findByJobId(jobId);

		if(existingRequisition!=null) {
			if(requisition.getJobProfile()!=null) {
				existingRequisition.setJobProfile(requisition.getJobProfile());
			}
			if(requisition.getAdditionalCondition()!=null) {
				existingRequisition.setAdditionalCondition(requisition.getAdditionalCondition());
			}
			if(requisition.getJobDescription()!=null) {
				existingRequisition.setJobDescription(requisition.getJobDescription());
			}
			if(requisition.getNoPanels()!=0) {
				existingRequisition.setNoPanels(requisition.getNoPanels());
			}
			if(requisition.getPartnerName()!=null) {
				existingRequisition.setPartnerName(requisition.getPartnerName());
			}
			if(requisition.getProjectName()!=null) {
				existingRequisition.setProjectName(requisition.getProjectName());
			}
			if(requisition.getStatus()!=null) {
				existingRequisition.setStatus(requisition.getStatus());
			}
			if(requisition.getTimeslot()!=null) {
				existingRequisition.setTimeslot(requisition.getTimeslot());
			}
			if(requisition.getSpecificPanel()!=null) {
				existingRequisition.setSpecificPanel(requisition.getSpecificPanel());
			}
			existingRequisition.setUpdatedDate(LocalDateTime.now());
			return requisitionRepository.save(existingRequisition);
		}else {
			throw new InvalidEmployeeID("JOB-ID is not valid");
		}
	}

	@Override
	public Requisition getRequisition(int jobId) throws InvalidEmployeeID {
		Requisition requisition = requisitionRepository.findByJobId(jobId);
		if(requisition!=null) {
			return requisition;
		}else {
			throw new InvalidEmployeeID("JOB-ID is not valid"); 
		}
	}

	@Override
	public String deleteRequisition(int jobId) throws InvalidEmployeeID {
		Requisition requisition = requisitionRepository.findByJobId(jobId);
		if(requisition!=null) {
			requisitionRepository.deleteById(jobId);
			return "Successfully Deleted";
		}else {
			throw new InvalidEmployeeID("JOB-ID is not valid"); 
		}
	}

	@Override
	public List<Requisition> getAllRequisition(int managerId) throws InvalidEmployeeID{
		List<Requisition> findByManager = requisitionRepository.findByManager(employeeRepository.findById(managerId).get());
		int size = findByManager.size();
		if(size>0) {
			return findByManager;
		}
		else {
			throw new InvalidEmployeeID("ManagerID is not valid"); 
		}

	}
}
