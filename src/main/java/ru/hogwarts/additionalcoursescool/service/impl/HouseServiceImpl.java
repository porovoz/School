package ru.hogwarts.additionalcoursescool.service.impl;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import ru.hogwarts.additionalcoursescool.model.Faculty;
import ru.hogwarts.additionalcoursescool.service.HouseService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HouseServiceImpl implements HouseService {
    private static Map<Long, Faculty> facultyMap = new HashMap<>();
    private static Long facultyId = 1L;

    @Override
    public Faculty createFaculty(Faculty faculty) {
        facultyMap.putIfAbsent(facultyId++, faculty);
        return faculty;
    }

    @Override
    public Faculty findFacultyById(Long facultyId) {
        ObjectUtils.isNotEmpty(facultyMap);
        return facultyMap.get(facultyId);
    }

    @Override
    public List<Faculty> findFacultyByColor(String color) {
        return facultyMap.values().stream().filter(faculty -> faculty.getColor().equals(color)).toList();
    }

    @Override
    public Map<Long, Faculty> findAllFaculties() {
        ObjectUtils.isNotEmpty(facultyMap);
        return facultyMap;
    }

    @Override
    public Faculty updateFaculty(Long facultyId, Faculty faculty) {
        ObjectUtils.isNotEmpty(faculty);
        if(facultyMap.containsKey(facultyId)) {
            facultyMap.put(facultyId, faculty);
            return faculty;
        }
        return null;
    }

    @Override
    public Faculty deleteFaculty(Long facultyId) {
        ObjectUtils.isNotEmpty(facultyMap);
        if (facultyMap.containsKey(facultyId)) {
            return facultyMap.remove(facultyId);
        }
        return null;
    }

    @Override
    public void deleteAllFaculties() {
        facultyMap = new HashMap<>();
    }
}
