package tasktracker.backend.task.internal.dto;


import tasktracker.backend.task.internal.model.Status;

public record TaskFilterDto(
        String title,
        Status status
) {
}
