package com.shc.shc_server.model;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Table(name = "activities")
@Data
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Time startTime;

    @Column(nullable = false)
    private Time endTime;

    @Column(nullable = false)
    private Double multiplier;

    @Column(nullable = false)
    private Integer scholarshipHoursOffered;

    @Column(nullable = false)
    private String coordinator;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private Integer maxCapacity;

    @Column(nullable = false)
    private Boolean complete;

    private List<Long> students;

    @Column(nullable = false)
    private String department;

    @Column(nullable = true, length = 500)
    private String description;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;
}