package com.example.hogwarts.repository;

import com.example.hogwarts.model.Faculty;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    List<Faculty> findByColor(String color);

    Faculty save(Faculty faculty);
}