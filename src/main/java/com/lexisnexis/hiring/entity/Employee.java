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
    private int employeeId;
    private String employeeName;
    private String employeePassword;
    @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL)
    private List<Role> roles;
    @OneToOne
    private Employee manager;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "schedule_interview_id")
    private ScheduleInterview scheduleInterview;
    private Date createdDate;
    private Date updatedDate;
    private boolean isDeleted;
}
