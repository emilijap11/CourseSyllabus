package com.example.kartonpredmeta.service.impl;

import com.example.kartonpredmeta.dto.TeachingAssignmentDTO;
import com.example.kartonpredmeta.repository.TeachingAssignmentRepository;
import com.example.kartonpredmeta.service.TeachingAssignmentService;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class TeachingAssignmentServiceImpl implements TeachingAssignmentService {

    private final TeachingAssignmentRepository repository;

    public TeachingAssignmentServiceImpl(TeachingAssignmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TeachingAssignmentDTO> findByCourse(Long courseId) {
        return repository.findByCourseId(courseId)
                .stream()
                .map(ta -> {
                    TeachingAssignmentDTO dto = new TeachingAssignmentDTO();
                    dto.setId(ta.getId());
                    dto.setType(ta.getType());
                    dto.setHours(ta.getHours());
                    dto.setTeacherId(ta.getTeacher().getId());
                    dto.setCourseId(ta.getCourse().getId());
                    return dto;
                })
                .toList();
    }

}
