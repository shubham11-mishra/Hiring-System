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
@Table(name = "comments_table")
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int comment_id;
    private String result;
    private String comments;
    private String level_name;
    @OneToOne
    private Candidate candidate;
    @OneToOne
    private Employee employee;
    private Date created_date;
    private Date updated_date;
    private boolean is_deleted;
}
