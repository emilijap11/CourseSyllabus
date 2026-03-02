package com.example.kartonpredmeta.repository;

import com.example.kartonpredmeta.entity.CourseObjective;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseObjectiveRepository
        extends JpaRepository<CourseObjective, Long> {




    List<CourseObjective> findByCourseId(Long courseId);
    void deleteByCourseId(Long courseId);

}
