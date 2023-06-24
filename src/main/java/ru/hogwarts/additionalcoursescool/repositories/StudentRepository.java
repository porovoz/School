package ru.hogwarts.additionalcoursescool.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.additionalcoursescool.model.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findStudentByAge(int age);
    List<Student> findStudentByAgeBetween(int min, int max);
    List<Student> findStudentByFaculty_Id(Long facultyId);
}
