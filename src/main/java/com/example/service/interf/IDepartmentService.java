package com.example.service.interf;

import com.example.entity.Department;

/**
 * Interface service for DepartmentDAO.
 */
public interface IDepartmentService extends IService<Department> {
    /**
     * Checks if phone is unique
     * @param phone enter
     * @return true, if unique
     */
    boolean isUniquePhone(String phone);

    /**
     * Checks if name is unique
     * @param name enter
     * @return true, if unique
     */
    boolean isUniqueName(String name);
}
