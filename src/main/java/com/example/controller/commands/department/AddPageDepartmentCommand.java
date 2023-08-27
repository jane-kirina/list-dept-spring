package com.example.controller.commands.department;

import com.example.controller.ICommand;
import com.example.util.constants.Paths;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Go to add-page Department.
 */
public class AddPageDepartmentCommand implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(AddPageDepartmentCommand.class);

    /**
     * {@link ICommand#execute}
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Forward the user to the add department page");
        forward(request, response, Paths.NEW_DEPT);
    }
}
