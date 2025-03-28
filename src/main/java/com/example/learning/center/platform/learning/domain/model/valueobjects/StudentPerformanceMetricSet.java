package com.example.learning.center.platform.learning.domain.model.valueobjects;

public record StudentPerformanceMetricSet(Integer totalCompletedCourses, Integer totalCompletedTutorials
) {
    public StudentPerformanceMetricSet() { this(0,0); }

    public StudentPerformanceMetricSet {
        if (totalCompletedCourses < 0)
            throw new IllegalArgumentException("Total completed courses cannot be negative");
        if (totalCompletedTutorials < 0)
            throw new IllegalArgumentException("Total completed tutorials cannot be negative");
    }

    public StudentPerformanceMetricSet incrementTotalCompletedCourses() {
        return new StudentPerformanceMetricSet(totalCompletedCourses + 1, totalCompletedTutorials);
    }

    public StudentPerformanceMetricSet incrementTotalCompletedTutorials() {
        return new StudentPerformanceMetricSet(totalCompletedCourses, totalCompletedTutorials + 1);
    }
}
