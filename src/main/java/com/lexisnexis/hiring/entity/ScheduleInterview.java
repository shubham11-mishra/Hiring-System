package com.lexisnexis.hiring.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
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
    private LocalDateTime interviewTime;
//    @OneToMany(mappedBy = "scheduleInterview",cascade = CascadeType.ALL)
//    @JsonManagedReference
//    private List <Employee> panels;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "interview_table",
            joinColumns = {
                    @JoinColumn(name = "interview_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "employee_id")
            }
    )
    private Set<Employee> panels;
    @OneToOne
    private Employee manager;
    private String levelOfInterview;
    @CreationTimestamp
    private LocalDateTime createdDate;
    @UpdateTimestamp
    private LocalDateTime updatedDate;
}
