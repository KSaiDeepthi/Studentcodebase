package com.example.live.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "MARKS")
public class Marks implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "CurrentScore")
    private float currentScore;

    @ManyToOne
    @JoinColumn(name = "StudentId")
    private StudentProfile studentProfile;

    @Column(name = "AverageScore")
    private float averageScore;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    private Date createdDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(float currentScore) {
        this.currentScore = currentScore;
    }

    public StudentProfile getStudentProfile() {
        return studentProfile;
    }

    public void setStudentProfile(StudentProfile studentProfile) {
        this.studentProfile = studentProfile;
    }

    public float getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(float averageScore) {
        this.averageScore = averageScore;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date created_date) {
        this.createdDate = createdDate;
    }
}
