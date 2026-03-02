package com.example.kartonpredmeta.service;

import com.example.kartonpredmeta.dto.TeachingAssignmentDTO;

import java.util.List;

public interface TeachingAssignmentService {

    List<TeachingAssignmentDTO> findByCourse(Long courseId);
}
