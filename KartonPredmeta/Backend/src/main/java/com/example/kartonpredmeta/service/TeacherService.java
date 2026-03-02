package com.example.kartonpredmeta.service;

import com.example.kartonpredmeta.entity.Teacher;

import java.util.List;



public interface TeacherService {
    Teacher create(Teacher teacher);
    List<Teacher> findAll();
    Teacher findById(Long id);
    Teacher update(Long id, Teacher teacher);
    void delete(Long id);
}
