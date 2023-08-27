package com.example.dao.impl.hibernate;

import com.example.dao.impl.hibernate.decorator.AbstractDecoratorHibernateDao;
import com.example.dao.interf.IDao;
import com.example.dao.interf.IEmployeeDao;
import com.example.dao.interf.hibernate.IEmployeeHibernateDao;
import com.example.entity.Employee;
import com.example.exception.database.DeleteInDatabaseException;
import com.example.util.constants.LoggerMessage;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.text.MessageFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Employee DAO for working with table 'employee' through hibernate.
 */
public class EmployeeHibernateDAO extends AbstractDecoratorHibernateDao<Employee> implements IEmployeeHibernateDao {
    private static final Logger LOGGER = Logger.getLogger(EmployeeHibernateDAO.class);
    private static final String HQL_ALL_EMPL = "From Employee";
    private static final String HQL_COUNT_EMAIL = "SELECT count(*) FROM Employee WHERE email=:name";
    private static final String HQL_COUNT_ID_BY_EMAIL = "SELECT count(id) From Employee WHERE email=:name AND id!=:id";
    private static final String HQL_DELETE_BY_ID = "delete from Employee where department.id=:id";

    public EmployeeHibernateDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    /**
     * {@link IDao#deleteEntity(Long)}
     */
    @Override
    public boolean deleteEntity(Long id) throws DeleteInDatabaseException {
        Employee employee;

        try {
            Session session = sessionFactory.getCurrentSession();
            employee = session.get(Employee.class, id);

            session.delete(employee);
        } catch (Exception exception) {
            LOGGER.error(MessageFormat.format(LoggerMessage.DELETE_FAILED_BY_ID, exception.getMessage(), id), exception);
            throw new DeleteInDatabaseException(exception);
        }

        return employee != null;
    }

    /**
     * {@link IEmployeeDao#deleteFromDepartment(Long)}
     */
    @Override
    public boolean deleteFromDepartment(Long id) throws DeleteInDatabaseException {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.createQuery(HQL_DELETE_BY_ID)
                    .setParameter("id", id)
                    .executeUpdate();
        } catch (Exception exception) {
            LOGGER.error(MessageFormat.format(LoggerMessage.DELETE_FAILED_BY_ID, exception.getMessage(), id), exception);
            throw new DeleteInDatabaseException(exception);
        }
        return true;
    }

    /**
     * {@link IDao#findAll()}
     */
    @Override
    public Set<Employee> findAll() {
        Session session = sessionFactory.openSession();

        Query<Employee> query = session.createQuery(HQL_ALL_EMPL, Employee.class);
        List<Employee> employees =  query.list();

        session.close();
        return new HashSet<>(employees);
    }

    /**
     * {@link IDao#findEntity(Long)}
     */
    @Override
    public Employee findEntity(Long id) {
        Session session = sessionFactory.openSession();

        Employee employee = session.get(Employee.class, id);

        session.close();
        return employee;
    }

    /**
     * {@link IDao#findMaxId()}
     */
    @Override
    public Long findMaxId() {
        Session session = sessionFactory.openSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = criteriaBuilder.createQuery(Long.class);
        Root<Employee> root = criteria.from(Employee.class);
        criteria.select(criteriaBuilder.max(root.get("id")));

        TypedQuery<Long> query = session.createQuery(criteria);

        List<Long> resultList = query.getResultList();

        session.close();

        Long result = resultList.get(0);

        return result == null ? 0 : result;
    }

    /**
     * {@link IDao#findNameCount(String)}
     */
    @Override
    public int findNameCount(String name) {
        return super.findCount(name, HQL_COUNT_EMAIL);
    }

    /**
     * {@link AbstractDecoratorHibernateDao#findCount(String, Long, String)}
     */
    @Override
    public int findCountIdByEmail(String name, Long id) {
        return super.findCount(name, id, HQL_COUNT_ID_BY_EMAIL);
    }

    /**
     * {@link IEmployeeDao#findEmployees(Long)}
     */
    @Override
    public Set<Employee> findEmployees(Long id) {
        Session session = sessionFactory.openSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        CriteriaQuery<Employee> criteria = criteriaBuilder.createQuery(Employee.class);

        Root<Employee> root = criteria.from(Employee.class);
        criteria.select(root).where(criteriaBuilder.equal(root.get("department"), id));

        TypedQuery<Employee> query = session.createQuery(criteria);

        List<Employee> employees = query.getResultList();

        session.close();

        return new HashSet<>(employees);
    }
}