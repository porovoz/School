package ru.hogwarts.additionalcourseschool.services;

import ru.hogwarts.additionalcourseschool.dto.FacultyDTO;

import java.util.List;

public interface FacultyService {
    FacultyDTO createFaculty(FacultyDTO facultyDTO);
    FacultyDTO findFacultyById(Long facultyId);
    List<FacultyDTO> findFacultyByColor(String color);
    FacultyDTO findFacultyByNameIgnoreCase(String name);
    FacultyDTO findFacultyByStudentId(Long studentId);
    List<FacultyDTO> findAllFaculties();
    FacultyDTO updateFaculty(FacultyDTO facultyDTO);
    void deleteFaculty(Long facultyId);
    void deleteAllFaculties();
}