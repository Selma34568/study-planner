package com.efeselma.studyplanner.service;

import com.efeselma.studyplanner.dto.request.*;
import com.efeselma.studyplanner.dto.response.CourseResponse;
import com.efeselma.studyplanner.exception.ResourceNotFoundException;
import com.efeselma.studyplanner.mapper.CourseMapper;
import com.efeselma.studyplanner.model.*;
import com.efeselma.studyplanner.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final CourseMapper courseMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<CourseResponse> getCourses(String username, String search, int page, int size, String sortBy, String direction) {
        Pageable pageable = createPageable(page, size, sortBy, direction);
        Page<Course> result = (search == null || search.isBlank())
                ? courseRepository.findByOwnerUsernameAndDeletedFalse(username, pageable)
                : courseRepository.findByOwnerUsernameAndDeletedFalseAndTitleContainingIgnoreCase(username, search, pageable);
        return result.map(courseMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public CourseResponse getCourse(Long id, String username) {
        return courseMapper.toResponse(findOwnedCourse(id, username));
    }

    @Override
    public CourseResponse createCourse(CreateCourseRequest request, String username) {
        User owner = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Course course = courseMapper.toEntity(request);
        course.setOwner(owner);
        return courseMapper.toResponse(courseRepository.save(course));
    }

    @Override
    public CourseResponse updateCourse(Long id, UpdateCourseRequest request, String username) {
        Course course = findOwnedCourse(id, username);
        courseMapper.updateEntity(course, request);
        return courseMapper.toResponse(courseRepository.save(course));
    }

    @Override
    public void deleteCourse(Long id, String username) {
        Course course = findOwnedCourse(id, username);
        course.setDeleted(true);
        courseRepository.save(course);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isCourseOwner(Long id, String username) {
        return courseRepository.findByIdAndOwnerUsernameAndDeletedFalse(id, username).isPresent();
    }

    private Course findOwnedCourse(Long id, String username) {
        return courseRepository.findByIdAndOwnerUsernameAndDeletedFalse(id, username)
                .orElseThrow(() -> new ResourceNotFoundException("Course", id));
    }

    private Pageable createPageable(int page, int size, String sortBy, String direction) {
        Sort.Direction sortDirection = direction.equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
        return PageRequest.of(page, size, Sort.by(sortDirection, sortBy));
    }
}
