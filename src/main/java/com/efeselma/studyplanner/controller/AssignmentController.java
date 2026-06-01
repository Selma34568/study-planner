package com.efeselma.studyplanner.controller;

import com.efeselma.studyplanner.dto.request.*;
import com.efeselma.studyplanner.dto.response.AssignmentResponse;
import com.efeselma.studyplanner.model.AssignmentStatus;
import com.efeselma.studyplanner.service.AssignmentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assignments")
@RequiredArgsConstructor
@Validated
public class AssignmentController {
    private final AssignmentService assignmentService;

    @GetMapping
    public ResponseEntity<Page<AssignmentResponse>> getAssignments(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) AssignmentStatus status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "DESC") String direction,
            Authentication authentication) {
        return ResponseEntity.ok(assignmentService.getAssignments(authentication.getName(), search, status, page, size, sortBy, direction));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssignmentResponse> getAssignment(@PathVariable @Positive Long id, Authentication authentication) {
        return ResponseEntity.ok(assignmentService.getAssignment(id, authentication.getName()));
    }

    @PostMapping
    public ResponseEntity<AssignmentResponse> createAssignment(@Valid @RequestBody CreateAssignmentRequest request, Authentication authentication) {
        return ResponseEntity.status(HttpStatus.CREATED).body(assignmentService.createAssignment(request, authentication.getName()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssignmentResponse> updateAssignment(@PathVariable @Positive Long id,
                                                               @Valid @RequestBody UpdateAssignmentRequest request,
                                                               Authentication authentication) {
        return ResponseEntity.ok(assignmentService.updateAssignment(id, request, authentication.getName()));
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<AssignmentResponse> markCompleted(@PathVariable @Positive Long id, Authentication authentication) {
        return ResponseEntity.ok(assignmentService.markCompleted(id, authentication.getName()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssignment(@PathVariable @Positive Long id, Authentication authentication) {
        assignmentService.deleteAssignment(id, authentication.getName());
        return ResponseEntity.noContent().build();
    }
}
