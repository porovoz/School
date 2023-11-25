package com.bestapp.school.services.impl;

import com.bestapp.school.dto.FacultyDTO;
import com.bestapp.school.model.Faculty;
import com.bestapp.school.repositories.FacultyRepository;
import com.bestapp.school.services.FacultyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.bestapp.school.repositories.StudentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;
    private final StudentRepository studentRepository;
    private static final Logger logger = LoggerFactory.getLogger(FacultyServiceImpl.class);

    public FacultyServiceImpl(FacultyRepository facultyRepository,
                              StudentRepository studentRepository) {
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public FacultyDTO createFaculty(FacultyDTO facultyDTO) {
        logger.info("Create faculty method was invoked");
        facultyRepository.save(toFaculty(facultyDTO));
        logger.info("Faculty {} was created successfully", facultyDTO);
        return facultyDTO;
    }

    @Override
    public FacultyDTO findFacultyById(Long facultyId) {
        logger.info("Find faculty by id = {} method was invoked", facultyId);
        Faculty faculty = facultyRepository.findById(facultyId).get();
        logger.info("Faculty with id = {} was successfully found", facultyId);
        return fromFaculty(faculty);
    }

    @Override
    public List<FacultyDTO> findFacultyByColor(String color) {
        logger.info("Find faculty by color: {} method was invoked", color);
        List<Faculty> faculties = facultyRepository.findFacultyByColor(color);
        List<FacultyDTO> facultyDTOS = new ArrayList<>();
        for (Faculty faculty : faculties) {
            FacultyDTO facultyDTO = fromFaculty(faculty);
            facultyDTOS.add(facultyDTO);
        }
        logger.info("Faculty/ies with color: {} was/were successfully found", color);
        return facultyDTOS;
    }

    @Override
    public FacultyDTO findFacultyByNameIgnoreCase(String name) {
        logger.info("Find faculty by name: {} ignore case method was invoked", name);
        Faculty faculty = facultyRepository.findFacultyByNameIgnoreCase(name);
        logger.info("Faculty with name: {} was successfully found", name);
        return fromFaculty(faculty);
    }

    @Override
    public FacultyDTO findFacultyByStudentId(Long studentId) {
        logger.info("Find faculty by student id = {} method was invoked", studentId);
        Faculty faculty = facultyRepository.findFacultyById(studentRepository.findById(studentId).get().getFaculty().getId());
        logger.info("Faculty with student id = {} was successfully found", studentId);
        return fromFaculty(faculty);
    }

    @Override
    public List<FacultyDTO> findAllFaculties() {
        logger.info("Find all faculties method was invoked");
        List<Faculty> faculties = facultyRepository.findAll();
        List<FacultyDTO> facultyDTOS = new ArrayList<>();
        for (Faculty faculty : faculties) {
            FacultyDTO facultyDTO = fromFaculty(faculty);
            facultyDTOS.add(facultyDTO);
        }
        logger.info("All faculties were successfully found");
        return facultyDTOS;
    }

    @Override
    public FacultyDTO updateFaculty(FacultyDTO facultyDTO) {
        logger.info("Update faculty {} method was invoked", facultyDTO);
        facultyRepository.save(toFaculty(facultyDTO));
        logger.info("Faculty {} was updated successfully", facultyDTO);
        return facultyDTO;
    }

    @Override
    public void deleteFaculty(Long facultyId) {
        logger.info("Delete faculty by id = {} method was invoked", facultyId);
        facultyRepository.deleteById(facultyId);
        logger.info("Faculty with id = {} was deleted successfully", facultyId);
    }

    @Override
    public void deleteAllFaculties() {
        logger.info("Delete all faculties method was invoked");
        facultyRepository.deleteAll();
        logger.info("All faculties were deleted successfully");
    }

    public static FacultyDTO fromFaculty(Faculty faculty) {
        FacultyDTO dto = new FacultyDTO();
        dto.setId(faculty.getId());
        dto.setName(faculty.getName());
        dto.setColor(faculty.getColor());
        return dto;
    }

    public static Faculty toFaculty(FacultyDTO facultyDTO) {
        return new Faculty(
                facultyDTO.getId(),
                facultyDTO.getName(),
                facultyDTO.getColor()
        );
    }
}