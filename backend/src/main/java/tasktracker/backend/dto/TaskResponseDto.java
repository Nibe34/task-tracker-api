package tasktracker.backend.dto;


import tasktracker.backend.model.Status;

import java.time.Instant;

public record TaskResponseDto(
        Long id,
        String title,
        String description,
        Status status,
        Instant createdAt,
        Instant completedAt
) {
}
