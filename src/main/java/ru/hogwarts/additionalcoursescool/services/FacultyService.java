package ru.hogwarts.additionalcoursescool.services;

import ru.hogwarts.additionalcoursescool.model.Faculty;

import java.util.Collection;
import java.util.List;

public interface FacultyService {
    Faculty createFaculty(Faculty faculty);
    Faculty findFacultyById(Long facultyId);
    List<Faculty> findFacultyByColor(String color);
    Collection<Faculty> findAllFaculties();
    Faculty updateFaculty(Faculty faculty);
    void deleteFaculty(Long facultyId);
    void deleteAllFaculties();
}
