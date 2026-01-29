package tasktracker.backend.exception;


public class CannotEditTaskInCurrentStatusException extends RuntimeException {
    public CannotEditTaskInCurrentStatusException(Long id) {
        super(String.format("Cannot change task with id:%s because it is in a non-editable status", id));
    }
}
