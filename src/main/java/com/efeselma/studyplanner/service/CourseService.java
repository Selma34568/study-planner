package com.efeselma.studyplanner.service;

import com.efeselma.studyplanner.dto.request.*;
import com.efeselma.studyplanner.dto.response.CourseResponse;
import org.springframework.data.domain.Page;

public interface CourseService {
    Page<CourseResponse> getCourses(String username, String search, int page, int size, String sortBy, String direction);
    CourseResponse getCourse(Long id, String username);
    CourseResponse createCourse(CreateCourseRequest request, String username);
    CourseResponse updateCourse(Long id, UpdateCourseRequest request, String username);
    void deleteCourse(Long id, String username);
    boolean isCourseOwner(Long id, String username);
}
