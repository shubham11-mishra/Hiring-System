package com.lexisnexis.hiring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "candidate_table")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int candidate_id;
    private String candidate_name;
    private Blob candidate_resume;
    private Date applied_date;
    private Date selection_date;
    private Date level1_date;
    private Date level2_date;
    @OneToOne
    private Employee hr;
    @OneToOne
    private Requisition job;
    private Date created_date;
    private Date updated_date;
    private boolean is_deleted;

}
