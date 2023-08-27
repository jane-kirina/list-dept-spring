package com.example.service.impl;

import com.example.dao.connection.SingletonConnection;
import com.example.dao.impl.DepartmentDAO;
import com.example.entity.Department;
import com.example.service.interf.IDepartmentService;
import com.example.service.interf.IService;

import java.util.Set;

/**
 * Department service for working with a database
 */
public class DepartmentService implements IDepartmentService {
    SingletonConnection connection = SingletonConnection.getInstance();

    /**
     * {@link IService#create(Object)}
     */
    @Override
    public boolean create(Department entity) {
        DepartmentDAO departmentDao = connection.getDepartmentDao();
        long count = this.findMaxId() + 1;
        return departmentDao.saveEntity(entity, count);
    }

    /**
     * {@link IService#update(Object)}
     */
    @Override
    public boolean update(Department entity) {
        DepartmentDAO departmentDao = connection.getDepartmentDao();
        return departmentDao.updateEntity(entity);
    }

    /**
     * {@link IService#delete(Long)}
     */
    @Override
    public boolean delete(Long id) {
        DepartmentDAO departmentDao = connection.getDepartmentDao();
        return departmentDao.deleteEntity(id);
    }

    /**
     * {@link IService#allData()}
     */
    @Override
    public Set<Department> allData() {
        DepartmentDAO departmentDao = connection.getDepartmentDao();
        return departmentDao.findAll();
    }

    /**
     * {@link IService#findEntity(Long)}
     */
    @Override
    public Department findEntity(Long id) {
        DepartmentDAO departmentDao = connection.getDepartmentDao();
        return departmentDao.findEntity(id);
    }

    /**
     * {@link IService#findMaxId()}
     */
    @Override
    public Long findMaxId() {
        DepartmentDAO departmentDao = connection.getDepartmentDao();
        return departmentDao.findMaxId();
    }

    /**
     * {@link IDepartmentService#isUniqueName(String)}
     */
    @Override
    public boolean isUniqueName(String name) {
        DepartmentDAO departmentDao = connection.getDepartmentDao();
        int count = departmentDao.findNameCount(name);
        return count == 0;
    }

    /**
     * {@link IDepartmentService#isUniquePhone(String)}
     */
    @Override
    public boolean isUniquePhone(String expression) {
        DepartmentDAO departmentDao = connection.getDepartmentDao();
        int count = departmentDao.findPhoneCount(expression);
        return count == 0;
    }
}
