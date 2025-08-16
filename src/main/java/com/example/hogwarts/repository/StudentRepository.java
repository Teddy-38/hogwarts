package com.example.hogwarts.repository;

import com.example.hogwarts.model.Student;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    
    List<Student> findByAge(int age);

    Collection<Student> findAll();
}