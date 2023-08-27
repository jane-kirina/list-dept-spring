package com.example.util.validation.verification;

import com.example.util.constants.Parameters;
import com.example.util.validation.annotaion.ValidAge;
import net.sf.oval.ValidationCycle;
import net.sf.oval.configuration.annotation.AbstractAnnotationCheck;
import net.sf.oval.exception.OValException;

import java.time.LocalDate;
import java.time.Period;

/**
 * Checks employee's age.
 */
public class CheckValidAge extends AbstractAnnotationCheck<ValidAge> {
    @Override
    public boolean isSatisfied(Object validatedObject, Object valueToValidate, ValidationCycle cycle) throws OValException {
        if (valueToValidate == null) {
            return false;
        }

        String value = valueToValidate.toString();
        int difference = Period.between(LocalDate.parse(value), LocalDate.now()).getYears();

        return difference > Parameters.ADULTHOOD;
    }
}
