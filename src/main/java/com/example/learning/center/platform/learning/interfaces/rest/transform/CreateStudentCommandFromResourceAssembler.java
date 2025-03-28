package com.example.learning.center.platform.learning.interfaces.rest.transform;

import com.example.learning.center.platform.learning.domain.model.commands.CreateStudentCommand;
import com.example.learning.center.platform.learning.interfaces.rest.resources.CreateStudentResource;

/**
 * Assembler to convert a CreateStudentResource to a CreateStudentCommand.
 */
public class CreateStudentCommandFromResourceAssembler {
    /**
     * Converts a CreateStudentResource to a CreateStudentCommand.
     *
     * @param resource The {@link CreateStudentResource} resource to convert.
     * @return The {@link CreateStudentCommand} command
     */
    public static CreateStudentCommand toCommandFromResource(CreateStudentResource resource) {
        return new CreateStudentCommand(resource.firstName(), resource.lastName(), resource.email(),
                resource.street(), resource.number(), resource.city(), resource.postalCode(), resource.country());
    }
}
