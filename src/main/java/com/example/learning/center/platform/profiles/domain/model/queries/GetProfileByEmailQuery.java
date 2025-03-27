package com.example.learning.center.platform.profiles.domain.model.queries;

import com.example.learning.center.platform.profiles.domain.model.valueobjects.EmailAddress;

public record GetProfileByEmailQuery(EmailAddress emailAddress) {
}
