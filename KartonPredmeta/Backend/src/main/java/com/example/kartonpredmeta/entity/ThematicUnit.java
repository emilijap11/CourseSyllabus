package com.example.kartonpredmeta.entity;

import jakarta.persistence.*;



@Entity
@Table(name = "thematic_units")
public class ThematicUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Integer numberOfLectures;

    private Integer numberOfExercises;




    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    public ThematicUnit() {}
    public ThematicUnit(String name, Course course) {
        this.name = name;
        this.course = course;
    }




    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDescription() {
        return description;
    }
    public Integer getNumberOfLectures() {
        return numberOfLectures;
    }
    public Integer getNumberOfExercises() {
        return numberOfExercises;
    }
    public Course getCourse() { return course; }


    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setNumberOfLectures(Integer numberOfLectures) {
        this.numberOfLectures = numberOfLectures;
    }
    public void setNumberOfExercises(Integer numberOfExercises) {
        this.numberOfExercises = numberOfExercises;
    }
    public void setCourse(Course course) { this.course = course; }
}
