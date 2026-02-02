package tasktracker.backend.task.internal.exception;


import tasktracker.backend.task.internal.domain.Status;

public class InvalidTaskStatusTransitionException extends RuntimeException {
    public InvalidTaskStatusTransitionException(Status status, Status newStatus) {
        super(String.format("Invalid transition from %s to %s", status, newStatus));
    }
}
