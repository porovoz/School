package ru.hogwarts.additionalcoursescool.services;

import ru.hogwarts.additionalcoursescool.model.Student;

import java.util.Collection;
import java.util.List;

public interface StudentService {
    Student createStudent(Student student);
    Student findStudentById(Long studentId);
    List<Student> findStudentByAge(int age);
    Collection<Student> findAllStudents();
    Student updateStudent(Student student);
    void deleteStudent(Long studentId);
    void deleteAllStudents();
}
