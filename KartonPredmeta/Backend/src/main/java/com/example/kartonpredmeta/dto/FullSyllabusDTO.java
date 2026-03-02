package com.example.kartonpredmeta.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "Kompletan karton predmeta (agregirani prikaz)")
public class FullSyllabusDTO {

    @Schema(description = "Glavni nastavnik")
    private TeacherDTO teacher;
    @Schema(description = "Lista nastavnika")
    private List<TeacherDTO> teachers;
    @Schema(description = "Podaci o predmetu")
    private CourseDTO course;
    @Schema(description = "Ciljevi predmeta")
    private List<String> courseObjectives;
    @Schema(description = "Ishodi ucenja")
    private List<String> learningOutcomes;
    @Schema(description = "Literatura")
    private List<String> literature;
    @Schema(description = "Tematske jedinice")
    private List<String> thematicUnits;
    @Schema(description = "Komponente ocenjivanja")
    private List<String> assessmentComponents;

    public FullSyllabusDTO() {}


    public TeacherDTO getTeacher() {
        return teacher;
    }
    public List<TeacherDTO> getTeachers() {
        return teachers;
    }
    public CourseDTO getCourse() {
        return course;
    }
    public List<String> getCourseObjectives() {
        return courseObjectives;
    }
    public List<String> getLearningOutcomes() {
        return learningOutcomes;
    }
    public List<String> getLiterature() {
        return literature;
    }
    public List<String> getThematicUnits() {
        return thematicUnits;
    }
    public List<String> getAssessmentComponents() {
        return assessmentComponents;
    }


    public void setTeacher(TeacherDTO teacher) {
        this.teacher = teacher;
    }
    public void setTeachers(List<TeacherDTO> teachers) {
        this.teachers = teachers;
    }
    public void setCourse(CourseDTO course) {
        this.course = course;
    }
    public void setCourseObjectives(List<String> courseObjectives) {
        this.courseObjectives = courseObjectives;
    }
    public void setLearningOutcomes(List<String> learningOutcomes) {
        this.learningOutcomes = learningOutcomes;
    }
    public void setLiterature(List<String> literature) {
        this.literature = literature;
    }
    public void setThematicUnits(List<String> thematicUnits) {
        this.thematicUnits = thematicUnits;
    }
    public void setAssessmentComponents(List<String> assessmentComponents) {
        this.assessmentComponents = assessmentComponents;
    }
}
