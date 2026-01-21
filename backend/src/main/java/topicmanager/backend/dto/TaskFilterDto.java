package topicmanager.backend.dto;


import topicmanager.backend.model.Status;

public record TaskFilterDto(
        String title,
        Status status
) {
}
