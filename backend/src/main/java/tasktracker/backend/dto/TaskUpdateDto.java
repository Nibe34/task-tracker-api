package tasktracker.backend.dto;


import jakarta.validation.constraints.Size;

public record TaskUpdateDto(
    @Size(min = 3, max = 40, message = "Title must be between 3 and 40 characters")
    String title,

    @Size(max = 500, message = "Description too long")
    String description
) {
}