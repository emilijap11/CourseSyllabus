package com.example.kartonpredmeta.repository;

import com.example.kartonpredmeta.entity.GradeComponent;
import org.springframework.data.jpa.repository.JpaRepository;



public interface GradeComponentRepository
        extends JpaRepository<GradeComponent, Long> {
}
