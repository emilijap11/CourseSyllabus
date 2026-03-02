package com.example.kartonpredmeta.service.impl;

import com.example.kartonpredmeta.entity.Course;
import com.example.kartonpredmeta.repository.CourseRepository;
import com.example.kartonpredmeta.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository repository;
    public CourseServiceImpl(CourseRepository repository) {
        this.repository = repository;
    }



    @Override
    public Course create(Course course) {
        if (course.getName() != null && repository.existsByNameIgnoreCase(course.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Course already exists");
        }
        if (course.getCode() != null && !course.getCode().isBlank() && repository.existsByCodeIgnoreCase(course.getCode())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Course code already exists");
        }
        Course saved = repository.save(course);
        if (saved.getCode() == null || saved.getCode().isBlank()) {
            saved.setCode(String.valueOf(saved.getId()));
            saved = repository.save(saved);
        }
        return saved;
    }


    @Override
    public List<Course> findAll() {
        return repository.findAll();
    }

    @Override
    public Course findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }



    @Override
    public Course update(Long id, Course course) {
        if (course.getName() != null && repository.existsByNameIgnoreCaseAndIdNot(course.getName(), id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Course already exists");
        }
        if (course.getCode() != null && !course.getCode().isBlank() && repository.existsByCodeIgnoreCaseAndIdNot(course.getCode(), id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Course code already exists");
        }
        Course existing = findById(id);
        existing.setName(course.getName());
        existing.setEcts(course.getEcts());
        existing.setDescription(course.getDescription());
        return repository.save(existing);
    }
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
