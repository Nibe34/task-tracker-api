package topicmanager.backend.exception;


public class InvalidSortDirectionException extends RuntimeException {
    public InvalidSortDirectionException(String sortDir) {
        super(String.format("Invalid sort direction: %s. Allowed values are: ASC, DESC", sortDir));
    }
}
