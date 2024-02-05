package by.itacademy.persistance.service;

import by.itacademy.persistance.controller.dto.AssessmentDto;
import by.itacademy.persistance.controller.dto.createdto.CreateAssessmentDto;

import java.util.List;

public interface AssessmentService {

    AssessmentDto findById(Integer id);

    List<AssessmentDto> getAll();

    Integer save(CreateAssessmentDto dto);

    void delete(Integer id);

    Integer update(AssessmentDto dto);
}
