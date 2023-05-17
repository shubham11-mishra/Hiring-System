package com.lexisnexis.hiring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Employee hiringManager;
    @OneToOne
    private Candidate candidate;
    private Date interviewTime;
    @OneToMany(mappedBy = "scheduleInterview",cascade = CascadeType.ALL)
    private List <Employee> panels;
    @OneToOne
    private Employee manager;
    private String levelOfInterview;
    private Date createdDate;
    private Date updatedDate;
    private boolean isDeleted;
}
