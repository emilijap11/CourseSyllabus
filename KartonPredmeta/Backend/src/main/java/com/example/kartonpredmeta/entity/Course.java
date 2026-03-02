package com.example.kartonpredmeta.entity;

import com.example.kartonpredmeta.entity.Teacher;
import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "courses")
public class Course {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;





    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 50)
    private String code;


    @Column
    private Integer ects;
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    private String description;




    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getCode() {
        return code;
    }
    public Integer getEcts() {
        return ects;
    }
    public Teacher getTeacher() {
        return teacher;}
    public String getDescription() {
        return description;
    }


    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public void setEcts(Integer ects) {
        this.ects = ects;
    }
     public void setTeacher(Teacher teacher) {
       this.teacher = teacher;}
    public void setDescription(String description) {
        this.description = description;
    }
}

