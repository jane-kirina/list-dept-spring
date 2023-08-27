package com.example.util.validation.annotaion;

import com.example.util.validation.verification.CheckExistDept;
import net.sf.oval.configuration.annotation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD})
@Constraint(checkWith = CheckExistDept.class)
public @interface ExistDept {
    String message() default "Department with this id doesn't exist.";
}
