package com.efeselma.studyplanner.mapper;

import com.efeselma.studyplanner.dto.request.*;
import com.efeselma.studyplanner.dto.response.StudySessionResponse;
import com.efeselma.studyplanner.model.StudySession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudySessionMapper {
    private final CourseMapper courseMapper;

    public StudySessionResponse toResponse(StudySession session) {
        return StudySessionResponse.builder()
                .id(session.getId())
                .topic(session.getTopic())
                .studyDate(session.getStudyDate())
                .startTime(session.getStartTime())
                .endTime(session.getEndTime())
                .completed(session.isCompleted())
                .course(courseMapper.toSummary(session.getCourse()))
                .createdAt(session.getCreatedAt())
                .updatedAt(session.getUpdatedAt())
                .build();
    }

    public StudySession toEntity(CreateStudySessionRequest request) {
        return StudySession.builder()
                .topic(request.getTopic())
                .studyDate(request.getStudyDate())
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .completed(false)
                .build();
    }

    public void updateEntity(StudySession session, UpdateStudySessionRequest request) {
        session.setTopic(request.getTopic());
        session.setStudyDate(request.getStudyDate());
        session.setStartTime(request.getStartTime());
        session.setEndTime(request.getEndTime());
        session.setCompleted(request.isCompleted());
    }
}
