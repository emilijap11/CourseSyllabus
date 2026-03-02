package com.example.kartonpredmeta.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "literature")
public class Literature {





    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String author;

    private Integer year;

    @Column(nullable = false)
    private String type;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;


    public Literature() {}

    public Literature(String title, Course course) {
        this.title = title;
        this.course = course;
    }


    public Long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public Integer getYear() {
        return year;
    }
    public String getType() {
        return type;
    }
    public Course getCourse() {
        return course;
    }



    public void setTitle(String title) {
        this.title = title;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setCourse(Course course) {
        this.course = course;
    }
}
