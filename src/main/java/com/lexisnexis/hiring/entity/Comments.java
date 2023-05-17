package com.lexisnexis.hiring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comments_table")
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentId;
    private String result;
    private String comments;
    private String levelName;
    @OneToOne
    private Candidate candidate;
    @OneToOne
    private Employee employee;
    private Date createdDate;
    private Date updatedDate;
    private boolean isDeleted;
}
