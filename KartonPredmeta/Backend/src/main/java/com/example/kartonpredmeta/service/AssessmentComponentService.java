package com.example.kartonpredmeta.service;
import com.example.kartonpredmeta.dto.AssessmentComponentDTO;
import com.example.kartonpredmeta.entity.AssessmentComponent;

import java.util.List;
public interface AssessmentComponentService {
    AssessmentComponent create(AssessmentComponent component);
    List<AssessmentComponentDTO> findByCourse(Long courseId);
}
