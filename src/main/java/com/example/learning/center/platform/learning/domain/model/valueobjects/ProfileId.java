package com.example.learning.center.platform.learning.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record ProfileId(Long profileId) {
    public ProfileId {
        if (profileId < 0) {
            throw new IllegalArgumentException("ProfileId must be greater than 0");
        }
    }

    public ProfileId() { this(0L); }
}
