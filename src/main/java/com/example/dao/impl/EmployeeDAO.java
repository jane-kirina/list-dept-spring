package com.example.dao.impl;

import com.example.dao.impl.decorator.AbstractDecoratorDao;
import com.example.dao.interf.IDao;
import com.example.dao.interf.IEmployeeDao;
import com.example.dao.interf.Mapper;
import com.example.entity.Department;
import com.example.entity.Employee;
import com.example.util.constants.Column;
import com.example.util.constants.QueriesSql;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Extending class EmployeeDAO for working with a table 'employee'.
 */
public class EmployeeDAO extends AbstractDecoratorDao<Employee> implements IEmployeeDao {
    private static final Logger LOGGER = Logger.getLogger(EmployeeDAO.class);
    private final Connection connection;

    public EmployeeDAO(Connection connection) {
        this.connection = connection;
        super.mapperFromSql = mapper;
        super.connection = connection;
    }

    /**
     * {@link IDao#saveEntity(Object, Long)}
     */
    @Override
    public boolean saveEntity(Employee entity, Long id) {
        try (PreparedStatement statement = connection.prepareStatement(QueriesSql.SQL_CREATE_EMPLOYEE)) {
            statement.setLong(1, id);
            statement.setString(2, entity.getName());
            statement.setDate(3, Date.valueOf(entity.getBirthDate()));
            statement.setString(4, entity.getEmail());
            statement.setInt(5, entity.getYearsWorking());
            statement.setLong(6, entity.getDepartment().getId());

            statement.executeUpdate();

            LOGGER.debug("Close resources: add in database");
            connection.close();
        } catch (SQLException exception) {
            LOGGER.error("Something went wrong. Added failed: " + exception.getMessage(), exception);
            return false;
        }
        return true;
    }

    /**
     * {@link IDao#updateEntity(Object)}
     */
    @Override
    public boolean updateEntity(Employee entity) {
        try (PreparedStatement statement = connection.prepareStatement(QueriesSql.SQL_UPDATE_EMPLOYEE)) {
            statement.setString(1, entity.getName());
            statement.setDate(2, Date.valueOf(entity.getBirthDate()));
            statement.setString(3, entity.getEmail());
            statement.setInt(4, entity.getYearsWorking());
            statement.setLong(5, entity.getDepartment().getId());
            statement.setLong(6, entity.getId());

            statement.executeUpdate();

            LOGGER.debug("Close resources: update in database");
            connection.close();
        } catch (SQLException exception) {
            LOGGER.error("Something went wrong. Updated failed: " + exception.getMessage(), exception);
            return false;
        }
        return true;
    }

    /**
     * {@link IDao#deleteEntity(Long)}
     */
    @Override
    public boolean deleteEntity(Long id) {
        return statementWithParameter(id, QueriesSql.SQL_DELETE_EMPLOYEE);
    }

    /**
     * {@link IEmployeeDao#deleteFromDepartment(Long)}
     */
    @Override
    public boolean deleteFromDepartment(Long id) {
        return statementWithParameter(id, QueriesSql.SQL_DELETE_EMPLOYEES_FROM_DEPARTMENT);
    }

    /**
     * {@link IDao#findAll()}
     */
    @Override
    public Set<Employee> findAll() {
        return findAll(QueriesSql.SQL_ALL_EMPLOYEE);
    }

    /**
     * {@link IDao#findEntity(Long)}
     */
    @Override
    public Employee findEntity(Long id) {
        return findById(QueriesSql.SQL_FIND_BY_ID_EMPLOYEE, id, new Employee());
    }

    /**
     * {@link IDao#findMaxId()}
     */
    @Override
    public Long findMaxId() {
        return findMax(QueriesSql.SQL_MAX_ID_EMPLOYEE);
    }

    /**
     * {@link IDao#findNameCount(String)}
     */
    @Override
    public int findNameCount(String name) {
        return findCountByExpression(name, QueriesSql.SQL_FIND_BY_EMAIL_EMPLOYEE);
    }

    /**
     * {@link IEmployeeDao#findEmployees(Long)}
     */
    @Override
    public Set<Employee> findEmployees(Long id) {
        Set<Employee> set = new LinkedHashSet<>();
        try (PreparedStatement statement = connection.prepareStatement(QueriesSql.SQL_ALL_EMPLOYEE_FROM_DEPARTMENT)) {
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            LOGGER.debug("Mapping map: " + mapperFromSql.toString());
            while (resultSet.next()) {
                set.add(mapper.map(resultSet));
            }

            LOGGER.debug("Close resources: find employees in database");
            resultSet.close();
            connection.close();
        } catch (SQLException exception) {
            LOGGER.error("Something went wrong. Couldn't find all employee by they dept: "
                    + exception.getMessage(), exception);

        }
        return set;
    }

    /**
     * Mapper for creating Employee from a ResultSet.
     * {@link Mapper#map(Object)}
     */
    private final Mapper<ResultSet, Employee> mapper = (ResultSet resultSet) -> {
        Employee employee = new Employee();
        Department department = new Department();
        try {
            employee.setId(resultSet.getLong(Column.EMPLOYEE_ID));
            employee.setName(resultSet.getString(Column.EMPLOYEE_NAME));
            employee.setBirthDate(LocalDate.parse(resultSet.getString(Column.EMPLOYEE_BIRTH)));
            employee.setEmail(resultSet.getString(Column.EMPLOYEE_EMAIL));
            employee.setYearsWorking(resultSet.getInt(Column.EMPLOYEE_YEARS_WORKING));
            department.setId(resultSet.getLong(Column.DEPARTMENT_ID));
            employee.setDepartment(department);
        } catch (SQLException exception) {
            LOGGER.error("Something went wrong with mapping object: " + exception.getMessage(), exception);
        }
        return employee;
    };
}
