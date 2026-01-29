package tasktracker.backend.exception;


import java.util.Set;

public class InvalidSortFieldException extends RuntimeException {
    public InvalidSortFieldException(String invalidField, Set<String> allowedFields) {
        super(String.format("Invalid sort field '%s'. Allowed fields are: %s", invalidField, allowedFields));
    }
}
