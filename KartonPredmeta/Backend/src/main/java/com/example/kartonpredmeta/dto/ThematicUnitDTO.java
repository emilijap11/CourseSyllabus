package com.example.kartonpredmeta.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO za tematsku jedinicu")
public class ThematicUnitDTO {

    @Schema(description = "ID tematske jedinice", example = "1")
    private Long id;
    @Schema(description = "Naziv", example = "Uvod u Javu")
    private String title;
    @Schema(description = "Opis", example = "Osnovni pojmovi i sintaksa")
    private String description;
    @Schema(description = "Broj predavanja", example = "2")
    private Integer numberOfLectures;
    @Schema(description = "Broj vezbi", example = "2")
    private Integer numberOfExercises;


    public Long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public Integer getNumberOfLectures() {
        return numberOfLectures;
    }
    public Integer getNumberOfExercises() {
        return numberOfExercises;
    }


    public void setId(Long id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setNumberOfLectures(Integer numberOfLectures) {
        this.numberOfLectures = numberOfLectures;
    }
    public void setNumberOfExercises(Integer numberOfExercises) {
        this.numberOfExercises = numberOfExercises;
    }
}
