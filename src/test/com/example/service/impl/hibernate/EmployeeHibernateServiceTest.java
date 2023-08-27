package com.example.service.impl.hibernate;

import com.example.dao.interf.hibernate.IEmployeeHibernateDao;
import com.example.entity.Employee;
import com.example.exception.database.AddInDatabaseException;
import com.example.exception.database.DeleteInDatabaseException;
import com.example.exception.database.UpdateDataInDatabaseException;
import com.google.common.collect.Sets;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeHibernateServiceTest {
    @InjectMocks
    private EmployeeHibernateService employeeHibernateService;
    @Mock
    private IEmployeeHibernateDao employeeHibernateDao;
    @Mock
    private Employee testEmployee;
    @Mock
    private Employee testEmployeeSecond;

    private final Long idOfEmployee = 1L;
    private final Long maxId = 0L;
    private final String emailOfEmployee = "email";

    @Test
    public void shouldFindAllEmployeeFromDepartment() {
        Set<Employee> set = Sets.newHashSet(testEmployee, testEmployeeSecond);

        when(employeeHibernateDao.findEmployees(idOfEmployee)).thenReturn(set);

        Set<Employee> result = employeeHibernateService.findFromDept(idOfEmployee);
        Long expected = 2L;
        Long actual = (long) result.size();

        assertEquals(expected, actual);
        assertTrue(result.containsAll(set));
        verify(employeeHibernateDao).findEmployees(idOfEmployee);
    }

    @Test
    public void shouldDeleteEmployeesFromDepartment_WithoutException() {
        Long idOfDepartmentWithEmployees = 1L;

        when(employeeHibernateDao.deleteFromDepartment(idOfDepartmentWithEmployees)).thenReturn(true);

        boolean result = employeeHibernateService.deleteEmployeesFromDept(idOfDepartmentWithEmployees);

        Assert.assertTrue(result);
        verify(employeeHibernateDao).deleteFromDepartment(idOfDepartmentWithEmployees);
    }

    @Test
    public void shouldDeleteEmployeesFromDepartment_ExpectException() {
        Long wrongIdOfDepartmentWithEmployees = -1L;

        doThrow(new DeleteInDatabaseException()).when(employeeHibernateDao).deleteFromDepartment(wrongIdOfDepartmentWithEmployees);

        boolean result = employeeHibernateService.deleteEmployeesFromDept(wrongIdOfDepartmentWithEmployees);

        Assert.assertTrue(result);
        verify(employeeHibernateDao).deleteFromDepartment(wrongIdOfDepartmentWithEmployees);
    }

    @Test
    public void shouldDeleteEmployee_WithoutException() {
        when(employeeHibernateDao.deleteEntity(idOfEmployee)).thenReturn(true);

        boolean result = employeeHibernateService.delete(idOfEmployee);

        Assert.assertTrue(result);
        verify(employeeHibernateDao).deleteEntity(idOfEmployee);
    }

    @Test
    public void shouldDeleteEmployee_ExpectException() {
        Long wrongIdOfEmployee = -1L;

        doThrow(new DeleteInDatabaseException()).when(employeeHibernateDao).deleteEntity(wrongIdOfEmployee);

        boolean result = employeeHibernateService.delete(wrongIdOfEmployee);

        Assert.assertTrue(result);
        verify(employeeHibernateDao).deleteEntity(wrongIdOfEmployee);
    }

    @Test
    public void shouldAddEmployee_WithoutException() {
        when(employeeHibernateDao.saveEntity(testEmployee, idOfEmployee)).thenReturn(true);
        when(employeeHibernateDao.findMaxId()).thenReturn(maxId);

        boolean result = employeeHibernateService.create(testEmployee);

        Assert.assertTrue(result);
        verify(employeeHibernateDao).saveEntity(testEmployee, idOfEmployee);
        verify(employeeHibernateDao).findMaxId();
    }

    @Test
    public void whenTryAddDefunctEmployee_ExpectException() {
        doThrow(new AddInDatabaseException()).when(employeeHibernateDao).saveEntity(null, idOfEmployee);
        when(employeeHibernateDao.findMaxId()).thenReturn(maxId);

        boolean result = employeeHibernateService.create(null);

        verify(employeeHibernateDao).saveEntity(null, idOfEmployee);
        verify(employeeHibernateDao).findMaxId();
    }

    @Test
    public void shouldUpdateEmployee_WithoutException() {
        when(employeeHibernateDao.updateEntity(testEmployee)).thenReturn(true);

        boolean result = employeeHibernateService.update(testEmployee);

        Assert.assertTrue(result);
        verify(employeeHibernateDao).updateEntity(testEmployee);
    }

    @Test
    public void shouldUpdateEmployee_ExpectException() {
        doThrow(new UpdateDataInDatabaseException()).when(employeeHibernateDao).updateEntity(testEmployee);

        boolean result = employeeHibernateService.update(testEmployee);

        Assert.assertTrue(result);
        verify(employeeHibernateDao).updateEntity(testEmployee);
    }

    @Test
    public void shouldFindAllEmployee() {
        Set<Employee> set = new LinkedHashSet<>();
        set.add(testEmployee);
        set.add(testEmployeeSecond);

        when(employeeHibernateDao.findAll()).thenReturn(set);

        Set<Employee> result = employeeHibernateService.allData();

        assertEquals(2, result.size());
        assertTrue(result.containsAll(set));
        verify(employeeHibernateDao).findAll();
    }

    @Test
    public void shouldFindCorrectEmployeeInDatabase() {
        when(employeeHibernateDao.findEntity(idOfEmployee)).thenReturn(testEmployee);

        Employee employee = employeeHibernateService.findEntity(idOfEmployee);

        Assert.assertEquals(testEmployee, employee);
        verify(employeeHibernateDao).findEntity(idOfEmployee);
    }

    @Test
    public void shouldFindMaxIdInDatabase() {
        when(employeeHibernateDao.findMaxId()).thenReturn(idOfEmployee);

        Long maxId = employeeHibernateService.findMaxId();
        Long expected = 1L;

        Assert.assertEquals(expected, maxId);
        verify(employeeHibernateDao).findMaxId();
    }

    @Test
    public void shouldPassUniquenessCheckEmailEmployee() {
        when(employeeHibernateDao.findNameCount(emailOfEmployee)).thenReturn(0);

        boolean result = employeeHibernateService.isUniqueEmail(emailOfEmployee, null);

        Assert.assertTrue(result);
        verify(employeeHibernateDao).findNameCount(emailOfEmployee);
    }

    @Test
    public void shouldNotPassUniquenessCheckEmailEmployee() {
        when(employeeHibernateDao.findNameCount(emailOfEmployee)).thenReturn(1);

        boolean result = employeeHibernateService.isUniqueEmail(emailOfEmployee, null);

        Assert.assertFalse(result);
        verify(employeeHibernateDao).findNameCount(emailOfEmployee);
    }

    @Test
    public void shouldPassUniquenessCheckEmailWithIdEmployee() {
        when(employeeHibernateDao.findCountIdByEmail(emailOfEmployee, idOfEmployee)).thenReturn(0);

        boolean result = employeeHibernateService.isUniqueEmail(emailOfEmployee, idOfEmployee);

        Assert.assertTrue(result);
        verify(employeeHibernateDao).findCountIdByEmail(emailOfEmployee, idOfEmployee);
    }

    @Test
    public void shouldNotPassUniquenessCheckEmailIdEmployee() {
        when(employeeHibernateDao.findCountIdByEmail(emailOfEmployee, idOfEmployee)).thenReturn(1);

        boolean result = employeeHibernateService.isUniqueEmail(emailOfEmployee, idOfEmployee);

        Assert.assertFalse(result);
        verify(employeeHibernateDao).findCountIdByEmail(emailOfEmployee, idOfEmployee);
    }
}