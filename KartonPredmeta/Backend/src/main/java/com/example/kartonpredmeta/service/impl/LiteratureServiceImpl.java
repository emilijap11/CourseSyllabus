package com.example.kartonpredmeta.service.impl;

import com.example.kartonpredmeta.dto.LiteratureDTO;
import com.example.kartonpredmeta.repository.LiteratureRepository;
import com.example.kartonpredmeta.service.LiteratureService;
import org.springframework.stereotype.Service;

import java.util.List;




@Service
public class LiteratureServiceImpl implements LiteratureService {
    private final LiteratureRepository repository;
    public LiteratureServiceImpl(LiteratureRepository repository) {
        this.repository = repository;
    }



    @Override
    public List<LiteratureDTO> findByCourse(Long courseId) {
        return repository.findByCourseId(courseId)
                .stream()
                .map(l -> new LiteratureDTO(
                        l.getId(),
                        l.getTitle(),
                        l.getAuthor(),
                        l.getYear(),
                        l.getType()
                ))
                .toList();}
}
