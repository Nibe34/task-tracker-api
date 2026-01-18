package topicmanager.backend.exception;


public class EmptyPatchException extends RuntimeException {
    public EmptyPatchException() {
        super("At least one field must be provided for update");
    }
}
