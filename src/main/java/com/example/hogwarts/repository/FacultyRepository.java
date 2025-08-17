package com.example.hogwarts.repository;

import com.example.hogwarts.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    List<Faculty> findByColor(String color);

    Faculty save(Faculty faculty);

    Optional<Object> findById(long id);

    void deleteById(long id);
}