package com.example.service.impl.hibernate;

import com.example.dao.interf.hibernate.IDepartmentHibernateDao;
import com.example.entity.Department;
import com.example.exception.database.AddInDatabaseException;
import com.example.exception.database.DeleteInDatabaseException;
import com.example.exception.database.UpdateDataInDatabaseException;
import com.example.service.interf.IDepartmentService;
import com.example.service.interf.IService;
import com.example.service.interf.hibernate.IDepartmentHibernateService;
import com.example.util.constants.LoggerMessage;
import org.apache.log4j.Logger;

import java.text.MessageFormat;
import java.util.Set;

/**
 * Department service for working with a database through hibernate.
 */
public class DepartmentHibernateService implements IDepartmentHibernateService {
    private static final Logger LOGGER = Logger.getLogger(DepartmentHibernateService.class);
    private final IDepartmentHibernateDao departmentHibernateDao;

    public DepartmentHibernateService(IDepartmentHibernateDao departmentHibernateDao) {
        this.departmentHibernateDao = departmentHibernateDao;
    }

    /**
     * {@link IService#create(Object)}
     */
    @Override
    public boolean create(Department entity) {
        long count = this.findMaxId() + 1;
        try {
            departmentHibernateDao.saveEntity(entity, count);
        } catch (AddInDatabaseException exception) {
            LOGGER.error("Something went wrong. Added failed: " + exception.getMessage(), exception);
        }
        return true;
    }

    /**
     * {@link IService#update(Object)}
     */
    @Override
    public boolean update(Department entity) {
        try {
            departmentHibernateDao.updateEntity(entity);
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
            departmentHibernateDao.deleteEntity(id);
        } catch (DeleteInDatabaseException exception) {
            LOGGER.error(MessageFormat.format(LoggerMessage.DELETE_FAILED_BY_ID, exception.getMessage(), id), exception);
        }
        return true;
    }

    /**
     * {@link IService#allData()}
     */
    @Override
    public Set<Department> allData() {
        return departmentHibernateDao.findAll();
    }

    /**
     * {@link IService#findEntity(Long)}
     */
    @Override
    public Department findEntity(Long id) {
        return departmentHibernateDao.findEntity(id);
    }

    /**
     * {@link IService#findMaxId()}
     */
    @Override
    public Long findMaxId() {
        return departmentHibernateDao.findMaxId();
    }

    /**
     * {@link IDepartmentService#isUniqueName(String)}-
     */
    @Override
    public boolean isUniqueName(String name) {
        int count = departmentHibernateDao.findNameCount(name);
        return count == 0;
    }

    /**
     * {@link IDepartmentHibernateService#isUniqueName(String, Long)}
     */
    @Override
    public boolean isUniqueName(String name, Long id) {
        if (id == null) {
            return isUniqueName(name);
        }
        int count = departmentHibernateDao.findCountByIdAndName(name, id);
        return count == 0;
    }

    /**
     * {@link IDepartmentService#isUniquePhone(String)}
     */
    @Override
    public boolean isUniquePhone(String phone) {
        int count = departmentHibernateDao.findPhoneCount(phone);
        return count == 0;
    }

    /**
     * {@link IDepartmentHibernateService#isUniquePhone(String, Long)}
     */
    @Override
    public boolean isUniquePhone(String phone, Long id) {
        if (id == null) {
            return isUniquePhone(phone);
        }
        int count = departmentHibernateDao.findCountByIdAndPhone(phone, id);
        return count == 0;
    }
}
