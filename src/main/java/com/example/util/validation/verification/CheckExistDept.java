package com.example.util.validation.verification;

import com.example.model.DepartmentPojo;
import com.example.service.interf.hibernate.IDepartmentHibernateService;
import com.example.util.validation.annotaion.ExistDept;
import net.sf.oval.ValidationCycle;
import net.sf.oval.configuration.annotation.AbstractAnnotationCheck;
import net.sf.oval.exception.OValException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Checks if department exists in database.
 */
public class CheckExistDept extends AbstractAnnotationCheck<ExistDept> {
    @Autowired
    private IDepartmentHibernateService departmentHibernateService;

    @Override
    public boolean isSatisfied(Object validatedObject, Object valueToValidate, ValidationCycle cycle) throws OValException {
        if (valueToValidate == null) {
            return false;
        }
        DepartmentPojo departmentPojo = (DepartmentPojo) valueToValidate;

        if (departmentPojo.getId() == null) {
            return false;
        }

        Long id = departmentPojo.getId();

        return getDepartmentHibernateService().findEntity(id) != null;
    }

    protected final IDepartmentHibernateService getDepartmentHibernateService() {
        return departmentHibernateService;
    }
}
