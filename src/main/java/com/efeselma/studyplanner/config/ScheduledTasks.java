package com.efeselma.studyplanner.config;

import com.efeselma.studyplanner.service.AssignmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
@Slf4j
public class ScheduledTasks {
    private final AssignmentService assignmentService;

    @Scheduled(fixedRate = 3600000)
    public void markOverdueAssignments() {
        int updated = assignmentService.markOverdueAssignments();
        if (updated > 0) {
            log.info("Marked {} assignments as overdue", updated);
        }
    }
}
