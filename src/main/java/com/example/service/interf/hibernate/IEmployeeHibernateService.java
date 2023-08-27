package com.example.service.interf.hibernate;

import com.example.service.interf.IEmployeeService;

/**
 * Interface service for EmployeeHibernateDAO.
 */
public interface IEmployeeHibernateService extends IEmployeeService {
    /**
     * Checks if email is unique
     * @param email to check
     * @param id of employee
     * @return true, if unique
     */
    boolean isUniqueEmail(String email, Long id);
}
