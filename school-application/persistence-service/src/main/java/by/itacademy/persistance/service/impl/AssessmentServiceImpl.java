package by.itacademy.persistance.service.impl;

import by.itacademy.persistance.controller.dto.AssessmentDto;
import by.itacademy.persistance.controller.dto.createdto.CreateAssessmentDto;
import by.itacademy.persistance.persistence.entity.Assessment;
import by.itacademy.persistance.persistence.exception.ValueNotFoundException;
import by.itacademy.persistance.persistence.repository.AssessmentRepository;
import by.itacademy.persistance.service.AssessmentService;
import by.itacademy.persistance.service.mapper.AssessmentMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class AssessmentServiceImpl implements AssessmentService {

    private final AssessmentRepository assessmentRepository;
    private final AssessmentMapper assessmentMapper;

    @Transactional(readOnly = true)
    @Override
    public AssessmentDto findById(final Integer id) {
        return assessmentRepository.findById(id)
                .map(assessmentMapper::assessmentToDto)
                .orElseThrow(() -> new ValueNotFoundException(
                        "Assessment with [%s] is not found".formatted(id)
                ));
    }

    @Override
    public List<AssessmentDto> getAll() {
        return assessmentRepository.findAll().stream()
                .map(assessment -> new AssessmentDto(assessment.getId(), assessment.getAssessment()))
                .toList();
    }

    @Override
    public Integer save(final CreateAssessmentDto dto) {
        final var assessment = new Assessment(dto.getAssessment());

        assessmentRepository.save(assessment);

        return assessment.getId();
    }

    @Override
    public void delete(final Integer id) {
        assessmentRepository.deleteById(id);
    }

    @Override
    public Integer update(final AssessmentDto dto) {
        final var assessment = new Assessment(dto.getId(), dto.getAssessment());

        assessmentRepository.save(assessment);

        return assessment.getId();
    }
}
