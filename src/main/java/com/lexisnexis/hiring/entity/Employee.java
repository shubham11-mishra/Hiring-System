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
@Table(name = "employee_table")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employee_id;
    private String employee_name;
    @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL)
    private List<Role> roles;
    @OneToOne
    private Employee manager;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "schedule_interview_id")
    private ScheduleInterview scheduleInterview;
    private Date created_date;
    private Date updated_date;
    private boolean is_deleted;
}
