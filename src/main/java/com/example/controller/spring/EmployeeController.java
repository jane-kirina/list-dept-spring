package com.example.controller.spring;

import com.example.entity.Employee;
import com.example.model.DepartmentPojo;
import com.example.model.EmployeePojo;
import com.example.model.FacadeForm;
import com.example.service.interf.hibernate.IEmployeeHibernateService;
import com.example.util.constants.Parameters;
import com.example.util.constants.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Controller foe Employee commands.
 */
@RestController
@RequestMapping(Url.MAIN_EMPLOYEE)
public class EmployeeController {
    private final IEmployeeHibernateService employeeHibernateService;
    private final Validator springValidator;

    @Autowired
    public EmployeeController(IEmployeeHibernateService employeeHibernateService, Validator springValidator) {
        this.employeeHibernateService = employeeHibernateService;
        this.springValidator = springValidator;
    }

    @InitBinder(Parameters.EMPLOYEE_POJO)
    public void customizeBinding(WebDataBinder binder) {
        binder.addValidators(springValidator);
    }

    /**
     * Return department with employees by Id.
     */
    @GetMapping
    public Set<EmployeePojo> listEmployee(@PathVariable Long deptId) {
        Set<Employee> employees = employeeHibernateService.findFromDept(deptId);

        return employees.stream()
                .map(FacadeForm.convertToEmployeePojo::map)
                .collect(Collectors.toSet());
    }

    /**
     * Create new employee.
     */
    @PostMapping(value = Url.CREATE_DATA, consumes="application/json")
    public ResponseEntity<?> addEmployee(@Valid @RequestBody EmployeePojo employeePojo,
                                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new ArrayList<>(bindingResult.getAllErrors()), HttpStatus.I_AM_A_TEAPOT);
        }
        Employee employee = FacadeForm.convertToEmployee.map(employeePojo);
        employeeHibernateService.create(employee);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Redirect user to add page.
     */
    @GetMapping(value = Url.CREATE_PAGE)
    public EmployeePojo showAddPageEmployee(@PathVariable Long deptId) {
        return new EmployeePojo(new DepartmentPojo());
    }

    /**
     * Update employee.
     */
    @PutMapping(value = Url.UPDATE_DATA)
    public ResponseEntity<?> updateEmployee(@Valid @RequestBody EmployeePojo employeePojo,
                                                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new ArrayList<>(bindingResult.getAllErrors()), HttpStatus.I_AM_A_TEAPOT);
        }
        Employee employee = FacadeForm.convertToEmployee.map(employeePojo);
        employeeHibernateService.update(employee);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Redirect user to update page.
     */
    @GetMapping(value = Url.UPDATE_PAGE)
    public EmployeePojo showUpdatePageEmployee(@PathVariable Long id, @PathVariable Long deptId) {
        Employee employee = employeeHibernateService.findEntity(id);

        return FacadeForm.convertToEmployeePojo.map(employee);
    }

    /**
     * Remove employee by Id.
     */
    @DeleteMapping(value = Url.DELETE)
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        employeeHibernateService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}