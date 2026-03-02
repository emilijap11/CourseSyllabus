package com.example.kartonpredmeta.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO za literaturu")
public class LiteratureDTO {

    @Schema(description = "ID literature", example = "1")
    private Long id;
    @Schema(description = "Naslov", example = "Uvod u baze podataka")
    private String title;
    @Schema(description = "Autor", example = "N. Autor")
    private String author;
    @Schema(description = "Godina izdanja", example = "2020")
    private int year;
    @Schema(description = "Tip literature", example = "OBAVEZNA")
    private String type;

    public LiteratureDTO(Long id, String title, String author, int year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
    }
    public LiteratureDTO(Long id, String title, String author, int year, String type) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.type = type;
    }


    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public Integer getYear() { return year; }
    public String getType() { return type; }
}
