package com.efeselma.studyplanner.dto.response;

import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class CourseResponse {
    private Long id;
    private String title;
    private String description;
    private String color;
    private int assignmentCount;
    private int studySessionCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
