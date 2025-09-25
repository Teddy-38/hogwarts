package com.example.hogwarts.repository;

import com.example.hogwarts.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository <Student, Long> {

    List<Student> findByAgeBetween(int min, int max);

    @Query("SELECT COUNT(s) FROM Student s")
    Integer getTotalStudentCount();

    @Query("SELECT AVG(s.age) FROM Student s")
    Double getAverageStudentAge();

    @Query(value = "SELECT * FROM student ORDER BY id DESC LIMIT 5", nativeQuery = true)
    List<Student> findLastFiveStudents();
}