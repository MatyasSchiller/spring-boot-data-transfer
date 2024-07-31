package com.example.springbootdatatransfer.model;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "exam_opportunities")
public class ExamOpportunity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date date;

    private String subject;
    private String instructor;
    private String student;
    private boolean passed;
    private String comments;

    // Getters and Setters
}

