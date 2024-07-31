package com.example.springbootdatatransfer.model;

import javax.persistence.*;

@Entity
@Table(name = "results")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String examAspect;
    private int grade;
    private Long examOpportunityId;

    // Getters and Setters
}

