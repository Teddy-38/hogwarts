package com.example.hogwarts.controller;


import com.example.hogwarts.model.Faculty;
import com.example.hogwarts.model.Student;
import com.example.hogwarts.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/age-between")
    public ResponseEntity<List<Student>> getStudentsByAgeBetween(@RequestParam int min, @RequestParam int max) {
        List<Student> students = (List<Student>) studentService.findByAgeBetween(min, max);
        if (students.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}/faculty")
    public ResponseEntity<Faculty> getFacultyByStudent(@PathVariable Long id) {
        Faculty faculty = studentService.getFacultyByStudentId(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }
    @GetMapping("/count")
    public ResponseEntity<Integer> getTotalStudentCount() {
        return ResponseEntity.ok(studentService.getTotalStudentCount());
    }

    @GetMapping("/average-age")
    public ResponseEntity<Double> getAverageStudentAge() {
        return ResponseEntity.ok(studentService.getAverageStudentAge(1));
    }

    @GetMapping("/last-five")
    public ResponseEntity<List<Student>> getLastFiveStudents() {
        return ResponseEntity.ok(studentService.findLastFiveStudents());
    }
    @GetMapping("/by-first-letter-a")
    public List<String> getStudentNamesStartingWithA() {
        return studentService.getStudentNamesStartingWithA();
    }

    @GetMapping("/print-parallel")
    public void printStudentsParallel() {
        studentService.printStudentsParallel();
    }

    @GetMapping("/print-synchronized")
    public void printStudentsSynchronized() {
        studentService.printStudentsSynchronized();
    }
}