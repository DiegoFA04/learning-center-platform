package com.example.learning.center.platform.learning.application.internal.queryservices;

import com.example.learning.center.platform.learning.domain.model.aggregates.Student;
import com.example.learning.center.platform.learning.domain.model.queries.GetStudentByAcmeStudentRecordIdQuery;
import com.example.learning.center.platform.learning.domain.model.queries.GetStudentByProfileIdQuery;
import com.example.learning.center.platform.learning.domain.services.StudentQueryService;
import com.example.learning.center.platform.learning.infrastructure.persistence.jpa.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementation of the StudentQueryService interface.
 */
@Service
public class StudentQueryServiceImpl implements StudentQueryService {
    private final StudentRepository studentRepository;

    /**
     * Constructor.
     *
     * @param studentRepository the student repository
     */
    public StudentQueryServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // inherited javadoc
    @Override
    public Optional<Student> handle(GetStudentByProfileIdQuery query) {
        return studentRepository.findByProfileId(query.profileId());
    }

    // inherited javadoc
    @Override
    public Optional<Student> handle(GetStudentByAcmeStudentRecordIdQuery query) {
        return studentRepository.findByAcmeStudentRecordId(query.studentRecordId());
    }
}
