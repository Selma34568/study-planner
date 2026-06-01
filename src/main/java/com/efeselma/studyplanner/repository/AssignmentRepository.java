package com.efeselma.studyplanner.repository;

import com.efeselma.studyplanner.model.Assignment;
import com.efeselma.studyplanner.model.AssignmentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    Page<Assignment> findByCourseOwnerUsernameAndDeletedFalse(String username, Pageable pageable);
    Page<Assignment> findByCourseOwnerUsernameAndDeletedFalseAndStatus(String username, AssignmentStatus status, Pageable pageable);
    Page<Assignment> findByCourseOwnerUsernameAndDeletedFalseAndTitleContainingIgnoreCase(String username, String title, Pageable pageable);
    Optional<Assignment> findByIdAndCourseOwnerUsernameAndDeletedFalse(Long id, String username);
    List<Assignment> findByDeletedFalseAndStatusNotAndDeadlineBefore(AssignmentStatus status, LocalDateTime now);
}
