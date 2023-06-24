package ru.hogwarts.additionalcoursescool.services.impl;

import org.springframework.stereotype.Service;
import ru.hogwarts.additionalcoursescool.dto.FacultyDTO;
import ru.hogwarts.additionalcoursescool.model.Faculty;
import ru.hogwarts.additionalcoursescool.repositories.FacultyRepository;
import ru.hogwarts.additionalcoursescool.repositories.StudentRepository;
import ru.hogwarts.additionalcoursescool.services.FacultyService;

import java.util.ArrayList;
import java.util.List;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;
    private final StudentRepository studentRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository,
                              StudentRepository studentRepository) {
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public FacultyDTO createFaculty(FacultyDTO facultyDTO) {
        facultyRepository.save(toFaculty(facultyDTO));
        return facultyDTO;
    }

    @Override
    public FacultyDTO findFacultyById(Long facultyId) {
        Faculty faculty = facultyRepository.findById(facultyId).get();
        return fromFaculty(faculty);
    }

    @Override
    public List<FacultyDTO> findFacultyByColor(String color) {
        List<Faculty> faculties = facultyRepository.findFacultyByColor(color);
        List<FacultyDTO> facultyDTOS = new ArrayList<>();
        for (Faculty faculty : faculties) {
            FacultyDTO facultyDTO = fromFaculty(faculty);
            facultyDTOS.add(facultyDTO);
        }
        return facultyDTOS;
    }

    @Override
    public FacultyDTO findFacultyByNameIgnoreCase(String name) {
        Faculty faculty = facultyRepository.findFacultyByNameIgnoreCase(name);
        return fromFaculty(faculty);
    }

    @Override
    public FacultyDTO findFacultyByStudentId(Long studentId) {
        Faculty faculty = facultyRepository.findFacultyById(studentRepository.findById(studentId).get().getFaculty().getId());
        return fromFaculty(faculty);
    }

    @Override
    public List<FacultyDTO> findAllFaculties() {
        List<Faculty> faculties = facultyRepository.findAll();
        List<FacultyDTO> facultyDTOS = new ArrayList<>();
        for (Faculty faculty : faculties) {
            FacultyDTO facultyDTO = fromFaculty(faculty);
            facultyDTOS.add(facultyDTO);
        }
        return facultyDTOS;
    }

    @Override
    public FacultyDTO updateFaculty(FacultyDTO facultyDTO) {
        facultyRepository.save(toFaculty(facultyDTO));
        return facultyDTO;
    }

    @Override
    public void deleteFaculty(Long facultyId) {
        facultyRepository.deleteById(facultyId);
    }

    @Override
    public void deleteAllFaculties() {
        facultyRepository.deleteAll();
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