package com.example.learning.center.platform.learning.domain.exceptions;

import com.example.learning.center.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;

public class StudentMetricsUpdateException extends RuntimeException {
    public StudentMetricsUpdateException(AcmeStudentRecordId studentRecordId, String exceptionMessage) {
        super("Error while updating metrics for student with recordID %s: %s".formatted(studentRecordId, exceptionMessage));
    }
}
