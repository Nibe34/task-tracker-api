package tasktracker.backend.model;


import lombok.Getter;

import java.util.Collections;
import java.util.Set;

@Getter
public enum Status {
    DONE(Collections.emptySet(), false),
    IN_PROGRESS(Set.of(Status.DONE), true),
    TODO(Set.of(Status.IN_PROGRESS, Status.DONE), true);

    private final Set<Status> allowedNextStatuses;
    private final boolean editable;


    Status(Set<Status> allowedNextStatuses, boolean editable) {
        this.allowedNextStatuses = allowedNextStatuses;
        this.editable = editable;
    }

    public boolean isValidTransition(Status newStatus) {
        return allowedNextStatuses.contains(newStatus);
    }
}
