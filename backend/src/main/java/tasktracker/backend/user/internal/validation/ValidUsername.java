package tasktracker.backend.user.internal.validation;


import jakarta.validation.Constraint;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.Payload;
import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = {})
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9_.-]{2,29}$")
public @interface ValidUsername {
    String message() default
            "Username must start with a letter and be 3–30 characters long. Allowed: letters, digits, '_', '-', '.'";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
