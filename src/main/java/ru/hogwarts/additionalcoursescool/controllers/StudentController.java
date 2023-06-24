package ru.hogwarts.additionalcoursescool.controllers;

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
import ru.hogwarts.additionalcoursescool.dto.StudentDTO;
import ru.hogwarts.additionalcoursescool.model.Student;
import ru.hogwarts.additionalcoursescool.services.StudentService;

import java.util.List;

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
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO) {
        StudentDTO createdStudentDTO = studentService.createStudent(studentDTO);
        return ResponseEntity.ok(createdStudentDTO);
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
    public ResponseEntity<StudentDTO> getStudent(@PathVariable Long studentId) {
        StudentDTO studentDTO = studentService.findStudentById(studentId);
        if (studentDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentDTO);
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
    public ResponseEntity<List<StudentDTO>> getStudentByAge(@RequestParam int age) {
        List<StudentDTO> studentDTOS = studentService.findStudentByAge(age);
        if (studentDTOS == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentDTOS);
    }

    @GetMapping("/findByAgeBetween")
    @Operation(
            summary = "Find student list by age between",
            description = "Show student list by age between"
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
    public ResponseEntity<List<StudentDTO>> getStudentByAgeBetween(@RequestParam int min, @RequestParam int max) {
        List<StudentDTO> studentDTOS = studentService.findStudentByAgeBetween(min, max);
        if (studentDTOS == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentDTOS);
    }

    @GetMapping("/getStudentsByFacultyId")
    @Operation(
            summary = "Find student by faculty id",
            description = "Search by faculty id"
    )
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
    ResponseEntity<List<StudentDTO>> getStudentsByFacultyId(@RequestParam Long facultyId) {
        List<StudentDTO> studentDTOS = studentService.findStudentByFacultyId(facultyId);
        if (studentDTOS == null) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentDTOS);
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
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        List<StudentDTO> studentDTOS = studentService.findAllStudents();
        if (studentDTOS == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentDTOS);
    }

    @PutMapping
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
    public ResponseEntity<StudentDTO> updateStudent(@RequestBody StudentDTO studentDTO) {
        StudentDTO updatedStudentDTO = studentService.updateStudent(studentDTO);
        if (updatedStudentDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedStudentDTO);
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