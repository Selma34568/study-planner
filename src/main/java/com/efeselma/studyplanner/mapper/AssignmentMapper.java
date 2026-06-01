package com.efeselma.studyplanner.mapper;

import com.efeselma.studyplanner.dto.request.*;
import com.efeselma.studyplanner.dto.response.AssignmentResponse;
import com.efeselma.studyplanner.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AssignmentMapper {
    private final CourseMapper courseMapper;

    public AssignmentResponse toResponse(Assignment assignment) {
        return AssignmentResponse.builder()
                .id(assignment.getId())
                .title(assignment.getTitle())
                .description(assignment.getDescription())
                .deadline(assignment.getDeadline())
                .status(assignment.getStatus())
                .course(courseMapper.toSummary(assignment.getCourse()))
                .createdAt(assignment.getCreatedAt())
                .updatedAt(assignment.getUpdatedAt())
                .build();
    }

    public Assignment toEntity(CreateAssignmentRequest request) {
        return Assignment.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .deadline(request.getDeadline())
                .status(AssignmentStatus.TODO)
                .build();
    }

    public void updateEntity(Assignment assignment, UpdateAssignmentRequest request) {
        assignment.setTitle(request.getTitle());
        assignment.setDescription(request.getDescription());
        assignment.setDeadline(request.getDeadline());
        assignment.setStatus(request.getStatus());
    }
}
