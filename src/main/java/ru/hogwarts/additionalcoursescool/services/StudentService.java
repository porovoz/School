package ru.hogwarts.additionalcoursescool.services;

import ru.hogwarts.additionalcoursescool.dto.StudentDTO;

import java.util.List;

public interface StudentService {
    StudentDTO createStudent(StudentDTO studentDTO);
    StudentDTO findStudentById(Long studentId);
    List<StudentDTO> findStudentByAge(int age);
    List<StudentDTO> findStudentByAgeBetween(int min, int max);
    List<StudentDTO> findStudentByFacultyId(Long facultyId);
    List<StudentDTO> findAllStudents();
    StudentDTO updateStudent(StudentDTO studentDTO);
    void deleteStudent(Long studentId);
    void deleteAllStudents();
}