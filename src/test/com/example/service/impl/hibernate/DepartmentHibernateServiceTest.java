package com.example.service.impl.hibernate;

import com.example.dao.interf.hibernate.IDepartmentHibernateDao;
import com.example.entity.Department;
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

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DepartmentHibernateServiceTest {
    @InjectMocks
    private DepartmentHibernateService departmentHibernateService;
    @Mock
    private IDepartmentHibernateDao departmentHibernateDao;
    @Mock
    private Department testDepartment;
    @Mock
    private Department testDepartmentSecond;

    private final Long idOfDepartment = 1L;
    private final String phoneOfDepartment = "phone";
    private final String nameOfDepartment = "name";
    private final Long maxId = 0L;

    @Test
    public void shouldAddDepartment_WithoutException() {
        when(departmentHibernateDao.saveEntity(testDepartment, idOfDepartment)).thenReturn(true);
        when(departmentHibernateDao.findMaxId()).thenReturn(0L);

        boolean result = departmentHibernateService.create(testDepartment);

        Assert.assertTrue(result);
        verify(departmentHibernateDao).saveEntity(testDepartment, idOfDepartment);
        verify(departmentHibernateDao).findMaxId();
    }

    @Test
    public void whenTryAddDefunctDepartment_ExpectException() {
        doThrow(new AddInDatabaseException()).when(departmentHibernateDao).saveEntity(null, idOfDepartment);
        when(departmentHibernateDao.findMaxId()).thenReturn(0L);

        boolean result = departmentHibernateService.create(null);

        verify(departmentHibernateDao).saveEntity(null, idOfDepartment);
        verify(departmentHibernateDao).findMaxId();
    }

    @Test
    public void shouldDeleteDepartment_WithoutException() {
        when(departmentHibernateDao.deleteEntity(idOfDepartment)).thenReturn(true);

        boolean result = departmentHibernateService.delete(idOfDepartment);

        Assert.assertTrue(result);
        verify(departmentHibernateDao).deleteEntity(idOfDepartment);
    }

    @Test
    public void whenTryDeleteDefunctDepartment_ExpectException() {
        Long wrongIdOfDepartment = -1L;
        doThrow(new DeleteInDatabaseException()).when(departmentHibernateDao).deleteEntity(wrongIdOfDepartment);

        boolean result = departmentHibernateService.delete(wrongIdOfDepartment);

        Assert.assertTrue(result);
        verify(departmentHibernateDao).deleteEntity(wrongIdOfDepartment);
    }

    @Test
    public void shouldPassUniquenessCheckNameDepartment() {
        when(departmentHibernateDao.findNameCount(nameOfDepartment)).thenReturn(0);

        boolean result = departmentHibernateService.isUniqueName(nameOfDepartment, null);

        Assert.assertTrue(result);
        verify(departmentHibernateDao).findNameCount(nameOfDepartment);
    }

    @Test
    public void shouldNotPassUniquenessCheckNameDepartment() {
        when(departmentHibernateDao.findNameCount(nameOfDepartment)).thenReturn(1);

        boolean result = departmentHibernateService.isUniqueName(nameOfDepartment, null);

        Assert.assertFalse(result);
        verify(departmentHibernateDao).findNameCount(nameOfDepartment);
    }

    @Test
    public void shouldPassUniquenessCheckNameWithIdDepartment() {
        when(departmentHibernateDao.findCountByIdAndName(nameOfDepartment, idOfDepartment)).thenReturn(0);

        boolean result = departmentHibernateService.isUniqueName(nameOfDepartment, idOfDepartment);

        Assert.assertTrue(result);
        verify(departmentHibernateDao).findCountByIdAndName(nameOfDepartment, idOfDepartment);
    }

    @Test
    public void shouldNotPassUniquenessCheckNameWithIdDepartment() {
        when(departmentHibernateDao.findCountByIdAndName(nameOfDepartment, idOfDepartment)).thenReturn(1);

        boolean result = departmentHibernateService.isUniqueName(nameOfDepartment, idOfDepartment);

        Assert.assertFalse(result);
        verify(departmentHibernateDao).findCountByIdAndName(nameOfDepartment, idOfDepartment);
    }

    @Test
    public void shouldPassUniquenessCheckPhoneDepartment() {
        when(departmentHibernateDao.findPhoneCount(phoneOfDepartment)).thenReturn(0);

        boolean result = departmentHibernateService.isUniquePhone(phoneOfDepartment, null);

        Assert.assertTrue(result);
        verify(departmentHibernateDao).findPhoneCount(phoneOfDepartment);
    }

    @Test
    public void shouldNotPassUniquenessCheckPhoneDepartment() {
        when(departmentHibernateDao.findPhoneCount(phoneOfDepartment)).thenReturn(1);

        boolean result = departmentHibernateService.isUniquePhone(phoneOfDepartment, null);

        Assert.assertFalse(result);
        verify(departmentHibernateDao).findPhoneCount(phoneOfDepartment);
    }

    @Test
    public void shouldPassUniquenessCheckPhoneWithIdDepartment() {
        when(departmentHibernateDao.findCountByIdAndPhone(phoneOfDepartment, idOfDepartment)).thenReturn(0);

        boolean result = departmentHibernateService.isUniquePhone(phoneOfDepartment, idOfDepartment);

        Assert.assertTrue(result);
        verify(departmentHibernateDao).findCountByIdAndPhone(phoneOfDepartment, idOfDepartment);
    }

    @Test
    public void shouldNotPassUniquenessCheckPhoneWithIdDepartment() {
        when(departmentHibernateDao.findCountByIdAndPhone(phoneOfDepartment, idOfDepartment)).thenReturn(1);

        boolean result = departmentHibernateService.isUniquePhone(phoneOfDepartment, idOfDepartment);

        Assert.assertFalse(result);
        verify(departmentHibernateDao).findCountByIdAndPhone(phoneOfDepartment, idOfDepartment);
    }

    @Test
    public void shouldFindMaxIdInDatabase() {
        when(departmentHibernateDao.findMaxId()).thenReturn(maxId);

        Long maxId = departmentHibernateService.findMaxId();
        Long expected = 0L;

        Assert.assertEquals(expected, maxId);
        verify(departmentHibernateDao).findMaxId();
    }

    @Test
    public void shouldFindCorrectDepartmentInDatabase() {
        when(departmentHibernateDao.findEntity(idOfDepartment)).thenReturn(testDepartment);

        Department department = departmentHibernateService.findEntity(idOfDepartment);

        Assert.assertEquals(testDepartment, department);
        verify(departmentHibernateDao).findEntity(idOfDepartment);
    }

    @Test
    public void shouldFindAllDepartment() {
        Set<Department> set = Sets.newHashSet(testDepartment, testDepartmentSecond);

        when(departmentHibernateDao.findAll()).thenReturn(set);

        Set<Department> result = departmentHibernateService.allData();
        Long expected = 2L;
        Long actual = (long) result.size();

        assertEquals(expected, actual);
        assertTrue(result.containsAll(set));
        verify(departmentHibernateDao).findAll();
    }

    @Test
    public void shouldUpdateDepartment_WithoutException() {
        when(departmentHibernateDao.updateEntity(testDepartment)).thenReturn(true);

        boolean result = departmentHibernateService.update(testDepartment);

        Assert.assertTrue(result);
        verify(departmentHibernateDao).updateEntity(testDepartment);
    }

    @Test
    public void whenTryUpdateDepartment_ExpectException() {
        doThrow(new UpdateDataInDatabaseException()).when(departmentHibernateDao).updateEntity(testDepartment);

        boolean result = departmentHibernateService.update(testDepartment);

        Assert.assertTrue(result);
        verify(departmentHibernateDao).updateEntity(testDepartment);
    }
}