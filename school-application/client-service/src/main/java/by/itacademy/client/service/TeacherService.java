package by.itacademy.client.service;

import by.itacademy.client.controller.dto.TeacherDto;

import java.util.List;
import java.util.Optional;

public interface TeacherService {

    Integer save(TeacherDto dto);

    List<TeacherDto> findAll();

    Optional<TeacherDto> findById(Integer id);

    void delete(Integer id);
}
