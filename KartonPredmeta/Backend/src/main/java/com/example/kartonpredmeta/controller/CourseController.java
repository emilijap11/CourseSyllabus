package com.example.kartonpredmeta.controller;
import com.example.kartonpredmeta.dto.*;
import com.example.kartonpredmeta.mapper.CourseMapper;
import com.example.kartonpredmeta.entity.Course;
import com.example.kartonpredmeta.repository.CourseRepository;
import com.example.kartonpredmeta.service.*;
import com.example.kartonpredmeta.service.impl.CourseObjectiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    private final CourseObjectiveService courseObjectiveService;
    private final LiteratureService literatureService;
    private final ThematicUnitService thematicUnitService;

    private final CourseService courseService;

    private final CourseMapper courseMapper;
    private final LearningOutcomeService learningOutcomeService;
    private final TeachingAssignmentService teachingAssignmentService;
    private final AssessmentComponentService assessmentComponentService;
    private final CourseRepository courseRepository;

    public CourseController(
            CourseService courseService,
            CourseMapper courseMapper,
            LearningOutcomeService learningOutcomeService,
            TeachingAssignmentService teachingAssignmentService,
            AssessmentComponentService assessmentComponentService,
            CourseRepository courseRepository,
            CourseObjectiveService courseObjectiveService,
            LiteratureService literatureService,
            ThematicUnitService thematicUnitService
    ) {
        this.courseService = courseService;
        this.courseMapper = courseMapper;
        this.learningOutcomeService = learningOutcomeService;
        this.teachingAssignmentService = teachingAssignmentService;
        this.assessmentComponentService = assessmentComponentService;
        this.courseRepository = courseRepository;
        this.courseObjectiveService = courseObjectiveService;
        this.literatureService = literatureService;
        this.thematicUnitService = thematicUnitService;
    }
    @GetMapping
    public List<CourseDTO> getAllCourses() {
        return courseService.findAll()
                .stream()
                .map(courseMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public CourseDetailsDTO getCourseDetails(@PathVariable Long id) {

        Course course = courseService.findById(id);

        CourseDetailsDTO dto = new CourseDetailsDTO();

        dto.setId(course.getId());
        dto.setName(course.getName());
        dto.setCode(course.getCode());
        dto.setEcts(course.getEcts());
        dto.setDescription(course.getDescription());

        if(course.getTeacher() != null){
            TeacherDTO t = new TeacherDTO();
            t.setId(course.getTeacher().getId());
            t.setFirstName(course.getTeacher().getFirstName());
            t.setLastName(course.getTeacher().getLastName());
            dto.setTeacher(t);
        }

        dto.setLearningOutcomes(
                learningOutcomeService.findByCourse(id)
        );

        dto.setTeachingAssignments(
                teachingAssignmentService.findByCourse(id)
        );

        dto.setAssessmentComponents(
                assessmentComponentService.findByCourse(id)
        );

        dto.setCourseObjectives(
                courseObjectiveService.findByCourse(id)
        );

        dto.setLiterature(
                literatureService.findByCourse(id)
        );

        dto.setThematicUnits(
                thematicUnitService.findByCourse(id)
        );

        return dto;
    }


    @PostMapping
    public CourseDTO create(@RequestBody CourseDTO dto) {
        Course saved = courseService.create(
                courseMapper.toEntity(dto)
        );
        return courseMapper.toDto(saved);
    }

    @PutMapping("/{id}")
    public Course updateCourse(
            @PathVariable Long id,
            @RequestBody Course updatedCourse
    ) {
        return courseService.update(id, updatedCourse);
    }
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

}
