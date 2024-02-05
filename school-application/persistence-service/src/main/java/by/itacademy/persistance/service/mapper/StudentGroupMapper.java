package by.itacademy.persistance.service.mapper;

import by.itacademy.persistance.controller.dto.StudentGroupDto;
import by.itacademy.persistance.persistence.entity.StudentGroup;

public interface StudentGroupMapper {

    StudentGroupDto mapToDto(StudentGroup studentGroup);
}
