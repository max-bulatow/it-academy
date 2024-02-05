package by.itacademy.client.service;

import by.itacademy.client.controller.dto.StudentDto;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    Integer save(StudentDto dto);

    List<StudentDto> findAll();

    Optional<StudentDto> findById(Integer id);

    void delete(Integer id);
}
