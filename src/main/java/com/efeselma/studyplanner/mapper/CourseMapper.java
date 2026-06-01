package com.efeselma.studyplanner.mapper;

import com.efeselma.studyplanner.dto.request.*;
import com.efeselma.studyplanner.dto.response.*;
import com.efeselma.studyplanner.model.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {
    public CourseResponse toResponse(Course course) {
        return CourseResponse.builder()
                .id(course.getId())
                .title(course.getTitle())
                .description(course.getDescription())
                .color(course.getColor())
                .assignmentCount(course.getAssignments() == null ? 0 : course.getAssignments().size())
                .studySessionCount(course.getStudySessions() == null ? 0 : course.getStudySessions().size())
                .createdAt(course.getCreatedAt())
                .updatedAt(course.getUpdatedAt())
                .build();
    }

    public CourseSummaryResponse toSummary(Course course) {
        return CourseSummaryResponse.builder()
                .id(course.getId())
                .title(course.getTitle())
                .color(course.getColor())
                .build();
    }

    public Course toEntity(CreateCourseRequest request) {
        return Course.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .color(request.getColor())
                .build();
    }

    public void updateEntity(Course course, UpdateCourseRequest request) {
        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        course.setColor(request.getColor());
    }
}
