package com.example.service.interf;

import com.example.entity.Employee;

import java.util.Set;

/**
 * Interface service for EmployeeDAO.
 */
public interface IEmployeeService extends IService<Employee> {
    /**
     * Finds list with employee.
     * @param id of department
     * @return list with employee from department
     */
    Set<Employee> findFromDept(Long id);

    /**
     * Delete all employees from department.
     * @param id of department
     * @return true if delete all employees
     */
    boolean deleteEmployeesFromDept(Long id);

    /**
     * Checks if email is unique
     * @param email enter
     * @return true, if unique
     */
    boolean isUniqueEmail(String email);
}
