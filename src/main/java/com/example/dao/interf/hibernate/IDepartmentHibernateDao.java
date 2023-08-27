package com.example.dao.interf.hibernate;

import com.example.dao.interf.IDepartmentDao;

/**
 * Interface for DepartmentHibernateDao.
 */
public interface IDepartmentHibernateDao extends IDepartmentDao {
    /**
     * Finds count of id by email in table.
     * @param name of department
     * @param id of department
     * @return count of id
     */
    int findCountByIdAndName(String name, Long id);

    /**
     * Finds count of id by email in table.
     * @param phone of department
     * @param id of department
     * @return count of id
     */
    int findCountByIdAndPhone(String phone, Long id);
}
