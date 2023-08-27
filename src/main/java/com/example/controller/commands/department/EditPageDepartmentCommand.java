package com.example.controller.commands.department;

import com.example.controller.ICommand;
import com.example.entity.Department;
import com.example.service.interf.hibernate.IDepartmentHibernateService;
import com.example.util.constants.Paths;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Go to edit-page Department.
 */
public class EditPageDepartmentCommand implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(EditPageDepartmentCommand.class);
    private IDepartmentHibernateService departmentHibernateService;

    /**
     * {@link ICommand#execute}
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Get id of department from request");
        Long deptId = Long.valueOf(request.getParameter("deptId"));
        LOGGER.info("Get department from database");
        Department dept = departmentHibernateService.findEntity(deptId);

        LOGGER.info("Set data of department to request");
        request.setAttribute("name", dept.getName());
        request.setAttribute("id", dept.getId());
        request.setAttribute("phone", dept.getNumber());

        LOGGER.info("Forward the user to the edit department page");
        forward(request, response, Paths.EDIT_DEPT);
    }

    public void setDepartmentHibernateService(IDepartmentHibernateService departmentHibernateService) {
        this.departmentHibernateService = departmentHibernateService;
    }
}
