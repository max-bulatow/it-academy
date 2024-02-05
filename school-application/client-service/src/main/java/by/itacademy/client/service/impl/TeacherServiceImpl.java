package by.itacademy.client.service.impl;


import by.itacademy.client.controller.dto.TeacherDto;
import by.itacademy.client.domain.entity.Teacher;
import by.itacademy.client.domain.repository.TeacherRepository;
import by.itacademy.client.service.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository repository;

    @Override
    public Integer save(final TeacherDto dto) {
        final var teacher = new Teacher(dto.getId(), dto.getFirstName(), dto.getLastName());
        repository.save(teacher);

        return teacher.getId();
    }

    @Override
    public List<TeacherDto> findAll() {
        return repository.findAll().stream()
                .map(teacher -> new TeacherDto(teacher.getId(), teacher.getFirstName(), teacher.getLastName()))
                .toList();
    }

    @Override
    public Optional<TeacherDto> findById(final Integer id) {
        return repository.findById(id)
                .map(teacher -> new TeacherDto(teacher.getId(), teacher.getFirstName(), teacher.getLastName()));
    }

    @Override
    public void delete(final Integer id) {
        repository.deleteById(id);
    }
}