package com.example.controller.commands.employee;

import com.example.controller.ICommand;
import com.example.entity.Employee;
import com.example.service.interf.hibernate.IEmployeeHibernateService;
import com.example.util.constants.Paths;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Go to edit-page Department.
 */
public class EditPageEmployeeCommand implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(EditPageEmployeeCommand.class);
    private IEmployeeHibernateService employeeHibernateService;

    /**
     * {@link ICommand#execute}
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Get id of employee from request");
        Long emplId = Long.valueOf(request.getParameter("emplId"));
        LOGGER.info("Get employee from database");
        Employee employee = employeeHibernateService.findEntity(emplId);

        LOGGER.info("Set data of employee to request");
        request.setAttribute("emplId", employee.getId());
        request.setAttribute("name", employee.getName());
        request.setAttribute("email", employee.getEmail());
        request.setAttribute("birthDate", employee.getBirthDate());
        request.setAttribute("yearsWorking", employee.getYearsWorking());
        request.setAttribute("idDept", employee.getDepartment().getId());

        LOGGER.info("Forward the user to the edit employee page");
        forward(request, response, Paths.EDIT_EMPL);
    }

    public void setEmployeeHibernateService(IEmployeeHibernateService employeeHibernateService) {
        this.employeeHibernateService = employeeHibernateService;
    }
}
