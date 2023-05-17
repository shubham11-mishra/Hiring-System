package com.lexisnexis.hiring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "candidate_table")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int candidateId;
    private String candidateName;
    private Blob candidateResume;
    private Date appliedDate;
    private Date selectionDate;
    private Date level1Date;
    private Date level2Date;
    @OneToOne
    private Employee hr;
    @OneToOne
    private Requisition job;
    private Date createdDate;
    private Date updatedDate;
    private boolean isDeleted;

}
