package com.example.kartonpredmeta.controller;
import com.example.kartonpredmeta.dto.TeacherDTO;
import com.example.kartonpredmeta.dto.CourseDTO;
import com.example.kartonpredmeta.entity.Teacher;
import com.example.kartonpredmeta.mapper.TeacherMapper;
import com.example.kartonpredmeta.mapper.CourseMapper;
import com.example.kartonpredmeta.service.TeacherService;
import com.example.kartonpredmeta.repository.CourseRepository;
import com.example.kartonpredmeta.repository.TeacherRepository;
import com.example.kartonpredmeta.repository.TeachingAssignmentRepository;
import com.example.kartonpredmeta.entity.Course;
import com.example.kartonpredmeta.entity.TeachingAssignment;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/teachers")
@Tag(name = "Teacher API", description = "CRUD operacije nad nastavnicima")
public class TeacherController {

    private final TeacherService service;
    private final TeacherMapper mapper;
    private final CourseMapper courseMapper;
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final TeachingAssignmentRepository teachingAssignmentRepository;

    public TeacherController(TeacherService service, TeacherMapper mapper, CourseMapper courseMapper, CourseRepository courseRepository, TeacherRepository teacherRepository, TeachingAssignmentRepository teachingAssignmentRepository) {
        this.service = service;
        this.mapper = mapper;
        this.courseMapper = courseMapper;
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
        this.teachingAssignmentRepository = teachingAssignmentRepository;
    }

    @Operation(summary = "Create new teacher", description = "Kreira nastavnika i povezuje ga sa predmetom.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Nastavnik uspesno kreiran",
                    content = @Content(schema = @Schema(implementation = TeacherDTO.class))),
            @ApiResponse(responseCode = "400", description = "Neispravan zahtev"),
            @ApiResponse(responseCode = "404", description = "Predmet nije pronadjen")
    })
    @PostMapping
    public TeacherDTO create(@Valid @RequestBody TeacherDTO dto) {
        Teacher saved;
        if (dto.getEmail() != null) {
            Teacher existing = teacherRepository.findByEmail(dto.getEmail()).orElse(null);
            if (existing != null) {
                saved = existing;
            } else {
                Teacher teacher = mapper.toEntity(dto);
                saved = service.create(teacher);
            }
        } else {
            Teacher teacher = mapper.toEntity(dto);
            saved = service.create(teacher);
        }
        Course course = null;
        if (dto.getCourseId() != null) {
            course = courseRepository.findById(dto.getCourseId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));
        } else if (dto.getCourseName() != null && !dto.getCourseName().isBlank()) {
            course = courseRepository.findByNameIgnoreCase(dto.getCourseName())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Course is required");
        }
        if (course.getTeacher() == null) {
            course.setTeacher(saved);
            courseRepository.save(course);
        }
        if (teachingAssignmentRepository.findByCourseIdAndTeacherId(course.getId(), saved.getId()).isEmpty()) {
            TeachingAssignment ta = new TeachingAssignment();
            ta.setCourse(course);
            ta.setTeacher(saved);
            ta.setType("PROFESSOR");
            ta.setHours(0);
            teachingAssignmentRepository.save(ta);
        }
        return mapper.toDto(saved);
    }

    @Operation(summary = "Get all teachers", description = "Vraca listu svih nastavnika.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista nastavnika",
                    content = @Content(schema = @Schema(implementation = TeacherDTO.class)))
    })
    @GetMapping
    public List<TeacherDTO> getAll() {
        return service.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Operation(summary = "Get teacher by ID", description = "Vraca nastavnika po ID-u.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Nastavnik pronadjen",
                    content = @Content(schema = @Schema(implementation = TeacherDTO.class))),
            @ApiResponse(responseCode = "404", description = "Nastavnik nije pronadjen")
    })
    @GetMapping("/{id}")
    public TeacherDTO getById(
            @Parameter(description = "ID nastavnika") @PathVariable Long id) {
        return mapper.toDto(service.findById(id));
    }

    @Operation(summary = "Get courses for teacher", description = "Vraca sve predmete koje nastavnik drzi.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista predmeta",
                    content = @Content(schema = @Schema(implementation = CourseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Nastavnik nije pronadjen")
    })
    @GetMapping("/{id}/courses")
    public List<CourseDTO> getCoursesByTeacher(
            @Parameter(description = "ID nastavnika") @PathVariable Long id) {
        List<Course> directCourses = courseRepository.findByTeacherId(id);
        List<Course> assignedCourses = teachingAssignmentRepository.findByTeacherId(id)
                .stream()
                .map(TeachingAssignment::getCourse)
                .toList();

        Map<Long, Course> byId = new LinkedHashMap<>();
        for (Course c : directCourses) {
            if (c != null && c.getId() != null) {
                byId.putIfAbsent(c.getId(), c);
            }
        }
        for (Course c : assignedCourses) {
            if (c != null && c.getId() != null) {
                byId.putIfAbsent(c.getId(), c);
            }
        }

        return new ArrayList<>(byId.values())
                .stream()
                .map(courseMapper::toDto)
                .toList();
    }

    @Operation(summary = "Update teacher", description = "Azurira postojeceg nastavnika.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Nastavnik uspesno azuriran",
                    content = @Content(schema = @Schema(implementation = TeacherDTO.class))),
            @ApiResponse(responseCode = "400", description = "Neispravan zahtev"),
            @ApiResponse(responseCode = "404", description = "Nastavnik nije pronadjen")
    })
    @PutMapping("/{id}")
    public TeacherDTO update(
            @Parameter(description = "ID nastavnika") @PathVariable Long id,
            @Valid @RequestBody TeacherDTO dto) {

        Teacher teacher = mapper.toEntity(dto);
        return mapper.toDto(service.update(id, teacher));
    }

    @Operation(summary = "Delete teacher", description = "Brise nastavnika po ID-u.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Nastavnik uspesno obrisan"),
            @ApiResponse(responseCode = "404", description = "Nastavnik nije pronadjen")
    })
    @DeleteMapping("/{id}")
    public void delete(@Parameter(description = "ID nastavnika") @PathVariable Long id) {
        service.delete(id);
    }
}
