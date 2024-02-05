package by.itacademy.persistance.service.mapper.impl;

import by.itacademy.persistance.controller.dto.AssessmentDto;
import by.itacademy.persistance.persistence.entity.Assessment;
import by.itacademy.persistance.service.mapper.AssessmentMapper;
import org.springframework.stereotype.Component;

@Component
public class AssessmentMapperImpl implements AssessmentMapper {
    @Override
    public AssessmentDto assessmentToDto(final Assessment assessment) {
        return new AssessmentDto(
                assessment.getId(),
                assessment.getAssessment()
        );
    }
}
