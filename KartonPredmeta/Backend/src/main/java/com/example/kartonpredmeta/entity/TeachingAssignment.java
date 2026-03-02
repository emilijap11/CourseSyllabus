package com.example.kartonpredmeta.entity;

import com.example.kartonpredmeta.entity.Teacher;
import jakarta.persistence.*;



@Entity
@Table(name = "teaching_assignments")
public class TeachingAssignment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private Integer hours;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;



    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;





    public Long getId() {
        return id;
    }
    public String getType() {
        return type;
    }
    public Integer getHours() {
        return hours;
    }
    public Course getCourse() {
        return course;
    }
    public Teacher getTeacher() {
        return teacher;
    }


    public void setId(Long id) {
        this.id = id;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setHours(Integer hours) {
        this.hours = hours;
    }
    public void setCourse(Course course) {
        this.course = course;
    }
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
