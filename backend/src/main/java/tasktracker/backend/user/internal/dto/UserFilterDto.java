package tasktracker.backend.user.internal.dto;

public record UserFilterDto(
        String username,
        String email
) {
}
