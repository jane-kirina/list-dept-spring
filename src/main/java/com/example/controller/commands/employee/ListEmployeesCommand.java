package com.example.controller.commands.employee;

import com.example.controller.ICommand;
import com.example.entity.Employee;
import com.example.service.interf.hibernate.IEmployeeHibernateService;
import com.example.util.constants.Parameters;
import com.example.util.constants.Paths;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Set;

/**
 * Finds all employees in the department in the database.
 */
public class ListEmployeesCommand implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(ListEmployeesCommand.class);
    private IEmployeeHibernateService employeeHibernateService;

    /**
     * When you first go to the page, deptId parameter from request is owned and the method adds it to the session
     * so that when you try to go back to the page, get the parameter from the session and find list with employees.
     * {@link ICommand#execute}
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        Long id;

        LOGGER.info("Get id of dept from request");
        String idStr = request.getParameter(Parameters.DEPT_ID);

        if (idStr == null) {
            LOGGER.debug("Id isn't in the request, we get it from the session");
            id = (Long) request.getSession().getAttribute(Parameters.DEPT_ID);
        } else {
            LOGGER.debug("Set id to the session");
            id = Long.parseLong(idStr);
            HttpSession session = request.getSession();
            session.setAttribute(Parameters.DEPT_ID, id);
        }

        LOGGER.info("Get all employees from dept and set it to the request");
        if (id != null) {
            Set<Employee> employees = employeeHibernateService.findFromDept(id);
            request.setAttribute("employees", employees);
            request.setAttribute(Parameters.DEPT_ID, id);

            forward(request, response, Paths.LIST_EMPL);
        } else {
            request.setAttribute("error", "Employee list not found");
            forward(request, response, Paths.ERROR);
        }
    }

    public void setEmployeeHibernateService(IEmployeeHibernateService employeeHibernateService) {
        this.employeeHibernateService = employeeHibernateService;
    }
}
