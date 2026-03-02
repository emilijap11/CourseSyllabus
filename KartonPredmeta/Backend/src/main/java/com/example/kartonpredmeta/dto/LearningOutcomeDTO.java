package com.example.kartonpredmeta.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO za ishod ucenja")
public class LearningOutcomeDTO {

    @Schema(description = "ID ishoda", example = "1")
    private Long id;
    @Schema(description = "Opis ishoda", example = "Student razume osnovne koncepte")
    private String description;

    public LearningOutcomeDTO(Long id, String description) {
        this.id = id;
        this.description = description;
    }


    public Long getId() { return id; }
    public String getDescription() { return description; }
}
