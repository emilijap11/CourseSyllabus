package com.example.kartonpredmeta.repository;
import com.example.kartonpredmeta.entity.AssessmentComponent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;




public interface AssessmentComponentRepository
        extends JpaRepository<AssessmentComponent, Long> {
    List<AssessmentComponent> findByCourseId(Long courseId);
    void deleteByCourseId(Long courseId);

}
