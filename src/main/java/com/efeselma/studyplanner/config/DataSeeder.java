package com.efeselma.studyplanner.config;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.efeselma.studyplanner.model.Assignment;
import com.efeselma.studyplanner.model.AssignmentStatus;
import com.efeselma.studyplanner.model.Course;
import com.efeselma.studyplanner.model.Role;
import com.efeselma.studyplanner.model.StudySession;
import com.efeselma.studyplanner.model.User;
import com.efeselma.studyplanner.repository.AssignmentRepository;
import com.efeselma.studyplanner.repository.CourseRepository;
import com.efeselma.studyplanner.repository.RoleRepository;
import com.efeselma.studyplanner.repository.StudySessionRepository;
import com.efeselma.studyplanner.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class DataSeeder {

    private final PasswordEncoder passwordEncoder;

    @Bean
    @Profile("dev")
    CommandLineRunner initData(RoleRepository roleRepository,
                               UserRepository userRepository,
                               CourseRepository courseRepository,
                               AssignmentRepository assignmentRepository,
                               StudySessionRepository studySessionRepository) {
        return args -> {
            Role userRole = roleRepository.findByName("USER")
                    .orElseGet(() -> roleRepository.save(
                            Role.builder()
                                    .name("USER")
                                    .description("Regular user")
                                    .build()
                    ));

            Role adminRole = roleRepository.findByName("ADMIN")
                    .orElseGet(() -> roleRepository.save(
                            Role.builder()
                                    .name("ADMIN")
                                    .description("Administrator")
                                    .build()
                    ));

            if (!userRepository.existsByUsername("student")) {
                User student = User.builder()
                        .username("student")
                        .email("student@example.com")
                        .password(passwordEncoder.encode("password123"))
                        .firstName("Demo")
                        .lastName("Student")
                        .roles(new HashSet<>(Set.of(userRole)))
                        .build();

                userRepository.save(student);

                Course course = courseRepository.save(Course.builder()
                        .title("Desktop Application Programming II")
                        .description("Spring Boot backend development course")
                        .color("blue")
                        .owner(student)
                        .build());

                assignmentRepository.save(Assignment.builder()
                        .title("Complete Study Planner API")
                        .description("Implement Spring Boot REST API with JWT security")
                        .deadline(LocalDateTime.now().plusDays(7))
                        .status(AssignmentStatus.TODO)
                        .course(course)
                        .build());

                studySessionRepository.save(StudySession.builder()
                        .topic("Practice DTOs and Validation")
                        .studyDate(LocalDate.now().plusDays(1))
                        .startTime(LocalTime.of(18, 0))
                        .endTime(LocalTime.of(19, 30))
                        .course(course)
                        .build());
            }

            if (!userRepository.existsByUsername("admin")) {
                User admin = User.builder()
                        .username("admin")
                        .email("admin@example.com")
                        .password(passwordEncoder.encode("admin1234"))
                        .firstName("Demo")
                        .lastName("Admin")
                        .roles(new HashSet<>(Set.of(userRole, adminRole)))
                        .build();

                userRepository.save(admin);
            }
        };
    }
}