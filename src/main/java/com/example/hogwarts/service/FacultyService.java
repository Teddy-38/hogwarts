package com.example.hogwarts.service;

import com.example.hogwarts.exception.ResourceNotFoundException;
import com.example.hogwarts.model.Faculty;
import com.example.hogwarts.model.Student;
import com.example.hogwarts.repository.FacultyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class FacultyService {

    private static final Logger logger = LoggerFactory.getLogger(FacultyService.class);

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        logger.info("Was invoked method for create faculty");
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(long id) {
        logger.info("Was invoked method for find faculty with id: {}", id);
        return facultyRepository.findById(id).orElseThrow(() -> {
            logger.error("There is no faculty with id = {}", id);
            return new ResourceNotFoundException("Faculty not found with id: " + id);
        });
    }

    public Faculty editFaculty(Faculty faculty) {
        logger.info("Was invoked method for edit faculty with id: {}", faculty.getId());
        logger.debug("Editing faculty details: {}", faculty);
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long id) {
        logger.info("Was invoked method for delete faculty with id: {}", id);
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> getAllFaculties() {
        logger.info("Was invoked method for get all faculties");
        return facultyRepository.findAll();
    }

    public List<Faculty> findByColor(String color) {
        logger.info("Was invoked method for find faculties by color: {}", color);
        List<Faculty> faculties = facultyRepository.findByColor(color);
        if (faculties.isEmpty()) {
            logger.warn("No faculties found with color: {}", color);
        }
        return faculties;
    }

    public Collection<Faculty> findByColorOrNameIgnoreCase(String color, String name) {
        logger.info("Was invoked method for find faculties by color or name (case-insensitive)");
        logger.debug("Search criteria: color='{}', name='{}'", color, name);
        return facultyRepository.findByColorIgnoreCaseOrNameIgnoreCase(color, name);
    }

    public Collection<Student> getStudentsByFaculty(long facultyId) {
        logger.info("Was invoked method to get students by faculty id: {}", facultyId);
        return findFaculty(facultyId).getStudents();
    }

    public Optional<Object> findByNameOrColor(String search) {
        return Optional.empty();
    }

    public Optional<Object> getStudentsByFacultyId(Long id) {return Optional.empty();
    }
}