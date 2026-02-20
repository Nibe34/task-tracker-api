package tasktracker.backend.user.internal.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super(String.format("User not found with id: %s", id));
    }
}
