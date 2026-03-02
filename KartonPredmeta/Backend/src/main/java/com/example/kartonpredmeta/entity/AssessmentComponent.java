package com.example.kartonpredmeta.entity;

import com.example.kartonpredmeta.dto.TeachingAssignmentDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "assessment_components")
public class AssessmentComponent {
    public AssessmentComponent() {}



    public AssessmentComponent(String name, Course course) {
        this.name = name;
        this.course = course;
    }

    public AssessmentComponent(String name, Integer maxPoints, Course course) {
        this.name = name;
        this.maxPoints = maxPoints;
        this.course = course;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer maxPoints;

    private String type;

    private Integer points;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;





    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Integer getMaxPoints() {
        return maxPoints;
    }
    public String getType() {
        return type;
    }
    public Integer getPoints() {
        return points;
    }
    public Course getCourse() {
        return course;
    }




    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setMaxPoints(Integer maxPoints) {
        this.maxPoints = maxPoints;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setPoints(Integer points) {
        this.points = points;
    }
    public void setCourse(Course course) {
        this.course = course;
    }
}
