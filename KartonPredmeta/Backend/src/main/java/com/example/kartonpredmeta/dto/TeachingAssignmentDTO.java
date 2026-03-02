package com.example.kartonpredmeta.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO za angazovanje nastavnika")
public class TeachingAssignmentDTO {

    @Schema(description = "ID angazovanja", example = "1")
    private Long id;
    @Schema(description = "Tip angazovanja", example = "PREDAVANJA")
    private String type;
    @Schema(description = "Broj casova", example = "30")
    private Integer hours;
    @Schema(description = "ID nastavnika", example = "1")
    private Long teacherId;
    @Schema(description = "ID predmeta", example = "1")
    private Long courseId;

    public TeachingAssignmentDTO() {
    }


    public Long getId() { return id; }
    public String getType() { return type; }
    public Integer getHours() { return hours; }
    public Long getTeacherId() { return teacherId; }
    public Long getCourseId() { return courseId; }


    public void setId(Long id) { this.id = id; }
    public void setType(String type) { this.type = type; }
    public void setHours(Integer hours) { this.hours = hours; }
    public void setTeacherId(Long teacherId) { this.teacherId = teacherId; }
    public void setCourseId(Long courseId) { this.courseId = courseId; }
}
