package by.itacademy.client.service;



import by.itacademy.client.controller.dto.SubjectDto;

import java.util.List;
import java.util.Optional;

public interface SubjectService {

    Integer save(SubjectDto dto);

    List<SubjectDto> findAll();

    Optional<SubjectDto> findById(Integer id);

    void delete(Integer id);
}
