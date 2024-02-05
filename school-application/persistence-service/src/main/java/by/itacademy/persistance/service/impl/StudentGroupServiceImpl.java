package by.itacademy.persistance.service.impl;

import by.itacademy.persistance.controller.dto.createdto.CreateStudentGroupDto;
import by.itacademy.persistance.controller.dto.StudentGroupDto;
import by.itacademy.persistance.persistence.entity.StudentGroup;
import by.itacademy.persistance.persistence.exception.ValueNotFoundException;
import by.itacademy.persistance.persistence.repository.StudentGroupRepository;
import by.itacademy.persistance.service.StudentGroupService;
import by.itacademy.persistance.service.mapper.StudentGroupMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentGroupServiceImpl implements StudentGroupService {

    private final StudentGroupRepository studentGroupRepository;
    private final StudentGroupMapper studentGroupMapper;

    @Override
    public StudentGroupDto findById(final Integer id) {
        return studentGroupRepository.findById(id)
                .map(studentGroupMapper::mapToDto)
                .orElseThrow(() -> new ValueNotFoundException(
                        "Student group with [%s] is not found".formatted(id)
                ));
    }

    @Override
    public List<StudentGroupDto> getAll() {
        return studentGroupRepository.findAll().stream()
                .map(studentGroup -> new StudentGroupDto(studentGroup.getId(), studentGroup.getName()))
                .toList();
    }

    @Override
    public Integer save(final CreateStudentGroupDto dto) {
        final var studentGroup = new StudentGroup(dto.getName());

        studentGroupRepository.save(studentGroup);

        return studentGroup.getId();
    }

    @Override
    public void delete(final Integer id) {
        studentGroupRepository.deleteById(id);
    }

    @Override
    public Integer update(final StudentGroupDto dto) {
        final var studentGroup = new StudentGroup(dto.getId(), dto.getName());

        studentGroupRepository.save(studentGroup);

        return studentGroup.getId();
    }
}
