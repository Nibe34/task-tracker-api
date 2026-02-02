package tasktracker.backend.task.internal.dto;


import tasktracker.backend.task.internal.domain.Status;

public record TaskFilterDto(
        String title,
        Status status
) {
}
