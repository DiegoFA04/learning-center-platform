package com.example.learning.center.platform.learning.interfaces.rest.transform;

import com.example.learning.center.platform.learning.domain.model.aggregates.Student;
import com.example.learning.center.platform.learning.interfaces.rest.resources.StudentResource;

public class StudentResourceFromEntityAssembler {
    public static StudentResource toResourceFromEntity(Student entity) {
        return new StudentResource(entity.getStudentRecordId(),
                entity.getProfileId(),
                entity.getTotalCompletedCourses(),
                entity.getTotalCompletedTutorials());
    }
}