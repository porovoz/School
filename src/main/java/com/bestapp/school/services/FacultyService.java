package com.bestapp.school.services;

import com.bestapp.school.dto.FacultyDTO;

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