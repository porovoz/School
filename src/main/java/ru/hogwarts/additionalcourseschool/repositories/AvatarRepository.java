package ru.hogwarts.additionalcourseschool.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.additionalcourseschool.model.Avatar;

import java.util.Optional;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {
    Optional<Avatar> findByStudentId(Long studentId);
}
