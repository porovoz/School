package ru.hogwarts.additionalcourseschool.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    public StudentServiceImpl(StudentRepository studentRepository,
                              FacultyRepository facultyRepository) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
    }

    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {
        logger.info("Create student method was invoked");
        studentRepository.save(toStudent(studentDTO));
        logger.info("Student {} was created successfully", studentDTO);
        return studentDTO;
    }

    @Override
    public StudentDTO findStudentById(Long studentId) {
        logger.info("Find student by id = {} method was invoked", studentId);
        Student student = studentRepository.findById(studentId).get();
        logger.info("Student with id = {} was successfully found", studentId);
        return fromStudent(student);
    }

    @Override
    public List<StudentDTO> findStudentByAge(int age) {
        logger.info("Find student by age = {} method was invoked", age);
        List<Student> students = studentRepository.findStudentByAge(age);
        List<StudentDTO> studentDTOS = new ArrayList<>();
        for (Student student : students) {
            StudentDTO studentDTO = fromStudent(student);
            studentDTOS.add(studentDTO);
        }
        logger.info("Student with age = {} was successfully found", age);
        return studentDTOS;
    }

    @Override
    public List<StudentDTO> findStudentByAgeBetween(int min, int max) {
        logger.info("Find student by age between min = {} and max = {} method was invoked", min, max);
        List<Student> students = studentRepository.findStudentByAgeBetween(min, max);
        List<StudentDTO> studentDTOS = new ArrayList<>();
        for (Student student : students) {
            StudentDTO studentDTO = fromStudent(student);
            studentDTOS.add(studentDTO);
        }
        logger.info("Students with age between min = {} and max = {} were successfully found", min, max);
        return studentDTOS;
    }

    @Override
    public List<StudentDTO> findStudentByFacultyId(Long facultyId) {
        logger.info("Find student by faculty id = {} method was invoked", facultyId);
        List<Student> students = studentRepository.findStudentByFaculty_Id(facultyId);
        List<StudentDTO> studentDTOS = new ArrayList<>();
        for (Student student : students) {
            StudentDTO studentDTO = fromStudent(student);
            studentDTOS.add(studentDTO);
        }
        logger.info("Students with faculty id = {} were successfully found", facultyId);
        return studentDTOS;
    }

    @Override
    public List<StudentDTO> findAllStudents() {
        logger.info("Find all students method was invoked");
        List<Student> students = studentRepository.findAll();
        List<StudentDTO> studentDTOS = new ArrayList<>();
        for (Student student : students) {
            StudentDTO studentDTO = fromStudent(student);
            studentDTOS.add(studentDTO);
        }
        logger.info("All students were successfully found");
        return studentDTOS;
    }

    @Override
    public StudentDTO updateStudent(StudentDTO studentDTO) {
        logger.info("Update student: {} method was invoked", studentDTO);
        studentRepository.save(toStudent(studentDTO));
        logger.info("Student {} was updated successfully", studentDTO);
        return studentDTO;
    }

    @Override
    public void deleteStudent(Long studentId) {
        logger.info("Delete student by id = {} was invoked", studentId);
        studentRepository.deleteById(studentId);
        logger.info("Student with id = {} was deleted successfully", studentId);
    }

    @Override
    public void deleteAllStudents() {
        logger.info("Delete all students method was invoked");
        studentRepository.deleteAll();
        logger.info("All students sere deleted successfully");
    }

    @Override
    public Integer findAllStudentNumber() {
        logger.info("Find all student number method was invoked");
        Integer studentNumber = studentRepository.findAllStudentNumber();
        logger.info("Number of students was successfully found");
        return studentNumber;
    }

    @Override
    public Integer findAverageStudentAge() {
        logger.info("Find average student age method was invoked");
        Integer averageStudentAgeNumber = studentRepository.findAverageStudentAge();
        logger.info("Average student age number was successfully found");
        return averageStudentAgeNumber;
    }

    @Override
    public List<YoungestStudents> findTopYoungestStudents() {
        logger.info("Find top youngest students method was invoked");
        List<YoungestStudents> topYoungestStudents = studentRepository.findTopYoungestStudents();
        logger.info("Top youngest students were successfully found");
        return topYoungestStudents;
    }

    @Override
    public List<StudentDTO> findAllStudentsPageable(Integer pageNumber, Integer pageSize) {
        logger.info("Find all students pageable method was invoked");
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
        logger.info("All students pageable were successfully found");
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
