package com.example.hogwarts;

import com.example.hogwarts.controller.StudentController;
import com.example.hogwarts.model.Student;
import com.example.hogwarts.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
class StudentControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateStudent() throws Exception {
        // given
        Student studentToCreate = new Student();
        studentToCreate.setName("Harry Potter");
        studentToCreate.setAge(11);

        Student createdStudent = new Student();
        createdStudent.setId(1L);
        createdStudent.setName("Harry Potter");
        createdStudent.setAge(11);

        when(studentService.createStudent(any(Student.class))).thenReturn(createdStudent);

        // when & then
        mockMvc.perform(post("/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentToCreate)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Harry Potter"));
    }

    @Test
    void testGetStudentById() throws Exception {
        // given
        Student student = new Student();
        student.setId(1L);
        student.setName("Hermione Granger");
        student.setAge(11);

        when(studentService.findStudent(1L)).thenReturn(student);

        mockMvc.perform(get("/student/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Hermione Granger"));
    }

    @Test
    void testFindByAgeBetween() throws Exception {
        // given
        Student student = new Student();
        student.setId(1L);
        student.setName("Ron Weasley");
        student.setAge(12);

        when(studentService.findByAgeBetween(12, 15)).thenReturn(List.of(student));

        mockMvc.perform(get("/student/age-between?min=12&max=15"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Ron Weasley"));
    }
}