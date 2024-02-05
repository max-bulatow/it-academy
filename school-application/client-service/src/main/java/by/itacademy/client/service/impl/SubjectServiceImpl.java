package by.itacademy.client.service.impl;


import by.itacademy.client.controller.dto.SubjectDto;
import by.itacademy.client.domain.entity.Subject;
import by.itacademy.client.domain.repository.SubjectRepository;
import by.itacademy.client.service.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository repository;

    @Override
    public Integer save(final SubjectDto dto) {
        final var subject = new Subject(dto.getId(), dto.getName());
        repository.save(subject);

        return subject.getId();
    }

    @Override
    public List<SubjectDto> findAll() {
        return repository.findAll().stream()
                .map(subject -> new SubjectDto(subject.getId(), subject.getName()))
                .toList();
    }

    @Override
    public Optional<SubjectDto> findById(final Integer id) {
        return repository.findById(id)
                .map(subject -> new SubjectDto(subject.getId(), subject.getName()));
    }

    @Override
    public void delete(final Integer id) {
        repository.deleteById(id);
    }
}
