package com.example.kartonpredmeta.service.impl;

import com.example.kartonpredmeta.dto.ThematicUnitDTO;
import com.example.kartonpredmeta.repository.ThematicUnitRepository;
import com.example.kartonpredmeta.service.ThematicUnitService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThematicUnitServiceImpl implements ThematicUnitService {




    private final ThematicUnitRepository repo;

    public ThematicUnitServiceImpl(ThematicUnitRepository repo) {
        this.repo = repo;
    }

    public List<ThematicUnitDTO> findByCourse(Long id) {
        return repo.findByCourseId(id)
                .stream()
                .map(t -> {
                    ThematicUnitDTO d = new ThematicUnitDTO();
                    d.setId(t.getId());
                    d.setTitle(t.getName());
                    d.setDescription(t.getDescription());
                    d.setNumberOfLectures(t.getNumberOfLectures());
                    d.setNumberOfExercises(t.getNumberOfExercises());

                    return d;
                })
                .toList();
    }
}
