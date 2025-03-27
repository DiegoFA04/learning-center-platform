package com.example.learning.center.platform.profiles.interfaces.rest.resources;

public record CreateProfileResource(
        String firstName,
        String lastName,
        String email,
        String street,
        String number,
        String city,
        String postalCode,
        String country
) {
    /**
     * Validates the resource.
     *
     * @throws IllegalArgumentException if the resource is invalid.
     */
    public CreateProfileResource {
        if (firstName == null || firstName.isBlank()) throw new IllegalArgumentException("firstName cannot be empty");
        if (lastName == null || lastName.isBlank()) throw new IllegalArgumentException("lastName cannot be empty");
        if (email == null || email.isBlank()) throw new IllegalArgumentException("email cannot be empty");
        if (street == null || street.isBlank()) throw new IllegalArgumentException("street cannot be empty");
        if (city == null || city.isBlank()) throw new IllegalArgumentException("city cannot be empty");
    }
}
