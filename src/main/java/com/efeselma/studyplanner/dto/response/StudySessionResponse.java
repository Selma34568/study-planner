package com.efeselma.studyplanner.dto.response;

import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class StudySessionResponse {
    private Long id;
    private String topic;
    private LocalDate studyDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean completed;
    private CourseSummaryResponse course;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
