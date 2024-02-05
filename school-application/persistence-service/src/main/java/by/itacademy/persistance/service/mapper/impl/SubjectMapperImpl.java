package by.itacademy.persistance.service.mapper.impl;

import by.itacademy.persistance.controller.dto.SubjectDto;
import by.itacademy.persistance.persistence.entity.Subject;
import by.itacademy.persistance.service.mapper.SubjectMapper;
import org.springframework.stereotype.Component;

@Component
public class SubjectMapperImpl implements SubjectMapper {
    @Override
    public SubjectDto mapToDto(final Subject subject) {
        return new SubjectDto(
                subject.getId(),
                subject.getName()
        );
    }
}
