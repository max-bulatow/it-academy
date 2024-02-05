package by.itacademy.persistance.service.mapper;

import by.itacademy.persistance.controller.dto.StudentDto;
import by.itacademy.persistance.persistence.entity.Student;

public interface StudentMapper {

    StudentDto mapToDto(Student student);
}
