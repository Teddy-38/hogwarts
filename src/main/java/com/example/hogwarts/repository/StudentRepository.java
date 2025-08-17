package com.example.hogwarts.repository;

import com.example.hogwarts.model.Student;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    
    List<Student> findByAge(int age);

    Collection<Student> findAll();

    Student save(Student student);

    Optional<Object> findById(long id);

    void deleteById(long id);
}