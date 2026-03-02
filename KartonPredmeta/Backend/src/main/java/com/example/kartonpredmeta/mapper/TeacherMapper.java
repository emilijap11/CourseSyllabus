package com.example.kartonpredmeta.mapper;
import com.example.kartonpredmeta.dto.TeacherDTO;
import com.example.kartonpredmeta.entity.Teacher;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface TeacherMapper {
    TeacherDTO toDto(Teacher teacher);
    Teacher toEntity(TeacherDTO dto);
}
