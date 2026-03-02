package com.example.kartonpredmeta.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "DTO za nastavnika")
public class TeacherDTO {

    @Schema(description = "Ime nastavnika", example = "Ana")
    @NotNull
    @Size(min = 2, max = 100)
    private String firstName;
    @Schema(description = "ID nastavnika", example = "1")
    private Long id;
    @Schema(description = "Prezime nastavnika", example = "Ivic")
    @NotNull
    @Size(min = 2, max = 100)
    private String lastName;

    @Schema(description = "Email nastavnika", example = "ana.ivic@example.com")
    @NotNull
    @Email
    private String email;
    @Schema(description = "ID predmeta za povezivanje", example = "1")
    private Long courseId;
    @Schema(description = "Naziv predmeta za povezivanje", example = "Programiranje 1")
    private String courseName;


    @Schema(description = "Zvanje", example = "Prof.")
    private String title;
    @Schema(description = "Akademsko zvanje", example = "Dr")
    private String academicTitle;
    @Schema(description = "Institucija", example = "FON")
    private String institution;


    public Long getId() { return id; }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmail() {
        return email;
    }
    public Long getCourseId() { return courseId; }
    public String getCourseName() { return courseName; }
    public String getTitle() {
        return title;
    }
    public String getAcademicTitle() {
        return academicTitle;
    }
    public String getInstitution() {
        return institution;
    }


    public void setId(Long id) { this.id = id; }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setCourseId(Long courseId) { this.courseId = courseId; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
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
