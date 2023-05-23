package com.lexisnexis.hiring.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "candidate_table")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int candidateId;
    private String candidateName;
    private String candidateResume;
    private LocalDateTime appliedDate;
    private LocalDateTime selectionDate;
    private LocalDateTime level1Date;
    private LocalDateTime level2Date;

    private String Result;
    @OneToOne(mappedBy = "manager",cascade = CascadeType.ALL)
//    @JsonManagedReference
    private Employee hiringManager;
    @OneToOne
//    @JsonManagedReference
    private Requisition requisitionName;
    @CreationTimestamp
    private LocalDateTime createdDate;
    @UpdateTimestamp
    private LocalDateTime updatedDate;

}
