package com.example.kartonpredmeta.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "DTO za komponentu ocene")
public class GradeComponentDTO {

    @Schema(description = "ID komponente ocene", example = "1")
    private Long id;

    @Schema(description = "Opis komponente", example = "Teorijski deo")
    private String description;

    @Schema(description = "Broj poena", example = "20")
    @NotNull
    private Integer points;
}
