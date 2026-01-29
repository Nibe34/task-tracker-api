package tasktracker.backend.dto;


import jakarta.validation.constraints.NotNull;
import tasktracker.backend.model.Status;

public record TaskStatusUpdateDto(
        @NotNull(message = "Status is required")
        Status status
) {
}
