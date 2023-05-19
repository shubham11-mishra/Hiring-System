package com.lexisnexis.hiring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lexisnexis.hiring.entity.Requisition;
import com.lexisnexis.hiring.exception.InvalidEmployeeID;
import com.lexisnexis.hiring.service.RequisitionService;

@RestController
public class RequisitionController {

	@Autowired
	private RequisitionService requisitionService;

	@PostMapping("addRequistion/{managerID}")
	public ResponseEntity<Requisition> addRequisition(@RequestBody Requisition requisition,@PathVariable int managerID) throws InvalidEmployeeID{
		return new ResponseEntity<Requisition>(requisitionService.addRequisition(requisition,managerID),HttpStatus.OK);
	}

	@PutMapping("updateRequistion/{jobId}")
	public ResponseEntity<Requisition> updateRequisition(@RequestBody Requisition requisition,@PathVariable int jobId) throws InvalidEmployeeID{
		return new ResponseEntity<Requisition>(requisitionService.updateRequisition(requisition,jobId),HttpStatus.OK);
	}

	@GetMapping("getRequistion/{jobId}")
	public ResponseEntity<Requisition> getRequisition(@PathVariable int jobId) throws InvalidEmployeeID{
		return new ResponseEntity<Requisition>(requisitionService.getRequisition(jobId),HttpStatus.OK);
	}

	@GetMapping("getAllRequistion/{managerId}")
	public ResponseEntity<List<Requisition>> getAllRequisition(@PathVariable int managerId)throws InvalidEmployeeID {
		return new ResponseEntity<List<Requisition>>(requisitionService.getAllRequisition(managerId),HttpStatus.OK);
	}

	@DeleteMapping("deleteRequistion/{jobId}")
	public ResponseEntity<String> deleteRequisition(@PathVariable int jobId) throws InvalidEmployeeID{
		return new ResponseEntity<String>(requisitionService.deleteRequisition(jobId),HttpStatus.OK);
	}


}
