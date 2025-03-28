package com.example.learning.center.platform.learning.interfaces.rest.resources;

public record StudentResource(
        String acmeStudentRecordId,
        Long profileId,
        Integer totalCompletedCourses,
        Integer totalCompletedTutorials) {
}
