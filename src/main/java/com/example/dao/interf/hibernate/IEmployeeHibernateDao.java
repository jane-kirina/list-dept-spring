package com.example.dao.interf.hibernate;

import com.example.dao.interf.IEmployeeDao;

/**
 * Interface for EmployeeHibernateDao.
 */
public interface IEmployeeHibernateDao extends IEmployeeDao {
    /**
     * Finds count of id by email in table.
     * @param email of employee
     * @param id of employee
     * @return count of id
     */
    int findCountIdByEmail(String email, Long id);
}
