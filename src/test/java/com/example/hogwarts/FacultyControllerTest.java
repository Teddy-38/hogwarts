package com.example.hogwarts;

import com.example.hogwarts.model.Faculty;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FacultyControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    FacultyControllerTest(TestRestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Test
    void testCreateFaculty() {

        Faculty faculty = new Faculty();
        faculty.setName("Slytherin");
        faculty.setColor("Green");

        ResponseEntity<Faculty> response = restTemplate.postForEntity("/faculty", faculty, Faculty.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getId()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo("Slytherin");
    }

    @Test
    void testFindFacultyByNameOrColor() {

        Faculty faculty = new Faculty();
        faculty.setName("Hufflepuff");
        faculty.setColor("Yellow");
        restTemplate.postForObject("/faculty", faculty, Faculty.class);

        String url = "/faculty/find?search=hufflepuff"; // поиск по имени, игнорируя регистр
        ResponseEntity<Faculty> response = restTemplate.getForEntity(url, Faculty.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getColor()).isEqualTo("Yellow");
    }
}