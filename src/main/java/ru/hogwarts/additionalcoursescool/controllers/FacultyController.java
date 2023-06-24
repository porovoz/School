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
import ru.hogwarts.additionalcoursescool.dto.FacultyDTO;
import ru.hogwarts.additionalcoursescool.model.Faculty;
import ru.hogwarts.additionalcoursescool.services.FacultyService;

import java.util.List;

@RestController
@RequestMapping("/faculty")
@Tag(name = "Faculties", description = "CRUD-operations to work with the faculties")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    @Operation(
            summary = "Create new faculty",
            description = "Create new faculty with its number"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Faculty was successfully created",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema =
                                    @Schema(implementation = Faculty.class))
                            )
                    }
            )
    })
    public ResponseEntity<FacultyDTO> createFaculty(@RequestBody FacultyDTO facultyDTO) {
        FacultyDTO createdFacultyDTO = facultyService.createFaculty(facultyDTO);
        return ResponseEntity.ok(createdFacultyDTO);
    }

    @GetMapping("/{facultyId}")
    @Operation(
            summary = "Find faculty by its number",
            description = "Search by faculty number"
    )
    @Parameters(value = {
            @Parameter(name = "facultyId", example = "1")
    })
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Faculty was successfully found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema =
                                    @Schema(implementation = Faculty.class))
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Faculty not found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema =
                                    @Schema(implementation = Faculty.class))
                            )
                    }
            )
    })
    public ResponseEntity<FacultyDTO> getFaculty(@PathVariable Long facultyId) {
        FacultyDTO facultyDTO = facultyService.findFacultyById(facultyId);
        if (facultyDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(facultyDTO);
    }

    @GetMapping("/findByColor")
    @Operation(
            summary = "Find faculty list by color",
            description = "Show faculty list by color"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Faculty list was successfully found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema =
                                    @Schema(implementation = Faculty.class))
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Faculty list was not found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema =
                                    @Schema(implementation = Faculty.class))
                            )
                    }
            )
    })
    public ResponseEntity<List<FacultyDTO>> getFacultyByColor(@RequestParam String color) {
        List<FacultyDTO> facultyDTOS = facultyService.findFacultyByColor(color);
        if (facultyDTOS == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(facultyDTOS);
    }

    @GetMapping("/findFacultyByName")
    @Operation(
            summary = "Find faculty by name",
            description = "Show faculty by name"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Faculty was successfully found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema =
                                    @Schema(implementation = Faculty.class))
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Faculty was not found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema =
                                    @Schema(implementation = Faculty.class))
                            )
                    }
            )
    })
    public ResponseEntity<FacultyDTO> getFacultyByName(@RequestParam String name) {
        FacultyDTO facultyDTO = facultyService.findFacultyByNameIgnoreCase(name);
        if (facultyDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(facultyDTO);
    }

    @GetMapping("/findByStudentId")
    @Operation(
            summary = "Find faculty by student id",
            description = "Show faculty by student id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Faculty was successfully found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema =
                                    @Schema(implementation = Faculty.class))
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Faculty was not found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema =
                                    @Schema(implementation = Faculty.class))
                            )
                    }
            )
    })
    public ResponseEntity<FacultyDTO> getFacultyByStudentId(@RequestParam Long studentId) {
        FacultyDTO facultyDTO = facultyService.findFacultyByStudentId(studentId);
        if (facultyDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(facultyDTO);
    }

    @GetMapping
    @Operation(
            summary = "Find all faculties",
            description = "Show all faculties"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Faculties were successfully found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema =
                                    @Schema(implementation = Faculty.class))
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Faculties were not found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema =
                                    @Schema(implementation = Faculty.class))
                            )
                    }
            )
    })
    public ResponseEntity<List<FacultyDTO>> getAllFaculties() {
        List<FacultyDTO> facultyDTOS = facultyService.findAllFaculties();
        if (facultyDTOS == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(facultyDTOS);
    }

    @PutMapping
    @Operation(
            summary = "Update faculty by its number",
            description = "Search by faculty number to update it"
    )
    @Parameters(value = {
            @Parameter(name = "facultyId", example = "1")
    })
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Faculty was successfully updated",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema =
                                    @Schema(implementation = Faculty.class))
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Faculty not found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema =
                                    @Schema(implementation = Faculty.class))
                            )
                    }
            )
    })
    public ResponseEntity<FacultyDTO> updateFaculty(@RequestBody FacultyDTO facultyDTO) {
        FacultyDTO updatedFacultyDTO = facultyService.updateFaculty(facultyDTO);
        if (updatedFacultyDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedFacultyDTO);
    }

    @DeleteMapping("/{facultyId}")
    @Operation(
            summary = "Delete faculty by its number",
            description = "Search by faculty number to delete it"
    )
    @Parameters(value = {
            @Parameter(name = "facultyId", example = "1")
    })
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Faculty was successfully deleted"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Faculty not found"
            )
    })
    public ResponseEntity<Void> deleteFacultyById(@PathVariable Long facultyId) {
        facultyService.deleteFaculty(facultyId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    @Operation(
            summary = "Delete all faculties",
            description = "Delete all faculties"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Faculties were successfully deleted"
            )
    })
    public ResponseEntity<Void> deleteAllFaculties() {
        facultyService.deleteAllFaculties();
        return ResponseEntity.ok().build();
    }
}