package com.efeselma.studyplanner.dto.request;

import com.efeselma.studyplanner.validation.FutureDateTime;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class CreateAssignmentRequest {
    @NotBlank(message = "Assignment title is required")
    @Size(min = 3, max = 200, message = "Assignment title must be between 3 and 200 characters")
    private String title;

    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;

    @NotNull(message = "Deadline is required")
    @FutureDateTime(message = "Deadline must be in the future")
    private LocalDateTime deadline;

    @NotNull(message = "Course ID is required")
    @Positive(message = "Course ID must be positive")
    private Long courseId;
}
