package ru.hogwarts.additionalcoursescool.service;

import ru.hogwarts.additionalcoursescool.model.Student;

import java.util.List;
import java.util.Map;

public interface StudentService {
    Student createStudent(Student student);
    Student findStudentById(Long studentId);
    List<Student> findStudentByAge(int age);
    Map<Long, Student> findAllStudents();
    Student updateStudent(Long studentId, Student student);
    Student deleteStudent(Long studentId);
    void deleteAllStudents();
}
