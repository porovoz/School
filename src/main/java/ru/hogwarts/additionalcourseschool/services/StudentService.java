package ru.hogwarts.additionalcourseschool.services;

import ru.hogwarts.additionalcourseschool.dto.StudentDTO;
import ru.hogwarts.additionalcourseschool.model.YoungestStudents;

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
    Integer findAllStudentNumber();
    Integer findAverageStudentAge();
    List<YoungestStudents> findTopYoungestStudents();
    List<StudentDTO> findAllStudentsPageable(Integer pageNumber, Integer pageSize);
}