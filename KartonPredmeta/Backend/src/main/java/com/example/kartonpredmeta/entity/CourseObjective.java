package com.example.kartonpredmeta.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "course_objective")
public class CourseObjective {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long objectiveId;

    private String description;



    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;


    public Long getObjectiveId() {
        return objectiveId;
    }
    public String getDescription() {
        return description;
    }
    public Course getCourse() {
        return course;
    }



    public void setObjectiveId(Long objectiveId) {
        this.objectiveId = objectiveId;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setCourse(Course course) {
        this.course = course;
    }
}
