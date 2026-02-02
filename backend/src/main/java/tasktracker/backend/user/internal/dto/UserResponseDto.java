package tasktracker.backend.user.internal.dto;


import java.time.Instant;

public record UserResponseDto(
        Long id,
        String username,
        String email,
        Instant createdAt
) {
}
