package com.example.hogwarts.repository;

import com.example.hogwarts.model.Faculty;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository extends com.example.hogwarts.repository.JpaRepository<Faculty, Long> {
}