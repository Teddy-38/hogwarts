package com.example.hogwarts.repository;

import com.example.hogwarts.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    Optional<Faculty> findByNameIgnoreCaseOrColorIgnoreCase(String name, String color);

    Collection<Faculty> findByColorIgnoreCaseOrNameIgnoreCase(String color, String name);

    List<Faculty> findByColor(String color);
}