package com.lexisnexis.hiring.controller;

import com.lexisnexis.hiring.dto.RequisitionDTO;
import com.lexisnexis.hiring.entity.Requisition;
import com.lexisnexis.hiring.service.EmployeeService;
import com.lexisnexis.hiring.service.RequisitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/requisition")
public class RequisitionController {

	@Autowired
	private RequisitionService requisitionService;

	@Autowired
	private EmployeeService employeeService;
	@GetMapping(value = "/addRequisition/{managerID}")
	private String getAddRequisition(Model model,@PathVariable int managerID)
	{
		model.addAttribute("managerID",managerID);
		model.addAttribute("panels",employeeService.getAllEmployeesByDesignation("panel"));
		return "addRequisition";
	}

	@PostMapping(value = "/saveRequisition/{managerID}")
	public String addRequisition(@ModelAttribute Requisition requisition,@PathVariable int managerID) {
		Requisition savedRequisition =requisitionService.addRequisition(requisition,managerID);
		return "redirect:/managerDashboard/"+managerID;
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
	public String getAllRequisition(Model model,@PathVariable int managerId) {
		List<RequisitionDTO> allRequisition = requisitionService.getAllRequisition(managerId);
		model.addAttribute("managerId",managerId);
		model.addAttribute("wow", allRequisition);
		return "listOfRequisition";
	}
	@GetMapping("getAllRequisitionWithStatus/{managerId}")
	public ResponseEntity<List<RequisitionDTO>> getRequisitionByManagerId(@PathVariable int managerId) {
		List<RequisitionDTO> requisitionByManagerId = requisitionService.getRequisitionByManagerId(managerId);
		return new ResponseEntity<List<RequisitionDTO>>(requisitionByManagerId,HttpStatus.OK);
	}
	@PostMapping("/processAction/{managerId}")
	public String updateRequisitionStatus(Model model, @PathVariable int managerId, @RequestParam("requisitionId") int requisitionId,@RequestParam("action") String action) {
		requisitionService.updateRequisitionStatus(requisitionId,action);
		List<RequisitionDTO> allRequisition = requisitionService.getAllRequisition(managerId);
		model.addAttribute("wow", allRequisition);
		model.addAttribute("size",allRequisition.size());
		return "listOfRequisition";
	}
}
