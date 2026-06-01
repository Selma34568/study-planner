package com.efeselma.studyplanner.repository;

import com.efeselma.studyplanner.model.StudySession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface StudySessionRepository extends JpaRepository<StudySession, Long> {
    Page<StudySession> findByCourseOwnerUsernameAndDeletedFalse(String username, Pageable pageable);
    Page<StudySession> findByCourseOwnerUsernameAndDeletedFalseAndStudyDateBetween(String username, LocalDate start, LocalDate end, Pageable pageable);
    Optional<StudySession> findByIdAndCourseOwnerUsernameAndDeletedFalse(Long id, String username);
}
