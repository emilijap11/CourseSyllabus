package com.example.kartonpredmeta.service.impl;

import com.example.kartonpredmeta.dto.CourseObjectiveDTO;
import com.example.kartonpredmeta.repository.CourseObjectiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;



@Service
public class CourseObjectiveService {



    @Autowired
    private CourseObjectiveRepository repo;

    public List<CourseObjectiveDTO> findByCourse(Long id){
        return repo.findByCourseId(id)
                .stream()
                .map(o -> {
                    CourseObjectiveDTO d = new CourseObjectiveDTO();
                    d.setId(o.getObjectiveId());
                    d.setDescription(o.getDescription());
                    return d;
                })
                .toList();
    }
}

