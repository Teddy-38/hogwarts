package com.example.hogwarts.service;

import com.example.hogwarts.exception.StudentNotFoundException;
import com.example.hogwarts.model.Faculty;
import com.example.hogwarts.model.Student;
import com.example.hogwarts.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    private final Logger logger = LoggerFactory.getLogger(StudentService.class);


    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        logger.info("Was invoked method for create student");
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        logger.info("Was invoked method for find student with id: {}", id);
        return studentRepository.findById(id).orElseThrow(() -> {
            logger.error("There is not student with id = {}", id);
            return new StudentNotFoundException("Student not found with id: " + id);
        });
    }

    public Student editStudent(Student student) {
        logger.info("Was invoked method for edit student with id: {}", student.getId());
        logger.debug("Editing student details: {}", student);
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        logger.info("Was invoked method for delete student with id: {}", id);
        studentRepository.deleteById(id);
    }

    public Collection<Student> getAllStudents() {
        logger.info("Was invoked method for get all students");
        return studentRepository.findAll();
    }

    public List<Student> findByAge(int age) {
        logger.info("Was invoked method for find students by age: {}", age);
        List<Student> students = (List<Student>) studentRepository.findByAge(age);
        if (students.isEmpty()) {
            logger.warn("No students found with age: {}", age);
        }
        return students;
    }

    public Collection<Student> findByAgeBetween(int min, int max) {
        logger.info("Was invoked method for find students by age between {} and {}", min, max);
        return studentRepository.findByAgeBetween(min, max);
    }

    public Faculty getFacultyByStudent(long studentId) {
        logger.info("Was invoked method to get faculty by student id: {}", studentId);
        return findStudent(studentId).getFaculty();
    }

    public Faculty getFacultyByStudentId(Long id) {
    return null;
    }

    public Integer getTotalStudentCount() {
    return 0;
    }

    public Double getAverageStudentAge(int i) {
        return (double) 0;
    }

    public List<Student> findLastFiveStudents() {return List.of();
    }
    public List<String> getStudentNamesStartingWithA() {
        return studentRepository.findAll().stream()
                .map(Student::getName)
                .filter(name -> name.toUpperCase().startsWith("A"))
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.toList());
    }
    public Double getAverageStudentAge() {
        return studentRepository.findAll().stream()
                .mapToInt(Student::getAge)
                .average()
                .orElse(0.0);
    }
    public void printStudentsParallel() {
        List<Student> students = studentRepository.findAll();

        if (students.size() < 6) {
            logger.warn("Недостаточно студентов для демонстрации. Требуется минимум 6.");
            return;
        }

        printStudentName(students.get(0), "Основной поток");
        printStudentName(students.get(1), "Основной поток");

        new Thread(() -> {
            printStudentName(students.get(2), "Параллельный поток 1");
            printStudentName(students.get(3), "Параллельный поток 1");
        }).start();

        new Thread(() -> {
            printStudentName(students.get(4), "Параллельный поток 2");
            printStudentName(students.get(5), "Параллельный поток 2");
        }).start();
    }

    public void printStudentsSynchronized() {
        List<Student> students = studentRepository.findAll();

        if (students.size() < 6) {
            logger.warn("Недостаточно студентов для демонстрации. Требуется минимум 6.");
            return;
        }

        printStudentNameSynchronized(students.get(0), "Основной поток");
        printStudentNameSynchronized(students.get(1), "Основной поток");

        new Thread(() -> {
            printStudentNameSynchronized(students.get(2), "Параллельный поток 1");
            printStudentNameSynchronized(students.get(3), "Параллельный поток 1");
        }).start();

        new Thread(() -> {
            printStudentNameSynchronized(students.get(4), "Параллельный поток 2");
            printStudentNameSynchronized(students.get(5), "Параллельный поток 2");
        }).start();
    }

    private void printStudentName(Student student, String threadName) {
        // System.out.println() сам по себе потокобезопасен, но порядок вывода не гарантирован
        System.out.println(threadName + ": " + student.getName() + " (ID: " + student.getId() + ")");
    }

    private synchronized void printStudentNameSynchronized(Student student, String threadName) {

        System.out.println(threadName + " (синхр.): " + student.getName() + " (ID: " + student.getId() + ")");
    }
}
