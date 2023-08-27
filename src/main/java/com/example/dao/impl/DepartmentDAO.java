package com.example.dao.impl;

import com.example.dao.impl.decorator.AbstractDecoratorDao;
import com.example.dao.interf.IDao;
import com.example.dao.interf.IDepartmentDao;
import com.example.dao.interf.Mapper;
import com.example.entity.Department;
import com.example.util.constants.Column;
import com.example.util.constants.QueriesSql;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

/**
 * Extending class DepartmentDAO for working with a table 'dept'.
 */
public class DepartmentDAO extends AbstractDecoratorDao<Department> implements IDepartmentDao {
    private static final Logger LOGGER = Logger.getLogger(DepartmentDAO.class);
    private final Connection connection;

    public DepartmentDAO(Connection connection) {
        this.connection = connection;
        super.mapperFromSql = mapper;
        super.connection = connection;
    }

    /**
     * {@link IDao#saveEntity(Object, Long)}
     */
    @Override
    public boolean saveEntity(Department entity, Long id) {
        try (PreparedStatement statement = connection.prepareStatement(QueriesSql.SQL_CREATE_DEPARTMENT)) {
            statement.setLong(1, id);
            statement.setString(2, entity.getName());
            statement.setString(3, entity.getNumber());

            statement.executeUpdate();

            LOGGER.debug("Close resources: add in database");
            connection.close();
        } catch (SQLException exception) {
            LOGGER.error("Something went wrong. Added failed: " + exception.getMessage());
            return false;
        }
        return true;
    }

    /**
     * {@link IDao#updateEntity(Object)}
     */
    @Override
    public boolean updateEntity(Department entity) {
        try (PreparedStatement statement = connection.prepareStatement(QueriesSql.SQL_UPDATE_DEPARTMENT)) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getNumber());
            statement.setLong(3, entity.getId());

            statement.executeUpdate();

            LOGGER.debug("Close resources: add in database");
            connection.close();
        } catch (SQLException exception) {
            LOGGER.error("Something went wrong. Updated failed: " + exception.getMessage());
            return false;
        }
        return true;
    }

    /**
     * {@link IDao#deleteEntity(Long)}
     */
    @Override
    public boolean deleteEntity(Long id) {
        return statementWithParameter(id, QueriesSql.SQL_DELETE_DEPARTMENT);
    }

    /**
     * {@link IDao#findAll()}
     */
    @Override
    public Set<Department> findAll() {
        return findAll(QueriesSql.SQL_ALL_DEPARTMENT);
    }

    /**
     * {@link IDao#findEntity(Long)}
     */
    @Override
    public Department findEntity(Long id) {
        return findById(QueriesSql.SQL_FIND_BY_ID_DEPARTMENT, id, new Department());
    }

    /**
     * {@link IDao#findMaxId()}
     */
    @Override
    public Long findMaxId() {
        return findMax(QueriesSql.SQL_MAX_ID_DEPARTMENT);
    }

    /**
     * {@link IDao#findNameCount(String)}
     */
    @Override
    public int findNameCount(String name) {
        return findCountByExpression(name, QueriesSql.SQL_FIND_BY_NAME_DEPARTMENT);
    }

    /**
     * {@link IDepartmentDao#findPhoneCount(String)}
     */
    @Override
    public int findPhoneCount(String phone) {
        return findCountByExpression(phone, QueriesSql.SQL_FIND_BY_PHONE_DEPARTMENT);
    }

    /**
     * Mapper for creating Department from ResultSet.
     * {@link Mapper#map(Object)}
     */
    private final Mapper<ResultSet, Department> mapper = (ResultSet resultSet) -> {
        Department department = new Department();
        try {
            department.setId(resultSet.getLong(Column.DEPARTMENT_ID));
            department.setName(resultSet.getString(Column.DEPARTMENT_NAME));
            department.setNumber(resultSet.getString(Column.DEPARTMENT_PHONE_NUMBER));
        } catch (SQLException exception) {
            LOGGER.error("Something went wrong with mapping object: " + exception.getMessage());
        }
        return department;
    };
}
