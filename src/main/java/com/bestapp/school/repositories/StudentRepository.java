package com.bestapp.school.repositories;

import com.bestapp.school.model.Student;
import com.bestapp.school.model.YoungestStudents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findStudentByAge(int age);
    List<Student> findStudentByAgeBetween(int min, int max);
    List<Student> findStudentByFaculty_Id(Long facultyId);
    @Query(value = "SELECT COUNT(s) FROM student s", nativeQuery = true)
    Integer findAllStudentNumber();
    @Query(value = "SELECT AVG(s.age) FROM student s", nativeQuery = true)
    Integer findAverageStudentAge();
    @Query(value = "SELECT name, age FROM student ORDER BY age LIMIT 5", nativeQuery = true)
    List<YoungestStudents> findTopYoungestStudents();
}
