package com.lexisnexis.hiring.entity;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
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
    private String timeslot;
    private int noPanels;
    @OneToOne
    private Employee specificPanel;
    private String additionalCondition;
    @OneToOne
    private Employee manager;
    @CreationTimestamp
    private LocalDateTime createdDate;
    @UpdateTimestamp
    private LocalDateTime updatedDate;
}
