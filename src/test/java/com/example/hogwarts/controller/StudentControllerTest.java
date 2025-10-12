package com.example.hogwarts.controller;

import com.example.hogwarts.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Test
    void printStudentsParallel_whenCalled_thenServiceMethodIsCalled() throws Exception {
        mockMvc.perform(get("/students/print-parallel"))
                .andExpect(status().isOk());

        verify(studentService).printStudentsParallel();
    }

    @Test
    void printStudentsSynchronized_whenCalled_thenServiceMethodIsCalled() throws Exception {
        mockMvc.perform(get("/students/print-synchronized"))
                .andExpect(status().isOk());

        verify(studentService).printStudentsSynchronized();
    }
}