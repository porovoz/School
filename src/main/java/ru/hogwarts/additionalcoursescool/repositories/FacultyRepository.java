package ru.hogwarts.additionalcoursescool.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.additionalcoursescool.model.Faculty;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    List<Faculty> findFacultyByColor(String color);
}