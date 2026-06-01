package com.efeselma.studyplanner.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class CreateCourseRequest {
    @NotBlank(message = "Course title is required")
    @Size(min = 2, max = 150, message = "Course title must be between 2 and 150 characters")
    private String title;

    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;

    @Size(max = 20, message = "Color cannot exceed 20 characters")
    private String color;
}
