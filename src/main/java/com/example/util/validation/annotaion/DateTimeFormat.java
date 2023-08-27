package com.example.util.validation.annotaion;

import com.example.util.validation.verification.CheckDateTimeFormat;
import net.sf.oval.configuration.annotation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD})
@Constraint(checkWith = CheckDateTimeFormat.class)
public @interface DateTimeFormat {
    String message() default "Please enter the correct format.";
}
