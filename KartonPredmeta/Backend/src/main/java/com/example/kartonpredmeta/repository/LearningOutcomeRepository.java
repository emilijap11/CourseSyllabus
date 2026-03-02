package com.example.kartonpredmeta.repository;

import com.example.kartonpredmeta.entity.LearningOutcome;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;




public interface LearningOutcomeRepository
        extends JpaRepository<LearningOutcome, Long> {

    List<LearningOutcome> findByCourseId(Long courseId);
    void deleteByCourseId(Long courseId);

}
