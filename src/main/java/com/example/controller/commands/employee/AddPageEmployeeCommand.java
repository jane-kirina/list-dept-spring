package com.example.controller.commands.employee;

import com.example.controller.ICommand;
import com.example.util.constants.Paths;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Go to add-page Employee.
 */
public class AddPageEmployeeCommand implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(AddPageEmployeeCommand.class);

    /**
     * {@link ICommand#execute}
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Forward the user to the add employee page");
        forward(request, response, Paths.NEW_EMPL);
    }
}
