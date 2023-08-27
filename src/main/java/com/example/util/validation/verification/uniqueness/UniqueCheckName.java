package com.example.util.validation.verification.uniqueness;

import com.example.model.DepartmentPojo;
import com.example.service.interf.hibernate.IDepartmentHibernateService;
import com.example.util.validation.annotaion.uniqueness.UniqueName;
import net.sf.oval.ValidationCycle;
import net.sf.oval.configuration.annotation.AbstractAnnotationCheck;
import net.sf.oval.exception.OValException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Checks department's name for uniqueness in database.
 */
public class UniqueCheckName extends AbstractAnnotationCheck<UniqueName> {
    @Autowired
    private IDepartmentHibernateService departmentHibernateService;

    @Override
    public boolean isSatisfied(Object validatedObject, Object valueToValidate, ValidationCycle cycle) throws OValException {
        if (valueToValidate == null) {
            return false;
        }
        DepartmentPojo departmentPojo = (DepartmentPojo) valueToValidate;

        if (departmentPojo.getName() == null) {
            return false;
        }

        String name = departmentPojo.getName();
        Long id = departmentPojo.getId();

        return getDepartmentHibernateService().isUniqueName(name, id);
    }

    protected final IDepartmentHibernateService getDepartmentHibernateService() {
        return departmentHibernateService;
    }
}
