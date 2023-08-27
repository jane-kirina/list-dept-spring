package com.example.util.validation.verification;

import com.example.util.constants.Parameters;
import com.example.util.validation.annotaion.ValidYearsWorking;
import net.sf.oval.ValidationCycle;
import net.sf.oval.configuration.annotation.AbstractAnnotationCheck;
import net.sf.oval.exception.OValException;

/**
 * Checks employee's years working for uniqueness in database.
 */
public class CheckValidYearsWorking extends AbstractAnnotationCheck<ValidYearsWorking> {
    @Override
    public boolean isSatisfied(Object validatedObject, Object valueToValidate, ValidationCycle cycle) throws OValException {
        if (valueToValidate == null) {
            return false;
        }

        String value = valueToValidate.toString();
        try {
            return Integer.parseInt(value) < Parameters.POSSIBLE_AGE;
        } catch (NumberFormatException exception) {
            return false;
        }
    }
}