package com.lexisnexis.hiring.controller;

import com.lexisnexis.hiring.dto.RequisitionDTO;
import com.lexisnexis.hiring.entity.Requisition;
import com.lexisnexis.hiring.service.RequisitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requisition")
public class RequisitionController {

	@Autowired
	private RequisitionService requisitionService;

	@PostMapping(value = "addRequisition/{managerID}")
	public ResponseEntity<String> addRequisition(@RequestBody Requisition requisition,@PathVariable int managerID) {
		Requisition savedRequisition =requisitionService.addRequisition(requisition,managerID);
		return new ResponseEntity<String>("Requisition Added Successfully With Profile "+savedRequisition.getJobProfile(),HttpStatus.OK);
	}

	@PutMapping("updateRequisition/{jobId}")
	public ResponseEntity<String> updateRequisition(@RequestBody Requisition requisition,@PathVariable int jobId) {
		Requisition savedRequisition =requisitionService.updateRequisition(requisition,jobId);
		return new ResponseEntity<String>("Requisition Updated Successfully With Profile "+savedRequisition.getJobProfile(),HttpStatus.OK);
	}
	@DeleteMapping("deleteRequisition/{jobId}")
	public ResponseEntity<String> deleteRequisition(@PathVariable int jobId) {
		return new ResponseEntity<String>(requisitionService.deleteRequisition(jobId),HttpStatus.OK);
	}

	@GetMapping("getRequisition/{jobId}")
	public ResponseEntity<RequisitionDTO> getRequisition(@PathVariable int jobId) {
		return new ResponseEntity<RequisitionDTO>(requisitionService.getRequisition(jobId),HttpStatus.OK);
	}

	@GetMapping("getAllRequisition/{managerId}")
	public ResponseEntity<List<RequisitionDTO>> getAllRequisition(@PathVariable int managerId) {
		return new ResponseEntity<List<RequisitionDTO>>(requisitionService.getAllRequisition(managerId),HttpStatus.OK);
	}

	@GetMapping("getAllRequisitionWithStatus/{managerId}")
	public ResponseEntity<List<RequisitionDTO>> getRequisitionByManagerId(@PathVariable int managerId) {
		List<RequisitionDTO> requisitionByManagerId = requisitionService.getRequisitionByManagerId(managerId);
		return new ResponseEntity<List<RequisitionDTO>>(requisitionByManagerId,HttpStatus.OK);
	}
}
