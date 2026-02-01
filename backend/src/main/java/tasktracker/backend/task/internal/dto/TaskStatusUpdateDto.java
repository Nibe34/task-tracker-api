package tasktracker.backend.task.internal.dto;


import jakarta.validation.constraints.NotNull;
import tasktracker.backend.task.internal.model.Status;

public record TaskStatusUpdateDto(
        @NotNull(message = "Status is required")
        Status status
) {
}
