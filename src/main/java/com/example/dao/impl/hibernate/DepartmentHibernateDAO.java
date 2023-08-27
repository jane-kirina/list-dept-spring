package com.example.dao.impl.hibernate;

import com.example.dao.impl.hibernate.decorator.AbstractDecoratorHibernateDao;
import com.example.dao.interf.IDao;
import com.example.dao.interf.IDepartmentDao;
import com.example.dao.interf.hibernate.IDepartmentHibernateDao;
import com.example.entity.Department;
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
 * Department service for working with table 'dept' through hibernate.
 */
public class DepartmentHibernateDAO extends AbstractDecoratorHibernateDao<Department> implements IDepartmentHibernateDao {
    private static final Logger LOGGER = Logger.getLogger(DepartmentHibernateDAO.class);
    private static final String HQL_COUNT_PHONE = "SELECT count(*) FROM Department WHERE number=:name";
    private static final String HQL_COUNT_ID_BY_PHONE = "SELECT count(id) FROM Department WHERE number=:name AND id!=:id";
    private static final String HQL_COUNT_NAME = "SELECT count(*) FROM Department WHERE name=:name";
    private static final String HQL_COUNT_ID_BY_NAME = "SELECT count(id) FROM Department WHERE name=:name AND id!=:id";
    private static final String HQL_ALL_DEPT = "From Department";

    public DepartmentHibernateDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    /**
     * {@link IDao#deleteEntity(Long)}
     */
    @Override
    public boolean deleteEntity(Long id) throws DeleteInDatabaseException {
        Department department;
        try {
            Session session = sessionFactory.getCurrentSession();

            department = session.get(Department.class, id);

            session.delete(department);
        } catch (Exception exception) {
            LOGGER.error(MessageFormat.format(LoggerMessage.DELETE_FAILED_BY_ID, exception.getMessage(), id), exception);
            throw new DeleteInDatabaseException(exception);
        }

        return department != null;
    }

    /**
     * {@link IDao#findAll()}
     */
    @Override
    public Set<Department> findAll() {
        Session session = sessionFactory.openSession();

        Query<Department> query = session.createQuery(HQL_ALL_DEPT, Department.class);
        List<Department> departments =  query.list();

        session.close();
        return new HashSet<>(departments);
    }

    /**
     * {@link IDao#findEntity(Long)}
     */
    @Override
    public Department findEntity(Long id) {
        Session session = sessionFactory.openSession();

        Department department = session.get(Department.class, id);

        session.close();
        return department;
    }

    /**
     * {@link IDao#findMaxId()}
     */
    @Override
    public Long findMaxId() {
        Session session = sessionFactory.openSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        CriteriaQuery<Long> criteria = session.getCriteriaBuilder().createQuery(Long.class);
        Root<Department> root = criteria.from(Department.class);
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
        return super.findCount(name, HQL_COUNT_NAME);
    }

    /**
     * {@link AbstractDecoratorHibernateDao#findCount(String, Long, String)}
     */
    @Override
    public int findCountByIdAndName(String name, Long id) {
        return super.findCount(name, id, HQL_COUNT_ID_BY_NAME);
    }

    /**
     * {@link AbstractDecoratorHibernateDao#findCount(String, Long, String)}
     */
    @Override
    public int findCountByIdAndPhone(String phone, Long id) {
        return super.findCount(phone, id, HQL_COUNT_ID_BY_PHONE);
    }

    /**
     * {@link IDepartmentDao#findPhoneCount(String)}
     */
    @Override
    public int findPhoneCount(String phone) {
        return super.findCount(phone, HQL_COUNT_PHONE);
    }
}