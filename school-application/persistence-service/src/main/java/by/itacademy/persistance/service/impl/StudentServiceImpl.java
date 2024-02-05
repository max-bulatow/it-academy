package by.itacademy.persistance.service.impl;

import by.itacademy.persistance.controller.dto.createdto.CreateStudentDto;
import by.itacademy.persistance.controller.dto.StudentDto;
import by.itacademy.persistance.persistence.entity.Student;
import by.itacademy.persistance.persistence.exception.ValueNotFoundException;
import by.itacademy.persistance.persistence.repository.StudentRepository;
import by.itacademy.persistance.service.StudentService;
import by.itacademy.persistance.service.mapper.StudentMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Transactional(readOnly = true)
    @Override
    public StudentDto findById(final Integer id) {
        return studentRepository.findById(id)
                .map(studentMapper::mapToDto)
                .orElseThrow(() -> new ValueNotFoundException(
                        "Student with id [%s] is not found".formatted(id)
                ));
    }

    @Override
    public List<StudentDto> getAll() {
        return studentRepository.findAll().stream()
                .map(student -> new StudentDto(student.getId(), student.getFirstName(), student.getLastName()))
                .toList();
    }

    @Override
    public Integer save(final CreateStudentDto dto) {
        final var student = new Student(dto.getFirstName(), dto.getLastName());

        studentRepository.save(student);

        return student.getId();
    }

    @Override
    public void delete(final Integer id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Integer update(final StudentDto dto) {
        final var student = new Student(dto.getId(), dto.getFirstName(), dto.getLastName());

        studentRepository.save(student);

        return student.getId();
    }

}