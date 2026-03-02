package com.example.kartonpredmeta.service;

import com.example.kartonpredmeta.dto.CourseDTO;
import com.example.kartonpredmeta.dto.FullSyllabusDTO;
import com.example.kartonpredmeta.dto.TeacherDTO;
import com.example.kartonpredmeta.entity.Teacher;
import com.example.kartonpredmeta.mapper.CourseMapper;
import com.example.kartonpredmeta.mapper.TeacherMapper;
import com.example.kartonpredmeta.entity.*;
import com.example.kartonpredmeta.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FullSyllabusService {
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final LearningOutcomeRepository learningOutcomeRepository;
    private final LiteratureRepository literatureRepository;
    private final ThematicUnitRepository thematicUnitRepository;
    private final AssessmentComponentRepository assessmentComponentRepository;
    private final CourseObjectiveRepository courseObjectiveRepository;
    private final TeachingAssignmentRepository teachingAssignmentRepository;
    private final CourseMapper courseMapper;
    private final TeacherMapper teacherMapper;



    public FullSyllabusService(
            CourseRepository courseRepository,
            TeacherRepository teacherRepository,
            LearningOutcomeRepository learningOutcomeRepository,
            LiteratureRepository literatureRepository,
            ThematicUnitRepository thematicUnitRepository,
            AssessmentComponentRepository assessmentComponentRepository,
            CourseObjectiveRepository courseObjectiveRepository,
            TeachingAssignmentRepository teachingAssignmentRepository,
            CourseMapper courseMapper,
            TeacherMapper teacherMapper
    )


    {
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
        this.learningOutcomeRepository = learningOutcomeRepository;
        this.literatureRepository = literatureRepository;
        this.thematicUnitRepository = thematicUnitRepository;
        this.assessmentComponentRepository = assessmentComponentRepository;
        this.courseObjectiveRepository = courseObjectiveRepository;
        this.teachingAssignmentRepository = teachingAssignmentRepository;
        this.courseMapper = courseMapper;
        this.teacherMapper = teacherMapper;
    }



    @Transactional
    public FullSyllabusDTO createFullSyllabus(FullSyllabusDTO dto) {
        if (dto.getCourse() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Course data is required");
        }
        if (dto.getCourse().getName() != null
                && courseRepository.existsByNameIgnoreCase(dto.getCourse().getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Course already exists");
        }
        if (dto.getCourse().getCode() != null && !dto.getCourse().getCode().isBlank()
                && courseRepository.existsByCodeIgnoreCase(dto.getCourse().getCode())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Course code already exists");
        }
        Course course = courseMapper.toEntity(dto.getCourse());
        courseRepository.save(course);
        if (course.getCode() == null || course.getCode().isBlank()) {
            course.setCode(String.valueOf(course.getId()));
            courseRepository.save(course);
        }
        List<TeacherDTO> teacherList = dto.getTeachers();
        if (teacherList != null && !teacherList.isEmpty()) {
            List<Teacher> savedTeachers = saveTeachingAssignments(course, teacherList);
            course.setTeacher(savedTeachers.get(0));
            courseRepository.save(course);
        } else if (dto.getTeacher() != null) {
            Teacher teacher = resolveTeacher(dto.getTeacher());
            if (teacher != null) {
                course.setTeacher(teacher);
                courseRepository.save(course);
            }
        }

        saveCourseObjectives(dto.getCourseObjectives(), course);
        saveLearningOutcomes(dto.getLearningOutcomes(), course);
        saveLiterature(dto.getLiterature(), course);
        saveThematicUnits(dto.getThematicUnits(), course);
        saveAssessmentComponents(dto.getAssessmentComponents(), course);

        return dto;
    }





    public FullSyllabusDTO getFullSyllabus(Long courseId) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        FullSyllabusDTO dto = new FullSyllabusDTO();

        dto.setCourse(courseMapper.toDto(course));
        dto.setTeacher(teacherMapper.toDto(course.getTeacher()));

        List<Teacher> teachers = teachingAssignmentRepository.findByCourseId(courseId)
                .stream()
                .map(TeachingAssignment::getTeacher)
                .toList();
        if (teachers.isEmpty() && course.getTeacher() != null) {
            teachers = List.of(course.getTeacher());
        }
        dto.setTeachers(toDistinctTeacherDtos(teachers));

        dto.setCourseObjectives(
                courseObjectiveRepository.findByCourseId(courseId)
                        .stream().map(CourseObjective::getDescription).toList()
        );


        dto.setLearningOutcomes(
                learningOutcomeRepository.findByCourseId(courseId)
                        .stream().map(LearningOutcome::getDescription).toList()
        );

        dto.setLiterature(
                literatureRepository.findByCourseId(courseId)
                        .stream().map(Literature::getTitle).toList()
        );

        dto.setThematicUnits(
                thematicUnitRepository.findByCourseId(courseId)
                        .stream().map(ThematicUnit::getName).toList()
        );

        dto.setAssessmentComponents(
                assessmentComponentRepository.findByCourseId(courseId)
                        .stream().map(AssessmentComponent::getName).toList()
        );

        return dto;
    }
    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(courseMapper::toDto)
                .collect(Collectors.toList());
    }



    @Transactional
    public FullSyllabusDTO updateSyllabus(Long courseId, FullSyllabusDTO dto) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        if (dto.getCourse() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Course data is required");
        }
        if (dto.getCourse().getName() != null
                && courseRepository.existsByNameIgnoreCaseAndIdNot(dto.getCourse().getName(), courseId)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Course already exists");
        }
        if (dto.getCourse().getCode() != null && !dto.getCourse().getCode().isBlank()
                && courseRepository.existsByCodeIgnoreCaseAndIdNot(dto.getCourse().getCode(), courseId)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Course code already exists");
        }



        course.setName(dto.getCourse().getName());
        course.setEcts(dto.getCourse().getEcts());
        course.setDescription(dto.getCourse().getDescription());

        if (dto.getTeachers() != null && !dto.getTeachers().isEmpty()) {
            teachingAssignmentRepository.deleteByCourseId(courseId);
            List<Teacher> savedTeachers = saveTeachingAssignments(course, dto.getTeachers());
            course.setTeacher(savedTeachers.get(0));
        } else if (dto.getTeacher() != null) {
            Teacher teacher = course.getTeacher();
            if (teacher != null) {
                if (dto.getTeacher().getFirstName() != null) {
                    teacher.setFirstName(dto.getTeacher().getFirstName());
                }
                if (dto.getTeacher().getLastName() != null) {
                    teacher.setLastName(dto.getTeacher().getLastName());
                }
                if (dto.getTeacher().getEmail() != null) {
                    teacher.setEmail(dto.getTeacher().getEmail());
                }
                if (dto.getTeacher().getTitle() != null) {
                    teacher.setTitle(dto.getTeacher().getTitle());
                }
            } else {
                Teacher newTeacher = resolveTeacher(dto.getTeacher());
                if (newTeacher != null) {
                    course.setTeacher(newTeacher);
                }
            }
        }



        courseObjectiveRepository.deleteByCourseId(courseId);
        learningOutcomeRepository.deleteByCourseId(courseId);
        literatureRepository.deleteByCourseId(courseId);
        thematicUnitRepository.deleteByCourseId(courseId);
        assessmentComponentRepository.deleteByCourseId(courseId);
        saveCourseObjectives(dto.getCourseObjectives(), course);
        saveLearningOutcomes(dto.getLearningOutcomes(), course);
        saveLiterature(dto.getLiterature(), course);
        saveThematicUnits(dto.getThematicUnits(), course);
        saveAssessmentComponents(dto.getAssessmentComponents(), course);
        return dto;}



    @Transactional
    public void deleteSyllabus(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        courseObjectiveRepository.deleteByCourseId(courseId);
        learningOutcomeRepository.deleteByCourseId(courseId);
        literatureRepository.deleteByCourseId(courseId);
        thematicUnitRepository.deleteByCourseId(courseId);
        assessmentComponentRepository.deleteByCourseId(courseId);
        teachingAssignmentRepository.deleteByCourseId(courseId);
        courseRepository.delete(course);}



    private void saveCourseObjectives(List<String> list, Course course) {
        if (list == null) {
            return;
        }

        for (String text : list) {
            CourseObjective co = new CourseObjective();
            co.setDescription(text);
            co.setCourse(course);
            courseObjectiveRepository.save(co);
        }
    }



    private void saveLearningOutcomes(List<String> list, Course course) {
        if (list == null) {
            return;
        }
        for (String text : list) {
            LearningOutcome lo = new LearningOutcome();
            lo.setDescription(text);
            lo.setCourse(course);
            learningOutcomeRepository.save(lo);
        }
    }



    private void saveLiterature(List<String> list, Course course) {
        if (list == null) {
            return;
        }
        for (String title : list) {
            Literature l = new Literature();
            l.setTitle(title);
            l.setType("OBAVEZNA");
            l.setCourse(course);
            literatureRepository.save(l);
        }
    }

    private void saveThematicUnits(List<String> list, Course course) {
        if (list == null) {
            return;
        }
        for (String name : list) {
            ThematicUnit t = new ThematicUnit();
            t.setName(name);
            t.setCourse(course);
            thematicUnitRepository.save(t);
        }
    }



    private void saveAssessmentComponents(List<String> list, Course course) {
        if (list == null) {
            return;
        }
        for (String name : list) {
            AssessmentComponent ac = new AssessmentComponent();
            ac.setName(name);
            ac.setMaxPoints(0);
            ac.setCourse(course);
            assessmentComponentRepository.save(ac);
        }
    }




    private List<Teacher> saveTeachingAssignments(Course course, List<TeacherDTO> teacherList) {
        List<Teacher> saved = new ArrayList<>();
        for (TeacherDTO dto : teacherList) {
            if (dto == null) {
                continue;
            }
            if (dto.getEmail() == null || dto.getEmail().isBlank()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Teacher email is required");
            }
            Teacher teacher = resolveTeacher(dto);
            if (teacher == null) {
                continue;
            }
            TeachingAssignment ta = new TeachingAssignment();
            ta.setCourse(course);
            ta.setTeacher(teacher);
            ta.setType("PROFESSOR");
            ta.setHours(0);
            teachingAssignmentRepository.save(ta);
            saved.add(teacher);
        }
        if (saved.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "At least one teacher is required");
        }
        return saved;
    }



    private Teacher resolveTeacher(TeacherDTO dto) {
        if (dto == null) {
            return null;
        }
        String email = dto.getEmail();
        if (email != null && !email.isBlank()) {
            Teacher existing = teacherRepository.findByEmail(email).orElse(null);
            if (existing != null) {
                if (dto.getFirstName() != null) { existing.setFirstName(dto.getFirstName()); }
                if (dto.getLastName() != null) { existing.setLastName(dto.getLastName()); }
                if (dto.getTitle() != null) { existing.setTitle(dto.getTitle()); }
                return teacherRepository.save(existing);
            }
        }
        Teacher teacher = teacherMapper.toEntity(dto);
        return teacherRepository.save(teacher);
    }



    private List<TeacherDTO> toDistinctTeacherDtos(List<Teacher> teachers) {
        Map<Long, TeacherDTO> byId = new LinkedHashMap<>();
        for (Teacher t : teachers) {
            if (t == null || t.getId() == null) {
                continue;
            }
            byId.putIfAbsent(t.getId(), teacherMapper.toDto(t));
        }
        return new ArrayList<>(byId.values());
    }



}
