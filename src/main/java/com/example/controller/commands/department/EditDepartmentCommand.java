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
 * Handles the editing process department.
 */
public class EditDepartmentCommand implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(EditDepartmentCommand.class);
    private IDepartmentHibernateService departmentHibernateService;
    private Validator validator;

    /**
     * {@link ICommand#execute}
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        Long deptId = Long.valueOf(request.getParameter("deptId"));

        Department dept = departmentHibernateService.findEntity(deptId);

        String name = request.getParameter("name");
        String phone = request.getParameter("phone");

        LOGGER.info("Checking the data for validity");

        Department department = new Department(deptId, name, phone);

        List<ConstraintViolation> violations = validator.validate(department);

        if (!violations.isEmpty()) {
            LOGGER.warn("Data not validated: " + violations);
            request.setAttribute("violationsList", violations);

            request.setAttribute("id", dept.getId());
            request.setAttribute("name", name);
            request.setAttribute("phone", phone);

            forward(request, response, Paths.EDIT_DEPT);
        } else {
            LOGGER.info("Data validated, edit in database");
            if (departmentHibernateService.update(department)) {
                LOGGER.info("Department was edited to database");
                forward(request, response, Actions.ACTION_DEPT_LIST);
            } else {
                LOGGER.error("Department wasn't edited in database: " + department);
                request.setAttribute("error", "The department was not edited");
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
