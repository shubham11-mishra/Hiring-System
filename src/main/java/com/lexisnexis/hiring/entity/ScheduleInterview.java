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
    private int interview_id;
    @OneToOne
    private Employee hr;
    @OneToOne
    private Candidate candidate;
    private Date interview_time;
    @OneToMany(mappedBy = "scheduleInterview",cascade = CascadeType.ALL)
    private List <Employee> panels;
    @OneToOne
    private Employee manager;
    private String level_of_interview;
    private Date created_date;
    private Date updated_date;
    private boolean is_deleted;
}
