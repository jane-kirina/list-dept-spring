package com.example.util.validation.annotaion.uniqueness;

import com.example.util.validation.verification.uniqueness.UniqueCheckPhone;
import net.sf.oval.configuration.annotation.Constraint;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(checkWith = UniqueCheckPhone.class)
public @interface UniquePhone {
    String message() default "Phone number is not unique.";
}
