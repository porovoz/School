package ru.hogwarts.additionalcoursescool.service;

import ru.hogwarts.additionalcoursescool.model.Faculty;

import java.util.List;
import java.util.Map;

public interface HouseService {
    Faculty createFaculty(Faculty faculty);
    Faculty findFacultyById(Long facultyId);
    List<Faculty> findFacultyByColor(String color);
    Map<Long, Faculty> findAllFaculties();
    Faculty updateFaculty(Long facultyId, Faculty faculty);
    Faculty deleteFaculty(Long facultyId);

    void deleteAllFaculties();
}
