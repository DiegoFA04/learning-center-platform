package com.example.learning.center.platform.learning.interfaces.rest.transform;

import com.example.learning.center.platform.learning.domain.model.aggregates.Enrollment;
import com.example.learning.center.platform.learning.interfaces.rest.resources.EnrollmentResource;

/**
 * Assembler to convert an Enrollment entity to an EnrollmentResource.
 */
public class EnrollmentResourceFromEntityAssembler {
    /**
     * Converts an Enrollment entity to an EnrollmentResource.
     *
     * @param entity The {@link Enrollment} entity to convert.
     * @return The {@link EnrollmentResource} resource
     */
    public static EnrollmentResource toResourceFromEntity(Enrollment entity) {
        return new EnrollmentResource(entity.getId(), entity.getAcmeStudentRecordId().studentRecordId(), entity.getCourse().getId(), entity.getStatus());
    }
}
