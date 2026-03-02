package com.example.kartonpredmeta.repository;

import com.example.kartonpredmeta.entity.TeachingAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeachingAssignmentRepository
        extends JpaRepository<TeachingAssignment, Long> {




    List<TeachingAssignment> findByCourseId(Long courseId);
    void deleteByCourseId(Long courseId);
    List<TeachingAssignment> findByTeacherId(Long teacherId);
    void deleteByTeacherId(Long teacherId);
    java.util.Optional<TeachingAssignment> findByCourseIdAndTeacherId(Long courseId, Long teacherId);
}
