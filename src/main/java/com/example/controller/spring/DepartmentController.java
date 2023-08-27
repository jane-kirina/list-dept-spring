package com.example.controller.spring;

import com.example.entity.Department;
import com.example.model.DepartmentPojo;
import com.example.model.FacadeForm;
import com.example.service.interf.hibernate.IDepartmentHibernateService;
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
 * Controller foe Department commands.
 */
@RestController
@RequestMapping(Url.MAIN_DEPARTMENT)
public class DepartmentController {
    private final IDepartmentHibernateService departmentHibernateService;
    private final Validator springValidator;

    @Autowired
    public DepartmentController(IDepartmentHibernateService departmentHibernateService, Validator springValidator) {
        this.departmentHibernateService = departmentHibernateService;
        this.springValidator = springValidator;
    }

    @InitBinder(Parameters.DEPARTMENT_POJO)
    public void customizeBinding(WebDataBinder binder) {
        binder.addValidators(springValidator);
    }

    /**
     * Return all departments.
     */
    @GetMapping
    public Set<DepartmentPojo> listDepartment() {
        Set<Department> departments = departmentHibernateService.allData();

        return departments.stream()
                .map(FacadeForm.convertToDepartmentPojo::map)
                .collect(Collectors.toSet());
    }

    /**
     * Create new department.
     */
    @PostMapping(value = Url.CREATE_DATA)
    public ResponseEntity<?> addDepartment(@Valid @RequestBody DepartmentPojo departmentPojo,
                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new ArrayList<>(bindingResult.getAllErrors()), HttpStatus.I_AM_A_TEAPOT);
        }
        Department department = FacadeForm.convertToDepartment.map(departmentPojo);
        departmentHibernateService.create(department);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Redirect user to add page.
     */
    @GetMapping(value = Url.CREATE_PAGE)
    public DepartmentPojo showAddPageDepartment() {
        return new DepartmentPojo();
    }

    /**
     * Update department.
     */
    @PutMapping(value = Url.UPDATE_DATA)
    public ResponseEntity<?> updateDepartment(@Valid @RequestBody DepartmentPojo departmentPojo,
                                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new ArrayList<>(bindingResult.getAllErrors()), HttpStatus.I_AM_A_TEAPOT);
        }
        Department department = FacadeForm.convertToDepartment.map(departmentPojo);
        departmentHibernateService.update(department);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Redirect user to update page.
     */
    @GetMapping(value = Url.UPDATE_PAGE)
    public DepartmentPojo showUpdatePageDepartment(@PathVariable Long id) {
        Department department = departmentHibernateService.findEntity(id);

        return FacadeForm.convertToDepartmentPojo.map(department);
    }

    /**
     * Remove department by Id.
     */
    @DeleteMapping(value =Url.DELETE)
    public ResponseEntity<?> deleteDepartment(@PathVariable Long id) {
        departmentHibernateService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}