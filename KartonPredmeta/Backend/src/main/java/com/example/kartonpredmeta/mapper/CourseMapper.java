package com.example.kartonpredmeta.mapper;
import com.example.kartonpredmeta.dto.CourseDTO;
import com.example.kartonpredmeta.entity.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {


    Course toEntity(CourseDTO dto);
    CourseDTO toDto(Course course);
}
