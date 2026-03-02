package com.example.kartonpredmeta.service.impl;

import com.example.kartonpredmeta.dto.AssessmentComponentDTO;
import com.example.kartonpredmeta.entity.AssessmentComponent;
import com.example.kartonpredmeta.repository.AssessmentComponentRepository;
import com.example.kartonpredmeta.service.AssessmentComponentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssessmentComponentServiceImpl
        implements AssessmentComponentService {



    private final AssessmentComponentRepository repository;
    public AssessmentComponentServiceImpl(AssessmentComponentRepository repository) {
        this.repository = repository;
    }

    @Override
    public AssessmentComponent create(AssessmentComponent component) {
        return repository.save(component);
    }

    @Override
    public List<AssessmentComponentDTO> findByCourse(Long courseId) {
        return repository.findAll()
                .stream()
                .filter(c -> c.getCourse().getId().equals(courseId))
                .map(c -> {
                    AssessmentComponentDTO dto = new AssessmentComponentDTO();
                    dto.setId(c.getId());
                    dto.setName(c.getName());
                    dto.setCourseId(c.getCourse().getId());
                    return dto;
                })
                .toList();
    }

}
