package tasktracker.backend.task.internal.dto;


import tasktracker.backend.task.internal.domain.Status;

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
