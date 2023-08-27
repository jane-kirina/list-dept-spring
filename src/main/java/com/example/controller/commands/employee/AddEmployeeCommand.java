package com.example.controller.commands.employee;

import com.example.controller.ICommand;
import com.example.entity.Department;
import com.example.entity.Employee;
import com.example.service.interf.hibernate.IEmployeeHibernateService;
import com.example.util.constants.Actions;
import com.example.util.constants.Paths;
import com.example.util.validation.Validation;
import com.google.common.primitives.Ints;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;

/**
 * Adds employee to the database.
 */
public class AddEmployeeCommand implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(AddEmployeeCommand.class);
    private IEmployeeHibernateService employeeHibernateService;
    private Validator validator;

    /**
     * Checks the entered data, if the data was entered incorrectly adds an error message to the list.
     * At the end of the method, checks if the list with errors empty.
     * If yes - after adding a new employee, redirects the user to the list of employees.
     * If not, it returns to the add page and displays errors.
     *
     * {@link ICommand#execute}
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Get data from request about new employee");
        String name = request.getParameter("name");
        String yearsWorkingStr = request.getParameter("yearsWorking");
        String email = request.getParameter("email");
        String birthDateStr = request.getParameter("dateOfBirth");

        LOGGER.info("Get id dept from session");
        Long deptId = (Long) request.getSession().getAttribute("deptId");

        LOGGER.info("Checking the data for validity");

        Department dept = new Department();
        dept.setId(deptId);

        LocalDate dateOfBirth = Validation.getValidDate(birthDateStr) != null ? LocalDate.parse(birthDateStr) : null;
        Integer yearsWorking = Ints.tryParse(yearsWorkingStr);

        Employee employee = new Employee(name, dateOfBirth, yearsWorking, email, dept);

        List<ConstraintViolation> violations = validator.validate(employee);

        if (!violations.isEmpty()) {
            LOGGER.warn("Data not validated: " + violations);
            request.setAttribute("violationsList", violations);

            request.setAttribute("name", name);
            request.setAttribute("yearsWorking", yearsWorkingStr);
            request.setAttribute("email", email);
            request.setAttribute("birthDate", dateOfBirth);

            forward(request, response, Paths.NEW_EMPL);
        } else {
            LOGGER.info("Data validated, add to database");
            if (employeeHibernateService.create(employee)) {
                forward(request, response, Actions.ACTION_EMPLOYEE_LIST);
            } else {
                LOGGER.error("Employee wasn't added to database: " + employee);
                request.setAttribute("error", "The employee was not added");
                forward(request, response, Paths.ERROR);
            }
        }
    }

    public void setEmployeeHibernateService(IEmployeeHibernateService employeeHibernateService) {
        this.employeeHibernateService = employeeHibernateService;
    }

    public void setValidator(Validator validator) {
        this.validator = validator;
    }
}

