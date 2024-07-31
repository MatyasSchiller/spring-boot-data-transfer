package com.example.springbootdatatransfer.model;

import javax.persistence.*;

@Entity
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String examAspect;
    private int grade;

    @ManyToOne
    @JoinColumn(name = "exam_opportunity_id")
    private ExamOpportunity examOpportunity;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExamAspect() {
        return examAspect;
    }

    public void setExamAspect(String examAspect) {
        this.examAspect = examAspect;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public ExamOpportunity getExamOpportunity() {
        return examOpportunity;
    }

    public void setExamOpportunity(ExamOpportunity examOpportunity) {
        this.examOpportunity = examOpportunity;
    }
}
