package com.efeselma.studyplanner;

import com.efeselma.studyplanner.dto.request.CreateCourseRequest;
import com.efeselma.studyplanner.dto.response.CourseResponse;
import com.efeselma.studyplanner.repository.UserRepository;
import com.efeselma.studyplanner.service.CourseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class CourseServiceImplTest {
    @Autowired
    CourseService courseService;

    @Autowired
    UserRepository userRepository;

    @Test
    void shouldCreateCourseForDemoStudent() {
        assertThat(userRepository.existsByUsername("student")).isTrue();

        CreateCourseRequest request = new CreateCourseRequest("Algorithms", "Algorithm practice", "green");
        CourseResponse response = courseService.createCourse(request, "student");

        assertThat(response.getId()).isNotNull();
        assertThat(response.getTitle()).isEqualTo("Algorithms");
    }
}
