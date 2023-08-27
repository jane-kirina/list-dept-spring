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
 * Handles the editing process employee.
 */
public class EditEmployeeCommand implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(EditEmployeeCommand.class);
    private IEmployeeHibernateService employeeHibernateService;
    private Validator validator;

    /**
     * {@link ICommand#execute}
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        Long id = Long.valueOf(request.getParameter("emplId"));

        Employee empl = employeeHibernateService.findEntity(id);

        String name = request.getParameter("name");
        String email = request.getParameter("email");

        String yearsWorkingStr = request.getParameter("yearsWorking");
        String birthDateStr = request.getParameter("dateOfBirth");
        String deptId = request.getParameter("deptId");

        LOGGER.info("Checking the data for validity");

        Department department = new Department();
        department.setId(Long.valueOf(deptId));

        LocalDate dateOfBirth = Validation.getValidDate(birthDateStr) != null ? LocalDate.parse(birthDateStr) : null;
        Integer yearsWorking = Ints.tryParse(yearsWorkingStr);

        Employee employee = new Employee(id, name, dateOfBirth, yearsWorking, email, department);

        List<ConstraintViolation> violations = validator.validate(employee);

        if (!violations.isEmpty()) {
            LOGGER.warn("Data not validated: " + violations);
            request.setAttribute("violationsList", violations);

            request.setAttribute("emplId", empl.getId());
            request.setAttribute("name", name);
            request.setAttribute("email", email);
            request.setAttribute("birthDate", birthDateStr);
            request.setAttribute("yearsWorking", yearsWorkingStr);
            request.setAttribute("deptId", deptId);

            forward(request, response, Paths.EDIT_EMPL);
        } else {
            LOGGER.info("Data validated, edit in database");
            if (employeeHibernateService.update(employee)) {
                LOGGER.info("Employee was edited to database");
                forward(request, response, Actions.ACTION_EMPLOYEE_LIST);
            } else {
                LOGGER.error("Employee wasn't edited to database: " + employee);
                request.setAttribute("error", "The employee was not edited");
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
