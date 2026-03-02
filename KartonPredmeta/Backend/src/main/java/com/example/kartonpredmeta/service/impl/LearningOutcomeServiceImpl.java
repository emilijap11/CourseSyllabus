package com.example.kartonpredmeta.service.impl;

import com.example.kartonpredmeta.dto.LearningOutcomeDTO;
import com.example.kartonpredmeta.entity.LearningOutcome;
import com.example.kartonpredmeta.repository.LearningOutcomeRepository;
import com.example.kartonpredmeta.service.LearningOutcomeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LearningOutcomeServiceImpl implements LearningOutcomeService {
    private final LearningOutcomeRepository repository;
    public LearningOutcomeServiceImpl(LearningOutcomeRepository repository) {
        this.repository = repository;
    }





    @Override
    public LearningOutcome create(LearningOutcome learningOutcome) {
        return repository.save(learningOutcome);
    }


    @Override
    public List<LearningOutcomeDTO> findByCourse(Long courseId) {
        return repository.findAll()
                .stream()
                .filter(lo -> lo.getCourse().getId().equals(courseId))
                .map(lo -> new LearningOutcomeDTO(
                        lo.getId(),
                        lo.getDescription()
                ))
                .toList();
    }

}
