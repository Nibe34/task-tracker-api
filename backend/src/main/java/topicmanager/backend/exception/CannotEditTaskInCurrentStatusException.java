package topicmanager.backend.exception;


public class CannotEditTaskInCurrentStatusException extends RuntimeException {
    public CannotEditTaskInCurrentStatusException(String field, Long id) {
        super(String.format("Cannot change %s in task with id %s because the task is already completed", field, id));
    }
}
