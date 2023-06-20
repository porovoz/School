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
import ru.hogwarts.additionalcoursescool.model.Faculty;
import ru.hogwarts.additionalcoursescool.service.HouseService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/faculty")
@Tag(name = "Faculties", description = "CRUD-operations to work with the faculties")
public class HouseController {
    private final HouseService houseService;

    public HouseController(HouseService houseService) {
        this.houseService = houseService;
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
    public ResponseEntity<Faculty> createFaculty(@RequestBody Faculty faculty) {
        Faculty createdFaculty = houseService.createFaculty(faculty);
        return ResponseEntity.ok(createdFaculty);
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
    public ResponseEntity<Faculty> getFaculty(@PathVariable Long facultyId) {
        Faculty faculty = houseService.findFacultyById(facultyId);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @GetMapping("/findByAColor")
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
    public ResponseEntity<List<Faculty>> getFacultyByColor(@RequestParam String color) {
        List<Faculty> faculties = houseService.findFacultyByColor(color);
        if (faculties == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculties);
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
    public ResponseEntity<Map<Long, Faculty>> getAllFaculties() {
        Map<Long, Faculty> faculties = houseService.findAllFaculties();
        if (faculties == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculties);
    }

    @PutMapping("/{facultyId}")
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
    public ResponseEntity<Faculty> updateFaculty(@PathVariable Long facultyId, @RequestBody Faculty faculty) {
        Faculty faculty1 = houseService.updateFaculty(facultyId, faculty);
        if (faculty1 == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty1);
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
        houseService.deleteFaculty(facultyId);
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
        houseService.deleteAllFaculties();
        return ResponseEntity.ok().build();
    }
}