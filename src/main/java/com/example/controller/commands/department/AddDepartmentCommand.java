package com.example.controller.commands.department;

import com.example.controller.ICommand;
import com.example.entity.Department;
import com.example.service.interf.hibernate.IDepartmentHibernateService;
import com.example.util.constants.Actions;
import com.example.util.constants.Paths;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Adds department to the database.
 */
public class AddDepartmentCommand implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(AddDepartmentCommand.class);
    private IDepartmentHibernateService departmentHibernateService;
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
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");

        LOGGER.info("Checking the data for validity");

        Department department = new Department(name, phone);

        List<ConstraintViolation> violations = validator.validate(department);

        if (!violations.isEmpty()) {
            LOGGER.warn("Data not validated: " + violations);
            request.setAttribute("violationsList", violations);

            request.setAttribute("name", name);
            request.setAttribute("phone", phone);

            forward(request, response, Paths.NEW_DEPT);
        } else {
            LOGGER.info("Data validated, add to database");
            if (departmentHibernateService.create(department)) {
                forward(request, response, Actions.ACTION_DEPT_LIST);
            } else {
                LOGGER.error("Department wasn't added to database: " + department);
                request.setAttribute("error", "The department was not added");
                forward(request, response, Paths.ERROR);
            }
        }
    }

    public void setDepartmentHibernateService(IDepartmentHibernateService departmentHibernateService) {
        this.departmentHibernateService = departmentHibernateService;
    }

    public void setValidator(Validator validator) {
        this.validator = validator;
    }
}
