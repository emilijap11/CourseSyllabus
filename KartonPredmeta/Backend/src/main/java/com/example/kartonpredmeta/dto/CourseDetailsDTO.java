package com.example.kartonpredmeta.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;


@Schema(description = "Detalji predmeta sa svim povezanim podacima")
public class CourseDetailsDTO {


    @Schema(description = "ID predmeta", example = "1")
    private Long id;

    @Schema(description = "Naziv predmeta", example = "Programiranje 1")
    private String name;
    @Schema(description = "Sifra predmeta", example = "PRG1")
    private String code;

    @Schema(description = "ECTS bodovi", example = "6")
    private Integer ects;
    @Schema(description = "Opis predmeta", example = "Uvod u osnove programiranja")
    private String description;



    @Schema(description = "Nosilac predmeta")
    private TeacherDTO teacher;


    @Schema(description = "Ciljevi predmeta")
    private List<CourseObjectiveDTO> courseObjectives;

    @Schema(description = "Ishodi ucenja")
    private List<LearningOutcomeDTO> learningOutcomes;
    @Schema(description = "Literatura")

    private List<LiteratureDTO> literature;
    @Schema(description = "Tematske jedinice")
    private List<ThematicUnitDTO> thematicUnits;

    @Schema(description = "Angazovanja nastavnika")
    private List<TeachingAssignmentDTO> teachingAssignments;

    @Schema(description = "Komponente ocenjivanja")
    private List<AssessmentComponentDTO> assessmentComponents;


    public CourseDetailsDTO() {}


    public Long getId() { return id; }
    public String getName() { return name; }

    public String getCode() { return code; }
    public Integer getEcts() { return ects; }
    public String getDescription() { return description; }
    public TeacherDTO getTeacher() { return teacher; }
    public List<CourseObjectiveDTO> getCourseObjectives() { return courseObjectives; }
    public List<LearningOutcomeDTO> getLearningOutcomes() { return learningOutcomes; }
    public List<LiteratureDTO> getLiterature() { return literature; }
    public List<ThematicUnitDTO> getThematicUnits() { return thematicUnits; }
    public List<TeachingAssignmentDTO> getTeachingAssignments() { return teachingAssignments; }
    public List<AssessmentComponentDTO> getAssessmentComponents() { return assessmentComponents; }


    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setCode(String code) { this.code = code; }

    public void setEcts(Integer ects) { this.ects = ects; }
    public void setDescription(String description) { this.description = description; }
    public void setTeacher(TeacherDTO teacher) { this.teacher = teacher; }
    public void setCourseObjectives(List<CourseObjectiveDTO> courseObjectives) {
        this.courseObjectives = courseObjectives;
    }
    public void setLearningOutcomes(List<LearningOutcomeDTO> learningOutcomes) {
        this.learningOutcomes = learningOutcomes;
    }
    public void setLiterature(List<LiteratureDTO> literature) {
        this.literature = literature;
    }

    public void setThematicUnits(List<ThematicUnitDTO> thematicUnits) {
        this.thematicUnits = thematicUnits;
    }
    public void setTeachingAssignments(List<TeachingAssignmentDTO> teachingAssignments) {
        this.teachingAssignments = teachingAssignments;
    }
    public void setAssessmentComponents(List<AssessmentComponentDTO> assessmentComponents) {
        this.assessmentComponents = assessmentComponents;
    }
}
