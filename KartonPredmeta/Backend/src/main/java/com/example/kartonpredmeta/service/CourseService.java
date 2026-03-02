package com.example.kartonpredmeta.service;

import com.example.kartonpredmeta.entity.Course;

import java.util.List;

public interface CourseService {
    Course create(Course course);
    List<Course> findAll();
    Course findById(Long id);
    Course update(Long id, Course course);
    void delete(Long id);
}

