package com.example.controller;

import org.apache.log4j.Logger;
import org.springframework.web.HttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Main servlet controller.
 */
public class Main implements HttpRequestHandler {
    private static final Logger LOGGER = Logger.getLogger(Main.class);
    private CommandFactory commandFactory;

    /**
     * Main method of this controller.
     * Accepts a request from the client, creates an instance of the class {@link CommandFactory}
     * and finds the required object in the internal map with commands
     * and calls the method {@link ICommand#execute(HttpServletRequest, HttpServletResponse)}
     * to process the request as needed.
     */
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) {
        ICommand command = commandFactory.getCommand(request);
        LOGGER.info("Execute the command: " + command.getClass().getName());
        command.execute(request, response);
    }

    public void setCommandFactory(CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
    }
}