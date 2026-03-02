package com.example.kartonpredmeta.service;

import com.example.kartonpredmeta.dto.LiteratureDTO;

import java.util.List;

public interface LiteratureService {
    List<LiteratureDTO> findByCourse(Long courseId);}
