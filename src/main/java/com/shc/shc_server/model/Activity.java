package com.shc.shc_server.model;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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

    @OneToMany(mappedBy = "activity", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference // Gestiona la serialización de la relación bidireccional
    private List<Student> students;

    @Column(nullable = false)
    private String department;

    @Column(nullable = true, length = 500)
    private String description;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;
}