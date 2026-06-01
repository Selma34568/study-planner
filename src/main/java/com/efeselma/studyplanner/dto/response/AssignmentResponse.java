package com.efeselma.studyplanner.dto.response;

import com.efeselma.studyplanner.model.AssignmentStatus;
import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class AssignmentResponse {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime deadline;
    private AssignmentStatus status;
    private CourseSummaryResponse course;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
