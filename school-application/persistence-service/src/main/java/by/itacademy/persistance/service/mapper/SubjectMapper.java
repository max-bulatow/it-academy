package by.itacademy.persistance.service.mapper;

import by.itacademy.persistance.controller.dto.SubjectDto;
import by.itacademy.persistance.persistence.entity.Subject;

public interface SubjectMapper {

    SubjectDto mapToDto(Subject subject);
}
