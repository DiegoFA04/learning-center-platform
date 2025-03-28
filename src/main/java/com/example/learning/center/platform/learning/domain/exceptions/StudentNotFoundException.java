package com.example.learning.center.platform.learning.domain.exceptions;

import com.example.learning.center.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(AcmeStudentRecordId studentRecordId) {
        super("Student with Acme student record ID %s not found".formatted(studentRecordId.studentRecordId()));
    }
}
