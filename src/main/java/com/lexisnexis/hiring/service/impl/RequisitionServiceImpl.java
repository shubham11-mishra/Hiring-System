package com.lexisnexis.hiring.service.impl;

import com.lexisnexis.hiring.dto.RequisitionDTO;
import com.lexisnexis.hiring.entity.Employee;
import com.lexisnexis.hiring.entity.Requisition;
import com.lexisnexis.hiring.exception.NoEmployeeFoundException;
import com.lexisnexis.hiring.exception.NoJobFoundException;
import com.lexisnexis.hiring.repository.EmployeeRepository;
import com.lexisnexis.hiring.repository.RequisitionRepository;
import com.lexisnexis.hiring.service.RequisitionService;
import com.lexisnexis.hiring.util.DTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RequisitionServiceImpl implements RequisitionService {

    @Autowired
    private RequisitionRepository requisitionRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    DTOConverter dtoConverter;


    @Override
    public Requisition addRequisition(Requisition requisition, int managerID) {
        Employee findByEmployeeId = employeeRepository.findById(managerID).get();
        if (findByEmployeeId != null) {
            Employee employee = employeeRepository.findById(requisition.getSpecificPanel().getEmployeeId()).get();
            requisition.setManager(findByEmployeeId);
            requisition.setSpecificPanel(employee);
            requisition.setStatus("Open");
            return requisitionRepository.save(requisition);
        } else {
            throw new NoEmployeeFoundException("Manager Not Found With " + managerID);
        }
    }

    @Override
    public Requisition updateRequisition(Requisition requisition, int jobId) {
        Requisition existingRequisition = requisitionRepository.findByJobId(jobId);
        if (existingRequisition != null) {
            if (requisition.getJobProfile() != null) {
                existingRequisition.setJobProfile(requisition.getJobProfile());
            }
            if (requisition.getAdditionalCondition() != null) {
                existingRequisition.setAdditionalCondition(requisition.getAdditionalCondition());
            }
            if (requisition.getJobDescription() != null) {
                existingRequisition.setJobDescription(requisition.getJobDescription());
            }
            if (requisition.getNoPanels() != 0) {
                existingRequisition.setNoPanels(requisition.getNoPanels());
            }
            if (requisition.getPartnerName() != null) {
                existingRequisition.setPartnerName(requisition.getPartnerName());
            }
            if (requisition.getProjectName() != null) {
                existingRequisition.setProjectName(requisition.getProjectName());
            }
            if (requisition.getStatus() != null) {
                existingRequisition.setStatus(requisition.getStatus());
            }
            if (requisition.getTimeslot() != null) {
                existingRequisition.setTimeslot(requisition.getTimeslot());
            }
            if (requisition.getSpecificPanel() != null) {
                existingRequisition.setSpecificPanel(requisition.getSpecificPanel());
            }
            return requisitionRepository.save(existingRequisition);
        } else {
            throw new NoJobFoundException("NO Job Found with ID " + jobId);
        }
    }

    @Override
    public RequisitionDTO getRequisition(int jobId) {
        Requisition requisition = requisitionRepository.findByJobId(jobId);
        if (requisition != null) {
            return dtoConverter.requisitionDTOConverter(requisition);
        } else {
            throw new NoJobFoundException("NO Job Found with ID " + jobId);
        }
    }

    @Override
    public String deleteRequisition(int jobId) {
        Requisition requisition = requisitionRepository.findByJobId(jobId);
        if (requisition != null) {
            requisitionRepository.deleteById(jobId);
            return "Successfully Deleted";
        } else {
            throw new NoJobFoundException("JOB-ID is not valid");
        }
    }

    @Override
    public List<RequisitionDTO> getAllRequisition(int managerId) {
        List<Requisition> findByManager = requisitionRepository.findByManager(employeeRepository.findById(managerId).get());
        if (findByManager.isEmpty()) {
            throw new NoEmployeeFoundException("ManagerID is not valid");
        }
        List<RequisitionDTO> requisitionDTOS = new ArrayList<>();
        for (Requisition requisition : findByManager) {
            if (requisition != null) {
                requisitionDTOS.add(dtoConverter.requisitionDTOConverter(requisition));
            }
        }
        return requisitionDTOS;
    }

    @Override
    public List<RequisitionDTO> getRequisitionByManagerId(int managerId) {
        List<Requisition> findByManagerOrStatus = requisitionRepository.findByManagerOrStatus(managerId);
        if (findByManagerOrStatus.isEmpty()) {
            throw new NoJobFoundException("No Job Found With Manager ID " + managerId);
        } else {
            List<RequisitionDTO> requisitionDTOS = new ArrayList<>();
            for (Requisition requisition : findByManagerOrStatus) {
                if (requisition != null) {
                    requisitionDTOS.add(dtoConverter.requisitionDTOConverter(requisition));
                }
            }
            return requisitionDTOS;
        }
    }

    @Override
    public List<RequisitionDTO> getRequisitionByStatusOpen(int hrID) {
        List<Requisition> findByHrIdAndOpen = requisitionRepository.findByHrIdAndOpen(hrID);
            List<RequisitionDTO> requisitionDTOS = new ArrayList<>();
            for (Requisition requisition : findByHrIdAndOpen) {
                if (requisition != null) {
                    requisitionDTOS.add(dtoConverter.requisitionDTOConverter(requisition));
                    System.out.println(requisition.getJobProfile());
                }
            }
            return requisitionDTOS;
    }

    @Override
    public Requisition updateRequisitionStatus(int requisitionId, String action) {
        Requisition findByJobId = requisitionRepository.findByJobId(requisitionId);
        String f = "F";
        String c = "C";
        String o = "O";
        if (action.contains(f)) {
            findByJobId.setStatus("Freeze");
            requisitionRepository.save(findByJobId);
        }
        if (action.contains(c)) {
            System.out.println("hello");
            requisitionRepository.deleteById(requisitionId);
        }
        if (action.contains(o)) {
            findByJobId.setStatus("Open");
            requisitionRepository.save(findByJobId);
        }
        return findByJobId;
    }

}
