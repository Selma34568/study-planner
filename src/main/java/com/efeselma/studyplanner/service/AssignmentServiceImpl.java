package com.efeselma.studyplanner.service;

import com.efeselma.studyplanner.dto.request.*;
import com.efeselma.studyplanner.dto.response.AssignmentResponse;
import com.efeselma.studyplanner.exception.*;
import com.efeselma.studyplanner.mapper.AssignmentMapper;
import com.efeselma.studyplanner.model.*;
import com.efeselma.studyplanner.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AssignmentServiceImpl implements AssignmentService {
    private final AssignmentRepository assignmentRepository;
    private final CourseRepository courseRepository;
    private final AssignmentMapper assignmentMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<AssignmentResponse> getAssignments(String username, String search, AssignmentStatus status, int page, int size, String sortBy, String direction) {
        Pageable pageable = createPageable(page, size, sortBy, direction);
        Page<Assignment> result;
        if (status != null) {
            result = assignmentRepository.findByCourseOwnerUsernameAndDeletedFalseAndStatus(username, status, pageable);
        } else if (search != null && !search.isBlank()) {
            result = assignmentRepository.findByCourseOwnerUsernameAndDeletedFalseAndTitleContainingIgnoreCase(username, search, pageable);
        } else {
            result = assignmentRepository.findByCourseOwnerUsernameAndDeletedFalse(username, pageable);
        }
        return result.map(assignmentMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public AssignmentResponse getAssignment(Long id, String username) {
        return assignmentMapper.toResponse(findOwnedAssignment(id, username));
    }

    @Override
    public AssignmentResponse createAssignment(CreateAssignmentRequest request, String username) {
        Course course = courseRepository.findByIdAndOwnerUsernameAndDeletedFalse(request.getCourseId(), username)
                .orElseThrow(() -> new ResourceNotFoundException("Course", request.getCourseId()));
        Assignment assignment = assignmentMapper.toEntity(request);
        assignment.setCourse(course);
        return assignmentMapper.toResponse(assignmentRepository.save(assignment));
    }

    @Override
    public AssignmentResponse updateAssignment(Long id, UpdateAssignmentRequest request, String username) {
        Assignment assignment = findOwnedAssignment(id, username);
        if (assignment.getStatus() == AssignmentStatus.COMPLETED && request.getStatus() != AssignmentStatus.COMPLETED) {
            throw new InvalidOperationException("Completed assignments cannot be reopened");
        }
        assignmentMapper.updateEntity(assignment, request);
        return assignmentMapper.toResponse(assignmentRepository.save(assignment));
    }

    @Override
    public AssignmentResponse markCompleted(Long id, String username) {
        Assignment assignment = findOwnedAssignment(id, username);
        assignment.setStatus(AssignmentStatus.COMPLETED);
        return assignmentMapper.toResponse(assignmentRepository.save(assignment));
    }

    @Override
    public void deleteAssignment(Long id, String username) {
        Assignment assignment = findOwnedAssignment(id, username);
        assignment.setDeleted(true);
        assignmentRepository.save(assignment);
    }

    @Override
    public int markOverdueAssignments() {
        List<Assignment> overdue = assignmentRepository.findByDeletedFalseAndStatusNotAndDeadlineBefore(
                AssignmentStatus.COMPLETED, LocalDateTime.now());
        overdue.forEach(a -> a.setStatus(AssignmentStatus.OVERDUE));
        assignmentRepository.saveAll(overdue);
        return overdue.size();
    }

    private Assignment findOwnedAssignment(Long id, String username) {
        return assignmentRepository.findByIdAndCourseOwnerUsernameAndDeletedFalse(id, username)
                .orElseThrow(() -> new ResourceNotFoundException("Assignment", id));
    }

    private Pageable createPageable(int page, int size, String sortBy, String direction) {
        Sort.Direction sortDirection = direction.equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
        return PageRequest.of(page, size, Sort.by(sortDirection, sortBy));
    }
}
