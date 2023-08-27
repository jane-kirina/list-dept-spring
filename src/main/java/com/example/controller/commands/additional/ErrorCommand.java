package com.example.controller.commands.additional;

import com.example.controller.ICommand;
import com.example.util.constants.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Error page.
 */
public class ErrorCommand implements ICommand {

    /**
     * {@link ICommand#execute}
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        forward(request, response, Paths.ERROR);
    }
}
