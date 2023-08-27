package com.example.util.validation.annotaion.uniqueness;

import com.example.util.validation.verification.uniqueness.UniqueCheckEmail;
import net.sf.oval.configuration.annotation.Constraint;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(checkWith = UniqueCheckEmail.class)
public @interface UniqueEmail {
    String message() default "Employee with this mail exists.";
}
