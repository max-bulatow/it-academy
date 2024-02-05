package by.itacademy.persistance.persistence.repository;

import by.itacademy.persistance.persistence.entity.StudentGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentGroupRepository extends JpaRepository<StudentGroup, Integer> {
}
