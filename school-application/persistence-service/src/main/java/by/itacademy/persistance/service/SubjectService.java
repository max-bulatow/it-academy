package by.itacademy.persistance.service;

import by.itacademy.persistance.controller.dto.createdto.CreateSubjectDto;
import by.itacademy.persistance.controller.dto.SubjectDto;

import java.util.List;

public interface SubjectService {

    SubjectDto findById(Integer id);

    List<SubjectDto> getAll();

    Integer save(CreateSubjectDto dto);

    void delete(Integer id);

    Integer update(SubjectDto dto);
}
