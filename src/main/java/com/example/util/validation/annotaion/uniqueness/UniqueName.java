package com.example.util.validation.annotaion.uniqueness;

import com.example.util.validation.verification.uniqueness.UniqueCheckName;
import net.sf.oval.configuration.annotation.Constraint;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(checkWith = UniqueCheckName.class)
public @interface UniqueName {
    String message() default "Department name is not unique.";
}
