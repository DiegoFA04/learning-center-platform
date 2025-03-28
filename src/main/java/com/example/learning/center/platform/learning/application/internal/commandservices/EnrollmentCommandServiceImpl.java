package com.example.learning.center.platform.learning.application.internal.commandservices;

import com.example.learning.center.platform.learning.domain.exceptions.CourseNotFoundException;
import com.example.learning.center.platform.learning.domain.exceptions.EnrollmentNotFoundException;
import com.example.learning.center.platform.learning.domain.exceptions.EnrollmentRequestException;
import com.example.learning.center.platform.learning.domain.exceptions.StudentNotFoundException;
import com.example.learning.center.platform.learning.domain.model.aggregates.Enrollment;
import com.example.learning.center.platform.learning.domain.model.commands.*;
import com.example.learning.center.platform.learning.domain.services.EnrollmentCommandService;
import com.example.learning.center.platform.learning.infrastructure.persistence.jpa.repositories.CourseRepository;
import com.example.learning.center.platform.learning.infrastructure.persistence.jpa.repositories.EnrollmentRepository;
import com.example.learning.center.platform.learning.infrastructure.persistence.jpa.repositories.StudentRepository;
import org.springframework.stereotype.Service;

/**
 * Service implementation for handling enrollment commands
 */
@Service
public class EnrollmentCommandServiceImpl implements EnrollmentCommandService {
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final EnrollmentRepository enrollmentRepository;

    /**
     * Constructor
     * @param courseRepository Course repository
     * @param studentRepository Student repository
     * @param enrollmentRepository Enrollment repository
     */
    public EnrollmentCommandServiceImpl(CourseRepository courseRepository, StudentRepository studentRepository, EnrollmentRepository enrollmentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    // inherited javadoc
    @Override
    public Long handle(RequestEnrollmentCommand command) {
        if (!studentRepository.existsByAcmeStudentRecordId(command.studentRecordId()))
            throw new StudentNotFoundException(command.studentRecordId());
        var course = courseRepository.findById(command.courseId())
                .orElseThrow(() -> new CourseNotFoundException(command.courseId()));
        try {
            var enrollment = new Enrollment(command.studentRecordId(), course);
            enrollmentRepository.save(enrollment);
            return enrollment.getId();
        } catch (Exception e) {
            throw new EnrollmentRequestException(e.getMessage());
        }
    }

    // inherited javadoc
    @Override
    public Long handle(ConfirmEnrollmentCommand command) {
        enrollmentRepository.findById(command.enrollmentId()).map(enrollment -> {
            enrollment.confirm();
            enrollmentRepository.save(enrollment);
            return enrollment.getId();
        }).orElseThrow(() -> new EnrollmentNotFoundException(command.enrollmentId()));
        return null;
    }

    // inherited javadoc
    @Override
    public Long handle(RejectEnrollmentCommand command) {
        enrollmentRepository.findById(command.enrollmentId()).map(enrollment -> {
            enrollment.reject();
            enrollmentRepository.save(enrollment);
            return enrollment.getId();
        }).orElseThrow(() -> new EnrollmentNotFoundException(command.enrollmentId()));
        return null;
    }

    // inherited javadoc
    @Override
    public Long handle(CancelEnrollmentCommand command) {
        enrollmentRepository.findById(command.enrollmentId()).map(enrollment -> {
            enrollment.cancel();
            enrollmentRepository.save(enrollment);
            return enrollment.getId();
        }).orElseThrow(() -> new EnrollmentNotFoundException(command.enrollmentId()));
        return null;
    }

    // inherited javadoc
    @Override
    public Long handle(CompleteTutorialForEnrollmentCommand command) {
        enrollmentRepository.findById(command.enrollmentId()).map(enrollment -> {
            enrollment.completeTutorial(command.tutorialId());
            enrollmentRepository.save(enrollment);
            return enrollment.getId();
        }).orElseThrow(() -> new EnrollmentNotFoundException(command.enrollmentId()));
        return null;
    }
}
