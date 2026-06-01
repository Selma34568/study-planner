package com.efeselma.studyplanner.service;

import com.efeselma.studyplanner.dto.request.*;
import com.efeselma.studyplanner.dto.response.StudySessionResponse;
import com.efeselma.studyplanner.exception.*;
import com.efeselma.studyplanner.mapper.StudySessionMapper;
import com.efeselma.studyplanner.model.*;
import com.efeselma.studyplanner.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional
public class StudySessionServiceImpl implements StudySessionService {
    private final StudySessionRepository studySessionRepository;
    private final CourseRepository courseRepository;
    private final StudySessionMapper studySessionMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<StudySessionResponse> getSessions(String username, LocalDate start, LocalDate end, int page, int size, String sortBy, String direction) {
        Pageable pageable = createPageable(page, size, sortBy, direction);
        Page<StudySession> result = (start != null && end != null)
                ? studySessionRepository.findByCourseOwnerUsernameAndDeletedFalseAndStudyDateBetween(username, start, end, pageable)
                : studySessionRepository.findByCourseOwnerUsernameAndDeletedFalse(username, pageable);
        return result.map(studySessionMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public StudySessionResponse getSession(Long id, String username) {
        return studySessionMapper.toResponse(findOwnedSession(id, username));
    }

    @Override
    public StudySessionResponse createSession(CreateStudySessionRequest request, String username) {
        validateTimeRange(request.getStartTime(), request.getEndTime());
        Course course = courseRepository.findByIdAndOwnerUsernameAndDeletedFalse(request.getCourseId(), username)
                .orElseThrow(() -> new ResourceNotFoundException("Course", request.getCourseId()));
        StudySession session = studySessionMapper.toEntity(request);
        session.setCourse(course);
        return studySessionMapper.toResponse(studySessionRepository.save(session));
    }

    @Override
    public StudySessionResponse updateSession(Long id, UpdateStudySessionRequest request, String username) {
        validateTimeRange(request.getStartTime(), request.getEndTime());
        StudySession session = findOwnedSession(id, username);
        studySessionMapper.updateEntity(session, request);
        return studySessionMapper.toResponse(studySessionRepository.save(session));
    }

    @Override
    public StudySessionResponse markCompleted(Long id, String username) {
        StudySession session = findOwnedSession(id, username);
        session.setCompleted(true);
        return studySessionMapper.toResponse(studySessionRepository.save(session));
    }

    @Override
    public void deleteSession(Long id, String username) {
        StudySession session = findOwnedSession(id, username);
        session.setDeleted(true);
        studySessionRepository.save(session);
    }

    private StudySession findOwnedSession(Long id, String username) {
        return studySessionRepository.findByIdAndCourseOwnerUsernameAndDeletedFalse(id, username)
                .orElseThrow(() -> new ResourceNotFoundException("StudySession", id));
    }

    private void validateTimeRange(java.time.LocalTime start, java.time.LocalTime end) {
        if (!end.isAfter(start)) {
            throw new InvalidOperationException("End time must be after start time");
        }
    }

    private Pageable createPageable(int page, int size, String sortBy, String direction) {
        Sort.Direction sortDirection = direction.equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
        return PageRequest.of(page, size, Sort.by(sortDirection, sortBy));
    }
}
