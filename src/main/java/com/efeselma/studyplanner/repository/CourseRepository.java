package com.efeselma.studyplanner.repository;

import com.efeselma.studyplanner.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Page<Course> findByOwnerUsernameAndDeletedFalse(String username, Pageable pageable);
    Page<Course> findByOwnerUsernameAndDeletedFalseAndTitleContainingIgnoreCase(String username, String title, Pageable pageable);
    Optional<Course> findByIdAndOwnerUsernameAndDeletedFalse(Long id, String username);
}
