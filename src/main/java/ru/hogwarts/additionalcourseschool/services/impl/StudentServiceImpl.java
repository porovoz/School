package ru.hogwarts.additionalcourseschool.services.impl;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.hogwarts.additionalcourseschool.dto.StudentDTO;
import ru.hogwarts.additionalcourseschool.model.Student;
import ru.hogwarts.additionalcourseschool.model.YoungestStudents;
import ru.hogwarts.additionalcourseschool.repositories.FacultyRepository;
import ru.hogwarts.additionalcourseschool.repositories.StudentRepository;
import ru.hogwarts.additionalcourseschool.services.StudentService;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;

    public StudentServiceImpl(StudentRepository studentRepository,
                              FacultyRepository facultyRepository) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
    }

    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {
        studentRepository.save(toStudent(studentDTO));
        return studentDTO;
    }

    @Override
    public StudentDTO findStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId).get();
        return fromStudent(student);
    }

    @Override
    public List<StudentDTO> findStudentByAge(int age) {
        List<Student> students = studentRepository.findStudentByAge(age);
        List<StudentDTO> studentDTOS = new ArrayList<>();
        for (Student student : students) {
            StudentDTO studentDTO = fromStudent(student);
            studentDTOS.add(studentDTO);
        }
        return studentDTOS;
    }

    @Override
    public List<StudentDTO> findStudentByAgeBetween(int min, int max) {
        List<Student> students = studentRepository.findStudentByAgeBetween(min, max);
        List<StudentDTO> studentDTOS = new ArrayList<>();
        for (Student student : students) {
            StudentDTO studentDTO = fromStudent(student);
            studentDTOS.add(studentDTO);
        }
        return studentDTOS;
    }

    @Override
    public List<StudentDTO> findStudentByFacultyId(Long facultyId) {
        List<Student> students = studentRepository.findStudentByFaculty_Id(facultyId);
        List<StudentDTO> studentDTOS = new ArrayList<>();
        for (Student student : students) {
            StudentDTO studentDTO = fromStudent(student);
            studentDTOS.add(studentDTO);
        }
        return studentDTOS;
    }

    @Override
    public List<StudentDTO> findAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentDTO> studentDTOS = new ArrayList<>();
        for (Student student : students) {
            StudentDTO studentDTO = fromStudent(student);
            studentDTOS.add(studentDTO);
        }
        return studentDTOS;
    }

    @Override
    public StudentDTO updateStudent(StudentDTO studentDTO) {
        studentRepository.save(toStudent(studentDTO));
        return studentDTO;
    }

    @Override
    public void deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    @Override
    public void deleteAllStudents() {
        studentRepository.deleteAll();
    }

    @Override
    public Integer findAllStudentNumber() {
        return studentRepository.findAllStudentNumber();
    }

    @Override
    public Integer findAverageStudentAge() {
        return studentRepository.findAverageStudentAge();
    }

    @Override
    public List<YoungestStudents> findTopYoungestStudents() {
        return studentRepository.findTopYoungestStudents();
    }

    @Override
    public List<StudentDTO> findAllStudentsPageable(Integer pageNumber, Integer pageSize) {
        if (pageSize > 50 || pageSize <= 0) {
            pageSize = 50;
        }
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        List<Student> students = studentRepository.findAll(pageRequest).getContent();
        List<StudentDTO> studentDTOS = new ArrayList<>();
        for (Student student : students) {
            StudentDTO studentDTO = fromStudent(student);
            studentDTOS.add(studentDTO);
        }
        return studentDTOS;
    }

    private StudentDTO fromStudent(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setAge(student.getAge());
        dto.setFacultyId(student.getFaculty().getId());
        dto.setFaculty(FacultyServiceImpl.fromFaculty(student.getFaculty()));
        return dto;
    }

    private Student toStudent(StudentDTO studentDTO) {
        return new Student(
                studentDTO.getId(),
                studentDTO.getName(),
                studentDTO.getAge(),
                facultyRepository.getReferenceById(studentDTO.getId())
        );
    }
}
