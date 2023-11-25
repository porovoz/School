package com.bestapp.school.repositories;

import com.bestapp.school.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    List<Faculty> findFacultyByColor(String color);
    Faculty findFacultyByNameIgnoreCase(String name);
    Faculty findFacultyById(Long studentId);
}
