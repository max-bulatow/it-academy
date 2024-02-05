package by.itacademy.persistance.service;

import by.itacademy.persistance.controller.dto.createdto.CreateTeacherDto;
import by.itacademy.persistance.controller.dto.TeacherDto;

import java.util.List;

public interface TeacherService {

    TeacherDto findById(Integer id);

    List<TeacherDto> getAll();

    Integer save(CreateTeacherDto dto);

    void delete(Integer id);

    Integer update(TeacherDto dto);
}
