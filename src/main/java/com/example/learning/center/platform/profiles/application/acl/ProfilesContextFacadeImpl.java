package com.example.learning.center.platform.profiles.application.acl;

import com.example.learning.center.platform.profiles.domain.model.commands.CreateProfileCommand;
import com.example.learning.center.platform.profiles.domain.model.queries.GetProfileByEmailQuery;
import com.example.learning.center.platform.profiles.domain.model.valueobjects.EmailAddress;
import com.example.learning.center.platform.profiles.domain.services.ProfileCommandService;
import com.example.learning.center.platform.profiles.domain.services.ProfileQueryService;
import com.example.learning.center.platform.profiles.interfaces.acl.ProfilesContextFacade;
import org.springframework.stereotype.Service;
/**
 * Profiles Context Facade Implementation
 */
@Service
public class ProfilesContextFacadeImpl implements ProfilesContextFacade {
    private final ProfileCommandService profileCommandService;
    private final ProfileQueryService profileQueryService;

    /**
     * Constructor
     *
     * @param profileCommandService The {@link ProfileCommandService} instance
     * @param profileQueryService The {@link ProfileQueryService} instance
     */
    public ProfilesContextFacadeImpl(ProfileCommandService profileCommandService, ProfileQueryService profileQueryService) {
        this.profileCommandService = profileCommandService;
        this.profileQueryService = profileQueryService;
    }

    // inherited javadoc
    @Override
    public Long createProfile(String firstName, String lastName, String email, String street, String number, String city, String postalCode, String country) {
        var createProfileCommand = new CreateProfileCommand(
                firstName,
                lastName,
                email,
                street,
                number,
                city,
                postalCode,
                country);
        var profile = profileCommandService.handle(createProfileCommand);
        return profile.isEmpty() ? Long.valueOf(0L) : profile.get().getId();
    }

    // inherited javadoc
    @Override
    public Long fetchProfileIdByEmail(String email) {
        var getProfileByEmailQuery = new GetProfileByEmailQuery(new EmailAddress(email));
        var profile = profileQueryService.handle(getProfileByEmailQuery);
        return profile.isEmpty() ? Long.valueOf(0L) : profile.get().getId();
    }
}
