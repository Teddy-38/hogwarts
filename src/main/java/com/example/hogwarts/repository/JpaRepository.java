package com.example.hogwarts.repository;

import com.example.hogwarts.model.Student;

import java.util.Optional;

public interface JpaRepository<T, T1> {
    Optional<Object> findById(long id);

    void deleteById(long id);

    Student save(Student student);
}
