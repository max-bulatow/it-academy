package by.itacademy.client.service.impl;


import by.itacademy.client.controller.dto.StudentGroupDto;
import by.itacademy.client.domain.entity.StudentGroup;
import by.itacademy.client.domain.repository.StudentGroupRepository;
import by.itacademy.client.service.StudentGroupService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class StudentGroupServiceImpl implements StudentGroupService {

    private final StudentGroupRepository repository;


    @Override
    public Integer save(final StudentGroupDto dto) {
        final var studentGroup = new StudentGroup(dto.getId(), dto.getName());
        repository.save(studentGroup);

        return studentGroup.getId();
    }

    @Override
    public List<StudentGroupDto> findAll() {
        return repository.findAll().stream()
                .map(studentGroup -> new StudentGroupDto(studentGroup.getId(), studentGroup.getName()))
                .toList();
    }

    @Override
    public Optional<StudentGroupDto> findById(final Integer id) {
        return repository.findById(id)
                .map(studentGroup -> new StudentGroupDto(studentGroup.getId(), studentGroup.getName()));
    }

    @Override
    public void delete(final Integer id) {
        repository.deleteById(id);
    }
}
