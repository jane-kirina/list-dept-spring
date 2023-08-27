package com.example.service.impl;

import com.example.dao.connection.SingletonConnection;
import com.example.dao.impl.EmployeeDAO;
import com.example.entity.Employee;
import com.example.service.interf.IEmployeeService;
import com.example.service.interf.IService;
import java.util.Set;

/**
 * Employee service for working with a database
 */
public class EmployeeService implements IEmployeeService {
    SingletonConnection connection = SingletonConnection.getInstance();

    /**
     * {@link IEmployeeService#findFromDept(Long)}
     */
    @Override
    public Set<Employee> findFromDept(Long id) {
        EmployeeDAO employeeDao = connection.getEmployeeDao();
        return employeeDao.findEmployees(id);
    }

    /**
     * {@link IEmployeeService#deleteEmployeesFromDept(Long)}
     */
    @Override
    public boolean deleteEmployeesFromDept(Long id) {
        EmployeeDAO employeeDao = connection.getEmployeeDao();
        return employeeDao.deleteFromDepartment(id);
    }

    /**
     * {@link IService#create(Object)}
     */
    @Override
    public boolean create(Employee entity) {
        EmployeeDAO employeeDao = connection.getEmployeeDao();
        long count = this.findMaxId() + 1;
        return employeeDao.saveEntity(entity, count);
    }

    /**
     * {@link IService#update(Object)}
     */
    @Override
    public boolean update(Employee entity) {
        EmployeeDAO employeeDao = connection.getEmployeeDao();
        return employeeDao.updateEntity(entity);
    }

    /**
     * {@link IService#delete(Long)}
     */
    @Override
    public boolean delete(Long id) {
        EmployeeDAO employeeDao = connection.getEmployeeDao();
        return employeeDao.deleteEntity(id);
    }

    /**
     * {@link IService#allData()}
     */
    @Override
    public Set<Employee> allData() {
        EmployeeDAO employeeDao = connection.getEmployeeDao();
        return employeeDao.findAll();
    }

    /**
     * {@link IService#findEntity(Long)}
     */
    @Override
    public Employee findEntity(Long id) {
        EmployeeDAO employeeDao = connection.getEmployeeDao();
        return employeeDao.findEntity(id);
    }

    /**
     * {@link IService#findMaxId()}
     */
    @Override
    public Long findMaxId() {
        EmployeeDAO employeeDao = connection.getEmployeeDao();
        return employeeDao.findMaxId();
    }

    /**
     * {@link IEmployeeService#isUniqueEmail(String)}
     */
    @Override
    public boolean isUniqueEmail(String expression) {
        EmployeeDAO employeeDao = connection.getEmployeeDao();
        int count = employeeDao.findNameCount(expression);
        return count == 0;
    }
}
