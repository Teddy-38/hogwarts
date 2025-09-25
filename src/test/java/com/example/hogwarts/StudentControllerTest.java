package com.example.hogwarts;

import com.example.hogwarts.model.Student;
import org.testng.annotations.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testCreateStudent() {
        Student student = new Student();
        student.setName("Draco Malfoy");
        student.setAge(12);

        ResponseEntity<Student> response = restTemplate.postForEntity("/student", student, Student.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo("Draco Malfoy");
    }

    @Test
    void testGetStudentById() {

        Student student = new Student();
        student.setName("Test Student");
        student.setAge(15);
        Student savedStudent = restTemplate.postForObject("/student", student, Student.class);

        ResponseEntity<Student> response = restTemplate.getForEntity("/student/" + savedStudent.getId(), Student.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo("Test Student");
    }

    @Test
    void testFindByAgeBetween() {
        Student student1 = new Student();
        student1.setName("Student13");
        student1.setAge(13);
        restTemplate.postForObject("/student", student1, Student.class);

        Student student2 = new Student();
        student2.setName("Student17");
        student2.setAge(17);
        restTemplate.postForObject("/student", student2, Student.class);

        String url = "/student/age-between?min=12&max=15";
        ResponseEntity<Student[]> response = restTemplate.getForEntity(url, Student[].class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().length).isGreaterThan(0);
        assertThat(response.getBody()).anyMatch(s -> s.getAge() == 13);
    }
}