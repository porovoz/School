package ru.hogwarts.additionalcoursescool.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.additionalcoursescool.model.Student;
import ru.hogwarts.additionalcoursescool.service.StudentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student")
@Tag(name = "Students", description = "CRUD-operations to work with the students")
public class StudentController {
private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    @Operation(
            summary = "Create new student",
            description = "Create new student with its number"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Student was successfully created",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema =
                                    @Schema(implementation = Student.class))
                            )
                    }
            )
    })
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student createdStudent = studentService.createStudent(student);
        return ResponseEntity.ok(createdStudent);
    }

    @GetMapping("/{studentId}")
    @Operation(
            summary = "Find student by its number",
            description = "Search by student number"
    )
    @Parameters(value = {
            @Parameter(name = "studentId", example = "1")
    })
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Student was successfully found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema =
                                    @Schema(implementation = Student.class))
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Student not found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema =
                                    @Schema(implementation = Student.class))
                            )
                    }
            )
    })
    public ResponseEntity<Student> getStudent(@PathVariable Long studentId) {
        Student student = studentService.findStudentById(studentId);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping("/findByAge")
    @Operation(
            summary = "Find student list by age",
            description = "Show student list by age"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Student list was successfully found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema =
                                    @Schema(implementation = Student.class))
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Student list was not found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema =
                                    @Schema(implementation = Student.class))
                            )
                    }
            )
    })
    public ResponseEntity<List<Student>> getStudentByAge(@RequestParam int age) {
        List<Student> students = studentService.findStudentByAge(age);
        if (students == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(students);
    }

    @GetMapping
    @Operation(
            summary = "Find all students",
            description = "Show all students"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Students were successfully found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema =
                                    @Schema(implementation = Student.class))
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Students were not found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema =
                                    @Schema(implementation = Student.class))
                            )
                    }
            )
    })
    public ResponseEntity<Map<Long, Student>> getAllStudents() {
        Map<Long, Student> students = studentService.findAllStudents();
        if (students == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(students);
    }

    @PutMapping("/{studentId}")
    @Operation(
            summary = "Update student by its number",
            description = "Search by student number to update it"
    )
    @Parameters(value = {
            @Parameter(name = "studentId", example = "1")
    })
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Student was successfully updated",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema =
                                    @Schema(implementation = Student.class))
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Student not found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema =
                                    @Schema(implementation = Student.class))
                            )
                    }
            )
    })
    public ResponseEntity<Student> updateStudent(@PathVariable Long studentId, @RequestBody Student student) {
        Student student1 = studentService.updateStudent(studentId, student);
        if (student1 == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student1);
    }

    @DeleteMapping("/{studentId}")
    @Operation(
            summary = "Delete student by its number",
            description = "Search by student number to delete it"
    )
    @Parameters(value = {
            @Parameter(name = "studentId", example = "1")
    })
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Student was successfully deleted"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Student not found"
            )
    })
    public ResponseEntity<Void> deleteStudentById(@PathVariable Long studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    @Operation(
            summary = "Delete all students",
            description = "Delete all students"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Students were successfully deleted"
            )
    })
    public ResponseEntity<Void> deleteAllStudents() {
        studentService.deleteAllStudents();
        return ResponseEntity.ok().build();
    }
}