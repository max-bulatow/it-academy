package by.itacademy.persistance.service.mapper;

import by.itacademy.persistance.controller.dto.TeacherDto;
import by.itacademy.persistance.persistence.entity.Teacher;

public interface TeacherMapper {

    TeacherDto mapToDto(Teacher teacher);
}
