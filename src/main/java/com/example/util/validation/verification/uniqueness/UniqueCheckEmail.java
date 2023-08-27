package com.example.util.validation.verification.uniqueness;

import com.example.model.EmployeePojo;
import com.example.service.interf.hibernate.IEmployeeHibernateService;
import com.example.util.validation.annotaion.uniqueness.UniqueEmail;
import net.sf.oval.ValidationCycle;
import net.sf.oval.configuration.annotation.AbstractAnnotationCheck;
import net.sf.oval.exception.OValException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Checks employee's mail for uniqueness in database.
 */
public class UniqueCheckEmail extends AbstractAnnotationCheck<UniqueEmail> {
    @Autowired
    private IEmployeeHibernateService employeeHibernateService;

    @Override
    public boolean isSatisfied(Object validatedObject, Object valueToValidate, ValidationCycle cycle) throws OValException {
        if (valueToValidate == null) {
            return false;
        }
        EmployeePojo employeePojo = (EmployeePojo) valueToValidate;

        if (employeePojo.getEmail() == null) {
            return false;
        }

        String email = employeePojo.getEmail();
        Long id = employeePojo.getId();

        return getEmployeeHibernateService().isUniqueEmail(email, id);
    }

    protected final IEmployeeHibernateService getEmployeeHibernateService() {
        return employeeHibernateService;
    }
}
