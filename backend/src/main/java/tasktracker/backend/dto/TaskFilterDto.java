package tasktracker.backend.dto;


import tasktracker.backend.model.Status;

public record TaskFilterDto(
        String title,
        Status status
) {
}
