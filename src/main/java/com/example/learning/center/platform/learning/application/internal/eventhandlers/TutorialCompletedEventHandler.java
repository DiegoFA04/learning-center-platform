package com.example.learning.center.platform.learning.application.internal.eventhandlers;

import com.example.learning.center.platform.learning.domain.model.commands.UpdateStudentMetricsOnTutorialCompletedCommand;
import com.example.learning.center.platform.learning.domain.model.events.TutorialCompletedEvent;
import com.example.learning.center.platform.learning.domain.model.queries.GetEnrollmentByIdQuery;
import com.example.learning.center.platform.learning.domain.services.EnrollmentQueryService;
import com.example.learning.center.platform.learning.domain.services.StudentCommandService;
import org.springframework.context.event.EventListener;

/**
 * TutorialCompletedEventHandler class.
 */
public class TutorialCompletedEventHandler {
    private final StudentCommandService studentCommandService;
    private final EnrollmentQueryService enrollmentQueryService;

    /**
     * Constructor.
     *
     * @param studentCommandService The {@link StudentCommandService} instance.
     * @param enrollmentQueryService The {@link EnrollmentQueryService} instance.
     */
    public TutorialCompletedEventHandler(StudentCommandService studentCommandService, EnrollmentQueryService enrollmentQueryService) {
        this.studentCommandService = studentCommandService;
        this.enrollmentQueryService = enrollmentQueryService;
    }

    /**
     * Handles the {@link TutorialCompletedEvent} event.
     * <p>
     *     This method updates the student metrics when a tutorial is completed.
     *     It gets the enrollment by id and updates the student metrics.
     * </p>
     *
     * @param event The {@link TutorialCompletedEvent} event.
     */
    @EventListener(TutorialCompletedEvent.class)
    public void on(TutorialCompletedEvent event) {
        var getEnrollmentByIdQuery = new GetEnrollmentByIdQuery(event.getEnrollmentId());
        var enrollment = enrollmentQueryService.handle(getEnrollmentByIdQuery);
        if (enrollment.isPresent()) {
            var studentEnrollment = enrollment.get();
            var updateStudentMetricsOnTutorialCompletedCommand = new UpdateStudentMetricsOnTutorialCompletedCommand(studentEnrollment.getAcmeStudentRecordId());
            studentCommandService.handle(updateStudentMetricsOnTutorialCompletedCommand);
        }
    }
}
