package com.example.controller.commands.employee;

import com.example.controller.ICommand;
import com.example.service.interf.hibernate.IEmployeeHibernateService;
import com.example.util.constants.Actions;
import com.example.util.constants.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Deletes employee.
 */
public class DeleteEmployeeCommand implements ICommand {
    private IEmployeeHibernateService employeeHibernateService;

    /**
     * {@link ICommand#execute}
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        Long emplId = Long.valueOf(request.getParameter("emplId"));

        if (employeeHibernateService.delete(emplId)) {
            redirect(request, response, Actions.ACTION_EMPLOYEE_LIST);
        } else {
            request.setAttribute("error", "The employee was not deleted ");
            forward(request, response, Paths.ERROR);
        }
    }

    public void setEmployeeHibernateService(IEmployeeHibernateService employeeHibernateService) {
        this.employeeHibernateService = employeeHibernateService;
    }
}
