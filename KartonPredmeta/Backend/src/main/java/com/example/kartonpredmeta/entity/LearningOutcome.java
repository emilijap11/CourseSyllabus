package com.example.kartonpredmeta.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "learning_outcomes")
public class LearningOutcome {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column(nullable = false, length = 500)
    private String description;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;



    public LearningOutcome() {}



    public LearningOutcome(String description, Course course) {
        this.description = description;
        this.course = course;
    }


    public Long getId() {
        return id;
    }
    public String getDescription() {
        return description;
    }

    public Course getCourse() {
        return course;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public void setCourse(Course course) {
        this.course = course;
    }
}
