package com.example.kartonpredmeta.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, length = 100)
    private String firstName;


    @Column(nullable = false, length = 100)
    private String lastName;


    @Column(unique = true)
    private String email;

    private String title;

    private String academicTitle;

    private String institution;



    public Long getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmail() {
        return email;
    }
    public String getTitle() {
        return title;
    }
    public String getAcademicTitle() {
        return academicTitle;
    }
    public String getInstitution() {
        return institution;
    }


    public void setId(Long id) {
        this.id = id;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setAcademicTitle(String academicTitle) {
        this.academicTitle = academicTitle;
    }
    public void setInstitution(String institution) {
        this.institution = institution;
    }
}
