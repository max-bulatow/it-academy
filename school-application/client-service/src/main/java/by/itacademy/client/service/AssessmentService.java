package by.itacademy.client.service;


import by.itacademy.client.controller.dto.AssessmentDto;

import java.util.List;
import java.util.Optional;

public interface AssessmentService {

    Integer save(AssessmentDto dto);

    List<AssessmentDto> findAll();

    Optional<AssessmentDto> findById(Integer id);

    void delete(Integer id);
}
