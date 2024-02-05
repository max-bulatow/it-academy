package by.itacademy.persistance.service.impl;

import by.itacademy.persistance.controller.dto.createdto.CreateTeacherDto;
import by.itacademy.persistance.controller.dto.TeacherDto;
import by.itacademy.persistance.persistence.entity.Teacher;
import by.itacademy.persistance.persistence.exception.ValueNotFoundException;
import by.itacademy.persistance.persistence.repository.TeacherRepository;
import by.itacademy.persistance.service.TeacherService;
import by.itacademy.persistance.service.mapper.TeacherMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;

    @Transactional(readOnly = true)
    @Override
    public TeacherDto findById(final Integer id) {
        return teacherRepository.findById(id)
                .map(teacherMapper::mapToDto)
                .orElseThrow(() -> new ValueNotFoundException(
                        "Teacher with id [%s] is not found".formatted(id)
                ));
    }

    @Override
    public List<TeacherDto> getAll() {
        return teacherRepository.findAll().stream()
                .map(teacher -> new TeacherDto(teacher.getId(), teacher.getFirstName(), teacher.getLastName()))
                .toList();
    }

    @Override
    public Integer save(final CreateTeacherDto dto) {
        final var teacher = new Teacher(dto.getFirstName(), dto.getLastName());

        teacherRepository.save(teacher);

        return teacher.getId();
    }

    @Override
    public void delete(final Integer id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public Integer update(final TeacherDto dto) {
        final var teacher = new Teacher(dto.getId(), dto.getFirstName(), dto.getLastName());

        teacherRepository.save(teacher);

        return teacher.getId();
    }
}
