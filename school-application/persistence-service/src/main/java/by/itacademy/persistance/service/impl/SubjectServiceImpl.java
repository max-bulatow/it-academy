package by.itacademy.persistance.service.impl;

import by.itacademy.persistance.controller.dto.createdto.CreateSubjectDto;
import by.itacademy.persistance.controller.dto.SubjectDto;
import by.itacademy.persistance.persistence.entity.Subject;
import by.itacademy.persistance.persistence.exception.ValueNotFoundException;
import by.itacademy.persistance.persistence.repository.SubjectRepository;
import by.itacademy.persistance.service.SubjectService;
import by.itacademy.persistance.service.mapper.SubjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;

    @Override
    public SubjectDto findById(final Integer id) {
        return subjectRepository.findById(id)
                .map(subjectMapper::mapToDto)
                .orElseThrow(() -> new ValueNotFoundException(
                        "Assessment with id [%s] is not found".formatted(id)
                ));
    }

    @Override
    public List<SubjectDto> getAll() {
        return subjectRepository.findAll().stream()
                .map(subject -> new SubjectDto(subject.getId(), subject.getName()))
                .toList();
    }

    @Override
    public Integer save(final CreateSubjectDto dto) {
        final var subject = new Subject(dto.getName());

        subjectRepository.save(subject);

        return subject.getId();
    }

    @Override
    public void delete(final Integer id) {
        subjectRepository.deleteById(id);
    }

    @Override
    public Integer update(final SubjectDto dto) {
        final var subject = new Subject(dto.getId(), dto.getName());

        subjectRepository.save(subject);

        return subject.getId();
    }
}
