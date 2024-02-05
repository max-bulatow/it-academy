package by.itacademy.client.service.impl;


import by.itacademy.client.controller.dto.StudentDto;
import by.itacademy.client.domain.entity.Student;
import by.itacademy.client.domain.repository.StudentRepository;
import by.itacademy.client.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;

    @Override
    public Integer save(final StudentDto dto) {
        final var student = new Student(dto.getId(), dto.getFirstName(), dto.getLastName());
        repository.save(student);

        return student.getId();
    }

    @Override
    public List<StudentDto> findAll() {
        return repository.findAll().stream()
                .map(student -> new StudentDto(student.getId(), student.getFirstName(), student.getLastName()))
                .toList();
    }

    @Override
    public Optional<StudentDto> findById(final Integer id) {
        return repository.findById(id)
                .map(student -> new StudentDto(student.getId(), student.getFirstName(), student.getLastName()));
    }

    @Override
    public void delete(final Integer id) {
        repository.deleteById(id);
    }
}
