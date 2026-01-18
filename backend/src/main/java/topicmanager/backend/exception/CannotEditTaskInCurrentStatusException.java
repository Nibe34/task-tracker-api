package topicmanager.backend.exception;


public class CannotEditTaskInCurrentStatusException extends RuntimeException {
    public CannotEditTaskInCurrentStatusException(Long id) {
        super(String.format("Cannot change task with id:%s because the task is already completed", id));
    }
}
