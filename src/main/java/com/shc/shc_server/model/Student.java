package com.shc.shc_server.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "students")
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String major;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    private Double scholarshipHours;

    @Column(nullable = false)
    private Double completedScholarshipHours;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "student_previous_activities",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "activity_id")
    )
    private List<Activity> previousActivities;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "student_preferred_activities",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "activity_id")
    )
    private List<Activity> preferredActivities;

    @Column(length = 500)
    private String aboutMe;

    @Column(nullable = false)
    private Double score;

    @ManyToOne
    @JoinColumn(name = "new_activity_id")
    private Activity newActivity;

    @ManyToOne
    @JoinColumn(name = "activity_id")
    @JsonBackReference
    private Activity activity;
}
