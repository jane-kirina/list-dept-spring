package com.example.service.impl.hibernate;

import com.example.dao.interf.hibernate.IEmployeeHibernateDao;
import com.example.entity.Employee;
import com.example.exception.database.AddInDatabaseException;
import com.example.exception.database.DeleteInDatabaseException;
import com.example.exception.database.UpdateDataInDatabaseException;
import com.example.service.interf.IEmployeeService;
import com.example.service.interf.IService;
import com.example.service.interf.hibernate.IEmployeeHibernateService;
import com.example.util.constants.LoggerMessage;
import org.apache.log4j.Logger;

import java.text.MessageFormat;
import java.util.Set;

/**
 * Employee service for working with a database through hibernate
 */
public class EmployeeHibernateService implements IEmployeeHibernateService {
    private static final Logger LOGGER = Logger.getLogger(EmployeeHibernateService.class);
    private final IEmployeeHibernateDao employeeHibernateDao;

    public EmployeeHibernateService(IEmployeeHibernateDao employeeHibernateDao) {
        this.employeeHibernateDao = employeeHibernateDao;
    }

    /**
     * {@link IEmployeeService#findFromDept(Long)}
     */
    @Override
    public Set<Employee> findFromDept(Long id) {
        return employeeHibernateDao.findEmployees(id);
    }

    /**
     * {@link IEmployeeService#deleteEmployeesFromDept(Long)}
     */
    @Override
    public boolean deleteEmployeesFromDept(Long id) {
        try {
            employeeHibernateDao.deleteFromDepartment(id);
        } catch (DeleteInDatabaseException exception) {
            LOGGER.error(MessageFormat.format(LoggerMessage.DELETE_FAILED_BY_ID, exception.getMessage(), id), exception);
        }
        return true;
    }

    /**
     * {@link IService#create(Object)}
     */
    @Override
    public boolean create(Employee entity) {
        long count = this.findMaxId() + 1;
        try {
            employeeHibernateDao.saveEntity(entity, count);
        } catch (AddInDatabaseException exception) {
            LOGGER.error("Something went wrong. Added failed: " + exception.getMessage(), exception);
        }
        return true;
    }

    /**
     * {@link IService#update(Object)}
     */
    @Override
    public boolean update(Employee entity) {
        try {
            employeeHibernateDao.updateEntity(entity);
        } catch (UpdateDataInDatabaseException exception) {
            LOGGER.error("Something went wrong. Updated failed: " + exception.getMessage(), exception);
        }
        return true;
    }

    /**
     * {@link IService#delete(Long)}
     */
    @Override
    public boolean delete(Long id) {
        try {
            employeeHibernateDao.deleteEntity(id);
        } catch (DeleteInDatabaseException exception) {
            LOGGER.error(MessageFormat.format(LoggerMessage.DELETE_FAILED_BY_ID, exception.getMessage(), id), exception);
        }
        return true;
    }

    /**
     * {@link IService#allData()}
     */
    @Override
    public Set<Employee> allData() {
        return employeeHibernateDao.findAll();
    }

    /**
     * {@link IService#findEntity(Long)}
     */
    @Override
    public Employee findEntity(Long id) {
        return employeeHibernateDao.findEntity(id);
    }

    /**
     * {@link IService#findMaxId()}
     */
    @Override
    public Long findMaxId() {
        return employeeHibernateDao.findMaxId();
    }

    /**
     * {@link IEmployeeService#isUniqueEmail(String)}
     */
    @Override
    public boolean isUniqueEmail(String expression) {
        int count = employeeHibernateDao.findNameCount(expression);
        return count == 0;
    }

    /**
     * {@link IEmployeeHibernateService#isUniqueEmail(String, Long)}
     */
    @Override
    public boolean isUniqueEmail(String expression, Long id) {
        if (id == null) {
            return isUniqueEmail(expression);
        }
        int count = employeeHibernateDao.findCountIdByEmail(expression, id);
        return count == 0;
    }
}
