package com.example.learning.center.platform.profiles.application.interal.commandservices;

import com.example.learning.center.platform.profiles.domain.model.aggregates.Profile;
import com.example.learning.center.platform.profiles.domain.model.commands.CreateProfileCommand;
import com.example.learning.center.platform.profiles.domain.model.valueobjects.EmailAddress;
import com.example.learning.center.platform.profiles.domain.services.ProfileCommandService;
import com.example.learning.center.platform.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileCommandServiceImpl implements ProfileCommandService {

    private final ProfileRepository profileRepository;

    public ProfileCommandServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }


    @Override
    public Optional<Profile> handle(CreateProfileCommand command) {
        var emailAddress = new EmailAddress(command.email());
        if (profileRepository.existsByEmailAddress(emailAddress)) {
            throw new IllegalArgumentException("Email address already in use");
        }
        var profile = new Profile(command);
        profileRepository.save(profile);
        return Optional.of(profile);
    }
}
