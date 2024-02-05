package by.itacademy.persistance.service.mapper;

import by.itacademy.persistance.controller.dto.AssessmentDto;
import by.itacademy.persistance.persistence.entity.Assessment;

public interface AssessmentMapper {

    AssessmentDto assessmentToDto(Assessment assessment);
}
