package com.lexisnexis.hiring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "schedule_interview_table")
public class ScheduleInterview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int interviewId;
    @OneToOne
    private Employee humanResource;
    @OneToOne
    private Candidate candidate;
    private Date interviewTime;
    @OneToMany(mappedBy = "scheduleInterview",cascade = CascadeType.ALL)
    private List <Employee> panels;
    @OneToOne
    private Employee manager;
    private String levelOfInterview;
    @CreationTimestamp
    private Date createdDate;
    @UpdateTimestamp
    private Date updatedDate;
    private boolean isDeleted;
}
