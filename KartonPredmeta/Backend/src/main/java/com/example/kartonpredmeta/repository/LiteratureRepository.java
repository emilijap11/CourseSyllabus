package com.example.kartonpredmeta.repository;

import com.example.kartonpredmeta.entity.Literature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;



public interface LiteratureRepository
        extends JpaRepository<Literature, Long> {


    List<Literature> findByCourseId(Long courseId);
    void deleteByCourseId(Long courseId);

}
