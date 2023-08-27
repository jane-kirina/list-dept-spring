package com.example.dao.interf;

import com.example.entity.Department;

/**
 * Interface for DepartmentDAO.
 */
public interface IDepartmentDao extends IDao<Department> {
    /**
     * Finds an object by a phone column in a table.
     * @param phone key
     * @return count of rows wit
     */
    int findPhoneCount(String phone);
}
