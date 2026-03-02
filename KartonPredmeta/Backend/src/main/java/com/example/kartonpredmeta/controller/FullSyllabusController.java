package com.example.kartonpredmeta.controller;

import com.example.kartonpredmeta.dto.CourseDTO;
import com.example.kartonpredmeta.dto.FullSyllabusDTO;
import com.example.kartonpredmeta.service.FullSyllabusService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/syllabus")
public class FullSyllabusController {


    private final FullSyllabusService service;
    public FullSyllabusController(FullSyllabusService service) {
        this.service = service;
    }



    @PostMapping
    public FullSyllabusDTO create(@RequestBody FullSyllabusDTO dto) {
        return service.createFullSyllabus(dto);
    }


    @GetMapping("/{courseId}")
    public FullSyllabusDTO getByCourse(@PathVariable Long courseId) {
        return service.getFullSyllabus(courseId);
    }


    @PutMapping("/{courseId}")
    public FullSyllabusDTO update(@PathVariable Long courseId,
                                  @RequestBody FullSyllabusDTO dto) {
        return service.updateSyllabus(courseId, dto);
    }


    @DeleteMapping("/{courseId}")
    public ResponseEntity<Void> delete(@PathVariable Long courseId) {
        service.deleteSyllabus(courseId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{courseId}/delete")
    public ResponseEntity<Void> deleteViaPost(@PathVariable Long courseId) {
        service.deleteSyllabus(courseId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/all")
    public List<CourseDTO> getAllCourses() {
        return service.getAllCourses();
    }

}
