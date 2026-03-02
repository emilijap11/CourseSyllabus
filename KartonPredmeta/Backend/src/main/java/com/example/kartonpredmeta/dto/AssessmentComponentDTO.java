package com.example.kartonpredmeta.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "DTO for grading ")
public class AssessmentComponentDTO {


    @Schema(description = "ID component", example = "1")
    private Long id;



    @Schema(description = "Name of the component", example = "Colloquium")
    @NotNull
    @Size(min = 2, max = 100)
    private String name;



    @Schema(description = "Description of component", example = "First colloquium")
    private String description;



    @Schema(description = "Tezina component", example = "0.4")
    @NotNull
    private Double weight;



    @Schema(description = "ID course", example = "1")
    @NotNull
    private Long courseId;



    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public Double getWeight() {
        return weight;
    }
    public Long getCourseId() {
        return courseId;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}
