package com.example.kartonpredmeta.mapper;
import com.example.kartonpredmeta.dto.GradeComponentDTO;
import com.example.kartonpredmeta.entity.GradeComponent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GradeComponentMapper {

    GradeComponent toEntity(GradeComponentDTO dto);
    GradeComponentDTO toDto(GradeComponent entity);
}
