package com.efeselma.studyplanner.service;

import com.efeselma.studyplanner.dto.request.*;
import com.efeselma.studyplanner.dto.response.AssignmentResponse;
import com.efeselma.studyplanner.model.AssignmentStatus;
import org.springframework.data.domain.Page;

public interface AssignmentService {
    Page<AssignmentResponse> getAssignments(String username, String search, AssignmentStatus status, int page, int size, String sortBy, String direction);
    AssignmentResponse getAssignment(Long id, String username);
    AssignmentResponse createAssignment(CreateAssignmentRequest request, String username);
    AssignmentResponse updateAssignment(Long id, UpdateAssignmentRequest request, String username);
    AssignmentResponse markCompleted(Long id, String username);
    void deleteAssignment(Long id, String username);
    int markOverdueAssignments();
}
