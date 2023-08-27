package com.example.controller.commands.department;

import com.example.controller.ICommand;
import com.example.entity.Department;
import com.example.service.interf.hibernate.IDepartmentHibernateService;
import com.example.util.constants.Paths;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * Finds all departments in the database.
 */
public class ListDepartmentsCommand implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(ListDepartmentsCommand.class);
    private IDepartmentHibernateService departmentHibernateService;

    /**
     * {@link ICommand#execute}
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Get all departments from database");
        Set<Department> departments = departmentHibernateService.allData();

        LOGGER.info("Set departments to the request");
        request.setAttribute("departments", departments);
        LOGGER.info("Forward the user to the list with departments page");
        forward(request, response, Paths.LIST_DEPT);
    }

    public void setDepartmentHibernateService(IDepartmentHibernateService departmentHibernateService) {
        this.departmentHibernateService = departmentHibernateService;
    }
}
