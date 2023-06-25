package ru.hogwarts.additionalcourseschool.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.additionalcourseschool.model.Faculty;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    List<Faculty> findFacultyByColor(String color);
    Faculty findFacultyByNameIgnoreCase(String name);
    Faculty findFacultyById(Long studentId);
}
