package com.efeselma.studyplanner.service;

import com.efeselma.studyplanner.dto.request.*;
import com.efeselma.studyplanner.dto.response.StudySessionResponse;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public interface StudySessionService {
    Page<StudySessionResponse> getSessions(String username, LocalDate start, LocalDate end, int page, int size, String sortBy, String direction);
    StudySessionResponse getSession(Long id, String username);
    StudySessionResponse createSession(CreateStudySessionRequest request, String username);
    StudySessionResponse updateSession(Long id, UpdateStudySessionRequest request, String username);
    StudySessionResponse markCompleted(Long id, String username);
    void deleteSession(Long id, String username);
}
