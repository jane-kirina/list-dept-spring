package com.example.service.interf.hibernate;

import com.example.service.interf.IDepartmentService;

/**
 * Interface service for DepartmentHibernateDAO.
 */
public interface IDepartmentHibernateService extends IDepartmentService {
    /**
     * Checks if phone is unique
     * @param phone to check
     * @param id of department
     * @return true, if unique
     */
    boolean isUniquePhone(String phone, Long id);

    /**
     * Checks if name is unique
     * @param name to check
     * @param id of department
     * @return true, if unique
     */
    boolean isUniqueName(String name, Long id);
}
