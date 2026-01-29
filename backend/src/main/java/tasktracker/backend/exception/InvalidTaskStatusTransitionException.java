package tasktracker.backend.exception;


import tasktracker.backend.model.Status;

public class InvalidTaskStatusTransitionException extends RuntimeException {
    public InvalidTaskStatusTransitionException(Status status, Status newStatus) {
        super(String.format("Invalid transition from %s to %s", status, newStatus));
    }
}
