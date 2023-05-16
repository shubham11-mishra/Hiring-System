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
@Table(name = "requisition_table")
public class Requisition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int job_id;
    private String job_profile;
    private String job_description;
    private String project_name;
    private String partner_name;
    private String status;
    private Date timeslot;
    private int no_panels;
    @OneToOne
    private Employee specific_panel;
    private String additional_condition;
    @OneToOne
    private  Employee manager;
    private Date created_date;
    private  Date updated_date;
    private boolean is_deleted;
}
