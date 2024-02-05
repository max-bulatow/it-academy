package by.itacademy.persistance.service;

import by.itacademy.persistance.controller.dto.createdto.CreateStudentGroupDto;
import by.itacademy.persistance.controller.dto.StudentGroupDto;

import java.util.List;

public interface StudentGroupService {

    StudentGroupDto findById(Integer id);

    List<StudentGroupDto> getAll();

    Integer save(CreateStudentGroupDto dto);

    void delete(Integer id);

    Integer update(StudentGroupDto dto);
}
