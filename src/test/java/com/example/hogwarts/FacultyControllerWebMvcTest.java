package com.example.hogwarts;

import com.example.hogwarts.controller.FacultyController;
import com.example.hogwarts.model.Faculty;
import com.example.hogwarts.service.FacultyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FacultyController.class)
class FacultyControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyService facultyService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateFaculty() throws Exception {
        // given
        Faculty facultyToCreate = new Faculty();
        facultyToCreate.setName("Gryffindor");
        facultyToCreate.setColor("Red");

        Faculty createdFaculty = new Faculty();
        createdFaculty.setId(1L);
        createdFaculty.setName("Gryffindor");
        createdFaculty.setColor("Red");

        when(facultyService.createFaculty(facultyToCreate)).thenReturn(createdFaculty);

        mockMvc.perform(post("/faculty")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(facultyToCreate)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.color").value("Red"));
    }

    @Test
    void testFindFacultyByNameOrColor() throws Exception {
        // given
        Faculty faculty = new Faculty();
        faculty.setId(1L);
        faculty.setName("Ravenclaw");
        faculty.setColor("Blue");

        when(facultyService.findByNameOrColor("ravenclaw")).thenReturn(Optional.of(faculty));

        mockMvc.perform(get("/faculty/find?search=ravenclaw"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Ravenclaw"));
    }
}