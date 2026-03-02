package com.example.kartonpredmeta.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO za cilj predmeta")
public class CourseObjectiveDTO {

    @Schema(description = "ID cilja", example = "1")
    private Long id;
    @Schema(description = "Opis cilja", example = "Upoznati studente sa osnovama")
    private String description;


    public Long getId() {
        return id;
    }
    public String getDescription() {
        return description;
    }


    public void setId(Long id) {
        this.id = id;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
