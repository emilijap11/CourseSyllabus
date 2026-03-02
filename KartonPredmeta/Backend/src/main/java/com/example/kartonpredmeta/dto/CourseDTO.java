package com.example.kartonpredmeta.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "DTO za predmet")
public class CourseDTO {

    @Schema(description = "ID predmeta", example = "1")
    private Long id;

    @Schema(description = "Naziv predmeta", example = "Programiranje 1")
    @NotNull
    @Size(min = 2, max = 100)
    private String name;

    @Schema(description = "Sifra predmeta", example = "PRG1")
    @Size(max = 50)
    private String code;

    @Schema(description = "ECTS bodovi", example = "6")
    private Integer ects;
    @Schema(description = "Opis predmeta", example = "Uvod u osnove programiranja")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getEcts() {
        return ects;
    }

    public void setEcts(Integer ects) {
        this.ects = ects;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
