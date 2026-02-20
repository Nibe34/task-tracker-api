package tasktracker.backend.user.internal.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import tasktracker.backend.user.internal.validation.ValidUsername;

public record UserUpdateDto(
        @NotBlank(message = "Username cannot be empty")
        @ValidUsername
        String username,

        @NotBlank(message = "Email cannot be empty")
        @Email(message = "Email must be in format user@domain")
        String email
) {
}