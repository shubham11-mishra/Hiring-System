package com.lexisnexis.hiring.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    @JsonBackReference
    private ScheduleInterview scheduleInterview;
    @CreationTimestamp
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime updatedDate;
    private boolean isDeleted = false;
}
