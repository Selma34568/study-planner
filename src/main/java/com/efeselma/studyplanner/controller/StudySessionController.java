package com.efeselma.studyplanner.controller;

import com.efeselma.studyplanner.dto.request.*;
import com.efeselma.studyplanner.dto.response.StudySessionResponse;
import com.efeselma.studyplanner.service.StudySessionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/study-sessions")
@RequiredArgsConstructor
@Validated
public class StudySessionController {
    private final StudySessionService studySessionService;

    @GetMapping
    public ResponseEntity<Page<StudySessionResponse>> getSessions(
            @RequestParam(required = false) LocalDate start,
            @RequestParam(required = false) LocalDate end,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "studyDate") String sortBy,
            @RequestParam(defaultValue = "ASC") String direction,
            Authentication authentication) {
        return ResponseEntity.ok(studySessionService.getSessions(authentication.getName(), start, end, page, size, sortBy, direction));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudySessionResponse> getSession(@PathVariable @Positive Long id, Authentication authentication) {
        return ResponseEntity.ok(studySessionService.getSession(id, authentication.getName()));
    }

    @PostMapping
    public ResponseEntity<StudySessionResponse> createSession(@Valid @RequestBody CreateStudySessionRequest request, Authentication authentication) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studySessionService.createSession(request, authentication.getName()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudySessionResponse> updateSession(@PathVariable @Positive Long id,
                                                              @Valid @RequestBody UpdateStudySessionRequest request,
                                                              Authentication authentication) {
        return ResponseEntity.ok(studySessionService.updateSession(id, request, authentication.getName()));
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<StudySessionResponse> markCompleted(@PathVariable @Positive Long id, Authentication authentication) {
        return ResponseEntity.ok(studySessionService.markCompleted(id, authentication.getName()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSession(@PathVariable @Positive Long id, Authentication authentication) {
        studySessionService.deleteSession(id, authentication.getName());
        return ResponseEntity.noContent().build();
    }
}
