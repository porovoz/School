package ru.hogwarts.additionalcoursescool.service.impl;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import ru.hogwarts.additionalcoursescool.model.Student;
import ru.hogwarts.additionalcoursescool.service.StudentService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {
    private static Map<Long, Student> studentMap = new HashMap<>();
    private static Long studentId = 1L;

    @Override
    public Student createStudent(Student student) {
        studentMap.putIfAbsent(studentId++, student);
        return student;
    }

    @Override
    public Student findStudentById(Long studentId) {
        ObjectUtils.isNotEmpty(studentMap);
        return studentMap.get(studentId);
    }

    @Override
    public List<Student> findStudentByAge(int age) {
        return studentMap.values().stream().filter(student -> student.getAge() == age).toList();
    }


    @Override
    public Map<Long, Student> findAllStudents() {
        ObjectUtils.isNotEmpty(studentMap);
        return studentMap;
    }

    @Override
    public Student updateStudent(Long studentId, Student student) {
        ObjectUtils.isNotEmpty(student);
        if(studentMap.containsKey(studentId)) {
            studentMap.put(studentId, student);
            return student;
        }
        return null;
    }

    @Override
    public Student deleteStudent(Long studentId) {
        ObjectUtils.isNotEmpty(studentMap);
        if (studentMap.containsKey(studentId)) {
            return studentMap.remove(studentId);
        }
        return null;
    }

    @Override
    public void deleteAllStudents() {
        studentMap = new HashMap<>();
    }
}
