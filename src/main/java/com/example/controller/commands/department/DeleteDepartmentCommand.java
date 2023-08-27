package com.example.controller.commands.department;

import com.example.controller.ICommand;
import com.example.service.interf.hibernate.IDepartmentHibernateService;
import com.example.service.interf.hibernate.IEmployeeHibernateService;
import com.example.util.constants.Actions;
import com.example.util.constants.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Deletes department.
 */
public class DeleteDepartmentCommand implements ICommand {
    private IDepartmentHibernateService departmentHibernateService;
    private IEmployeeHibernateService employeeHibernateService;

    /**
     * If the department has employees, it first deletes employees, then the department itself.
     * {@link ICommand#execute}
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        Long deptId = Long.valueOf(request.getParameter("deptId"));

        employeeHibernateService.deleteEmployeesFromDept(deptId);
        if (departmentHibernateService.delete(deptId)){
            redirect(request, response, Actions.ACTION_DEPT_LIST);
        } else {
            request.setAttribute("error", "The department was not deleted ");
            forward(request, response, Paths.ERROR);
        }
    }

    public void setDepartmentHibernateService(IDepartmentHibernateService departmentHibernateService) {
        this.departmentHibernateService = departmentHibernateService;
    }

    public void setEmployeeHibernateService(IEmployeeHibernateService employeeHibernateService) {
        this.employeeHibernateService = employeeHibernateService;
    }
}
