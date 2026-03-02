package com.example.kartonpredmeta.repository;

import com.example.kartonpredmeta.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findByNameIgnoreCase(String name);
    boolean existsByNameIgnoreCase(String name);
    boolean existsByCodeIgnoreCase(String code);
    boolean existsByNameIgnoreCaseAndIdNot(String name, Long id);
    boolean existsByCodeIgnoreCaseAndIdNot(String code, Long id);

    List<Course> findByTeacherId(Long teacherId);
}
