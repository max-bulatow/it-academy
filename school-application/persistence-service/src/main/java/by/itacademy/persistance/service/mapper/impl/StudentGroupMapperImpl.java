package by.itacademy.persistance.service.mapper.impl;

import by.itacademy.persistance.controller.dto.StudentGroupDto;
import by.itacademy.persistance.persistence.entity.StudentGroup;
import by.itacademy.persistance.service.mapper.StudentGroupMapper;
import org.springframework.stereotype.Component;

@Component
public class StudentGroupMapperImpl implements StudentGroupMapper {

    @Override
    public StudentGroupDto mapToDto(final StudentGroup studentGroup) {
        return new StudentGroupDto(
                studentGroup.getId(),
                studentGroup.getName()
        );
    }
}
