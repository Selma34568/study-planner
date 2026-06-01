package com.efeselma.studyplanner.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FutureDateTimeValidator.class)
@Documented
public @interface FutureDateTime {
    String message() default "Date and time must be in the future";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
