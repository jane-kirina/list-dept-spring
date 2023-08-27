package com.example.controller;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * The class for handling the input action and starting the method.
 */
public class CommandFactory {
    private static final Logger LOGGER = Logger.getLogger(CommandFactory.class);
    private final Map<String, ICommand> commands;
    private final ICommand defaultCommand;

    public CommandFactory(Map<String, ICommand> commands, ICommand defaultCommand) {
        this.commands = commands;
        this.defaultCommand = defaultCommand;
    }

    public ICommand getCommand(HttpServletRequest request) {
        LOGGER.info("Get parameter from request");
        String action = request.getParameter("action");
        return commands.getOrDefault(action, defaultCommand);
    }
}
