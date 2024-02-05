package by.itacademy.client.service.impl;


import by.itacademy.client.controller.dto.AssessmentDto;
import by.itacademy.client.domain.entity.Assessment;
import by.itacademy.client.domain.repository.AssessmentRepository;
import by.itacademy.client.service.AssessmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AssessmentServiceImpl implements AssessmentService {

    private final AssessmentRepository repository;

    @Override
    public Integer save(final AssessmentDto dto) {
        final var assessment = new Assessment(dto.getId(), dto.getAssessment());
        repository.save(assessment);

        return assessment.getId();
    }

    @Override
    public List<AssessmentDto> findAll() {
        return repository.findAll().stream()
                .map(assessment -> new AssessmentDto(assessment.getId(), assessment.getAssessment()))
                .toList();
    }

    @Override
    public Optional<AssessmentDto> findById(final Integer id) {
        return repository.findById(id)
                .map(assessment -> new AssessmentDto(assessment.getId(), assessment.getAssessment()));
    }

    @Override
    public void delete(final Integer id) {
        repository.deleteById(id);
    }
}
