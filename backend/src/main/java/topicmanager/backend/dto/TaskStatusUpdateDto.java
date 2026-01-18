package topicmanager.backend.dto;


import jakarta.validation.constraints.NotNull;
import topicmanager.backend.model.Status;

public record TaskStatusUpdateDto(
        @NotNull(message = "Status is required")
        Status status
) {
}
