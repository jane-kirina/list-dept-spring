package com.example.model;

import com.example.dao.interf.Mapper;
import com.example.entity.Department;
import com.example.entity.Employee;

public class FacadeForm {
    private FacadeForm() {}

    public static final ConvertData<DepartmentPojo, Department> convertToDepartment = (DepartmentPojo departmentPojo) -> {
        Department department = new Department();

        department.setId(departmentPojo.getId());
        department.setName(departmentPojo.getName());
        department.setNumber(departmentPojo.getNumber());

        return department;
    };

    public static final ConvertData<Department, DepartmentPojo> convertToDepartmentPojo = (Department department) -> {
        DepartmentPojo departmentPojo = new DepartmentPojo();

        departmentPojo.setId(department.getId());
        departmentPojo.setName(department.getName());
        departmentPojo.setNumber(department.getNumber());

        return departmentPojo;
    };

    public static final ConvertData<Employee, EmployeePojo> convertToEmployeePojo = (Employee employee) -> {
        EmployeePojo employeePojo = new EmployeePojo();

        employeePojo.setId(employee.getId());
        employeePojo.setName(employee.getName());
        employeePojo.setBirthDate(employee.getBirthDate());
        employeePojo.setYearsWorking(employee.getYearsWorking());
        employeePojo.setEmail(employee.getEmail());

        DepartmentPojo departmentPojo = FacadeForm.convertToDepartmentPojo.map(employee.getDepartment());

        employeePojo.setDepartment(departmentPojo);

        return employeePojo;
    };

    public static final Mapper<EmployeePojo, Employee> convertToEmployee = (EmployeePojo employeePojo) -> {
        Employee employee = new Employee();

        employee.setId(employeePojo.getId());
        employee.setName(employeePojo.getName());
        employee.setBirthDate(employeePojo.getBirthDate());
        employee.setYearsWorking(employeePojo.getYearsWorking());
        employee.setEmail(employeePojo.getEmail());

        Department department = FacadeForm.convertToDepartment.map(employeePojo.getDepartment());

        employee.setDepartment(department);

        return employee;
    };

}
