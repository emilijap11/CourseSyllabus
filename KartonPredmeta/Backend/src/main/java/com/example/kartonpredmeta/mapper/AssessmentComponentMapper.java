package com.example.kartonpredmeta.mapper;
import com.example.kartonpredmeta.dto.AssessmentComponentDTO;
import com.example.kartonpredmeta.entity.AssessmentComponent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface AssessmentComponentMapper {
    @Mapping(source = "course.id", target = "courseId")
    AssessmentComponentDTO toDto(AssessmentComponent entity);

    @Mapping(source = "courseId", target = "course.id")

    AssessmentComponent toEntity(AssessmentComponentDTO dto);
}
