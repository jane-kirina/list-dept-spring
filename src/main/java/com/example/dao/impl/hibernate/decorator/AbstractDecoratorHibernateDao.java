package com.example.dao.impl.hibernate.decorator;

import com.example.dao.interf.IDao;
import com.example.exception.database.AddInDatabaseException;
import com.example.exception.database.UpdateDataInDatabaseException;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Abstract Decorator for Dao, Hibernate.
 * @param <T> type of entity
 */
public abstract class AbstractDecoratorHibernateDao<T> {
    private static final Logger LOGGER = Logger.getLogger(AbstractDecoratorHibernateDao.class);
    protected final SessionFactory sessionFactory;

    protected AbstractDecoratorHibernateDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Adds row in table if input entity is not already exist.
     * @param entity to add
     * @return true if was added
     */
    public boolean saveEntity(T entity, Long id) throws AddInDatabaseException {
        Long saveId;

        try {
            saveId = (Long) sessionFactory.getCurrentSession().save(entity);
        } catch (Exception exception) {
            LOGGER.error("Something went wrong. Added failed: " + exception.getMessage(), exception);
            throw new AddInDatabaseException(exception);
        }

        return saveId != null;
    }

    /**
     * {@link IDao#updateEntity(Object)}
     */
    public boolean updateEntity(T entity) throws UpdateDataInDatabaseException {
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(entity);
        } catch (Exception exception) {
            LOGGER.error("Something went wrong. Updated failed: " + exception.getMessage(), exception);
            throw new UpdateDataInDatabaseException(exception);
        }

        return true;
    }

    /**
     * Find count by hql.
     * @param name for hql
     * @param hql to table
     * @return count
     */
    protected int findCount(String name, String hql) {
        Session session = sessionFactory.openSession();

        Query<Long> query = session.createQuery(hql, Long.class);
        query.setParameter("name", name);

        List<Long> resultList = query.getResultList();

        session.close();

        return ((Number) resultList.get(0)).intValue();
    }

    /**
     * Find count by hql.
     * @param name for hql
     * @param id for hql
     * @param hql to table
     * @return count
     */
    protected int findCount(String name, Long id, String hql) {
        Session session = sessionFactory.openSession();

        Query<Long> query = session.createQuery(hql, Long.class);
        query.setParameter("name", name);
        query.setParameter("id", id);

        List<Long> resultList = query.getResultList();

        session.close();

        return ((Number) resultList.get(0)).intValue();
    }
}