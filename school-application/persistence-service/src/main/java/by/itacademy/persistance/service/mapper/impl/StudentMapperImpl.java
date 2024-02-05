package by.itacademy.persistance.service.mapper.impl;

import by.itacademy.persistance.controller.dto.StudentDto;
import by.itacademy.persistance.persistence.entity.Student;
import by.itacademy.persistance.service.mapper.StudentMapper;
import org.springframework.stereotype.Component;

@Component
public class StudentMapperImpl implements StudentMapper {
    @Override
    public StudentDto mapToDto(final Student student) {
        return new StudentDto(
                student.getId(),
                student.getFirstName(),
                student.getLastName()
        );
    }
}
