package by.itacademy.persistance.service.mapper.impl;

import by.itacademy.persistance.controller.dto.TeacherDto;
import by.itacademy.persistance.persistence.entity.Teacher;
import by.itacademy.persistance.service.mapper.TeacherMapper;
import org.springframework.stereotype.Component;

@Component
public class TeacherMapperImpl implements TeacherMapper {
    @Override
    public TeacherDto mapToDto(final Teacher teacher) {
        return new TeacherDto(
                teacher.getId(),
                teacher.getFirstName(),
                teacher.getLastName()
        );
    }
}
