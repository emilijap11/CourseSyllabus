package com.example.kartonpredmeta.service.impl;

import com.example.kartonpredmeta.entity.Teacher;
import com.example.kartonpredmeta.repository.CourseRepository;
import com.example.kartonpredmeta.repository.TeacherRepository;
import com.example.kartonpredmeta.repository.TeachingAssignmentRepository;

import com.example.kartonpredmeta.service.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository repository;
    private final CourseRepository courseRepository;
    private final TeachingAssignmentRepository teachingAssignmentRepository;

    public TeacherServiceImpl(TeacherRepository repository,
                              CourseRepository courseRepository,
                              TeachingAssignmentRepository teachingAssignmentRepository) {
        this.repository = repository;
        this.courseRepository = courseRepository;
        this.teachingAssignmentRepository = teachingAssignmentRepository;
    }



    @Override
    public Teacher create(Teacher teacher) {
        if (teacher.getEmail() != null) {
            repository.findByEmail(teacher.getEmail()).ifPresent(existing -> {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
            });
        }
        return repository.save(teacher);
    }



    @Override
    public List<Teacher> findAll() {
        return repository.findAll();
    }



    @Override
    public Teacher findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Teacher with id " + id + " not found"));
    }



    @Override
    public Teacher update(Long id, Teacher teacher) {
        Teacher existing = findById(id);

        existing.setFirstName(teacher.getFirstName());
        existing.setLastName(teacher.getLastName());
        existing.setEmail(teacher.getEmail());
        existing.setTitle(teacher.getTitle());

        return repository.save(existing);
    }



    @Override
    @org.springframework.transaction.annotation.Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher not found");
        }
        courseRepository.findByTeacherId(id)
                .forEach(c -> {
                    c.setTeacher(null);
                    courseRepository.save(c);
                });
        teachingAssignmentRepository.deleteByTeacherId(id);
        repository.deleteById(id);
    }
}
