package com.example.kartonpredmeta.service;

import com.example.kartonpredmeta.dto.LearningOutcomeDTO;
import com.example.kartonpredmeta.entity.LearningOutcome;

import java.util.List;



public interface LearningOutcomeService {
    LearningOutcome create(LearningOutcome learningOutcome);
    List<LearningOutcomeDTO> findByCourse(Long courseId);
}
