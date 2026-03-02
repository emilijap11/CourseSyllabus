package com.example.kartonpredmeta.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "grade_components")
public class GradeComponent {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;


    private String description;


    @Column(nullable = false)
    private Integer points;

    private String name;


    private String typeDescription;

    private Integer maxPoints;

    @ManyToOne
    @JoinColumn(name = "assessment_component_id", nullable = false)
    private AssessmentComponent assessmentComponent;



    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;


    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
    public Integer getPoints() {
        return points;
    }
    public String getName() {
        return name;
    }
    public String getTypeDescription() {
        return typeDescription;
    }
    public Integer getMaxPoints() {
        return maxPoints;
    }
    public AssessmentComponent getAssessmentComponent() {
        return assessmentComponent;
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

    public void setPoints(Integer points) {
        this.points = points;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setTypeDescription(String typeDescription) {
        this.typeDescription = typeDescription;
    }
    public void setMaxPoints(Integer maxPoints) {
        this.maxPoints = maxPoints;
    }
    public void setAssessmentComponent(AssessmentComponent assessmentComponent) {

        this.assessmentComponent = assessmentComponent;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
