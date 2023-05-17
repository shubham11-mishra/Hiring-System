package com.lexisnexis.hiring.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "requisition_table")
public class Requisition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int jobId;
    private String jobProfile;
    private String jobDescription;
    private String projectName;
    private String partnerName;
    private String status;
    private Date timeslot;
    private int noPanels;
    @OneToOne
    private Employee specificPanel;
    private String additionalCondition;
    @OneToOne
    private  Employee manager;
    private Date createdDate;
    private  Date updatedDate;
    private boolean isDeleted;
}
