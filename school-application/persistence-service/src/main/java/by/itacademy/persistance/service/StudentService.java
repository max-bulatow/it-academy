package by.itacademy.persistance.service;

import by.itacademy.persistance.controller.dto.createdto.CreateStudentDto;
import by.itacademy.persistance.controller.dto.StudentDto;

import java.util.List;

public interface StudentService {

    StudentDto findById(Integer id);

    List<StudentDto> getAll();

    Integer save(CreateStudentDto dto);

    void delete(Integer id);

    Integer update(StudentDto dto);
}
