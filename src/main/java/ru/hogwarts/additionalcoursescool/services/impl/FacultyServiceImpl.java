package ru.hogwarts.additionalcoursescool.services.impl;

import org.springframework.stereotype.Service;
import ru.hogwarts.additionalcoursescool.model.Faculty;
import ru.hogwarts.additionalcoursescool.repositories.FacultyRepository;
import ru.hogwarts.additionalcoursescool.services.FacultyService;

import java.util.Collection;
import java.util.List;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty findFacultyById(Long facultyId) {
        return facultyRepository.findById(facultyId).get();
    }

    @Override
    public List<Faculty> findFacultyByColor(String color) {
        return facultyRepository.findFacultyByColor(color);
    }

    @Override
    public Collection<Faculty> findAllFaculties() {
        return facultyRepository.findAll();
    }

    @Override
    public Faculty updateFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public void deleteFaculty(Long facultyId) {
        facultyRepository.deleteById(facultyId);
    }

    @Override
    public void deleteAllFaculties() {
        facultyRepository.deleteAll();
    }
}
