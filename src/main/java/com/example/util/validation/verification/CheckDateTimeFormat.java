package com.example.util.validation.verification;

import com.example.util.validation.Validation;
import com.example.util.validation.annotaion.DateTimeFormat;
import net.sf.oval.ValidationCycle;
import net.sf.oval.configuration.annotation.AbstractAnnotationCheck;
import net.sf.oval.exception.OValException;

/**
 * Checks date for valid format.
 */
public class CheckDateTimeFormat extends AbstractAnnotationCheck<DateTimeFormat> {
    @Override
    public boolean isSatisfied(Object validatedObject, Object valueToValidate, ValidationCycle cycle) throws OValException {
        if (valueToValidate == null) {
            return false;
        }

        String value = valueToValidate.toString();

        return Validation.getValidDate(value) != null;
    }
}