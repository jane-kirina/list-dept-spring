package com.example.util.validation.annotaion;

import com.example.util.validation.verification.CheckValidAge;
import net.sf.oval.configuration.annotation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD})
@Constraint(checkWith = CheckValidAge.class)
public @interface ValidAge {
    String message() default "You can add an employee who has reached the age of 18.";
}