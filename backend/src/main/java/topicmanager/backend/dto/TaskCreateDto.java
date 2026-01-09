package topicmanager.backend.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TaskCreateDto(
    @NotBlank(message = "Title cannot be empty")
    @Size(min = 3, max = 40, message = "Title must be between 3 and 100 characters")
    String title,

    @Size(max = 500, message = "Description too long")
    String description
) {
}
