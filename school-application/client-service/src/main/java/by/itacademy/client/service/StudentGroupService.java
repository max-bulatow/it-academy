package by.itacademy.client.service;

import by.itacademy.client.controller.dto.StudentGroupDto;

import java.util.List;
import java.util.Optional;

public interface StudentGroupService {

    Integer save(StudentGroupDto dto);

    List<StudentGroupDto> findAll();

    Optional<StudentGroupDto> findById(Integer id);

    void delete(Integer id);
}
