package ru.hogwarts.additionalcoursescool.services.impl;

import org.springframework.stereotype.Service;
import ru.hogwarts.additionalcoursescool.model.Student;
import ru.hogwarts.additionalcoursescool.repositories.StudentRepository;
import ru.hogwarts.additionalcoursescool.services.StudentService;

import java.util.Collection;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student findStudentById(Long studentId) {
        return studentRepository.findById(studentId).get();
    }

    @Override
    public List<Student> findStudentByAge(int age) {
        return studentRepository.findStudentByAge(age);
    }

    @Override
    public Collection<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    @Override
    public void deleteAllStudents() {
        studentRepository.deleteAll();
    }
}
