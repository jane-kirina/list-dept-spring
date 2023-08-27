package com.example.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interface for implementing servlet commands.
 */
public interface ICommand {
    /**
     * This method reads a command from the request and processes it.
     * The result will be given as a page to forward to.
     *
     * @param request with information for processing
     * @param response with processed information from the request
     */
    void execute(HttpServletRequest request, HttpServletResponse response);

    /**
     * Processes the request and is redirected to another page with the information received from the request,
     * processes the request on the server side.
     *
     * @param request with information for processing
     * @param response with processed information from the request
     * @param path where to redirect the user after processing the data
     */
    default void redirect(HttpServletRequest request, HttpServletResponse response, String path) {
        try {
            response.sendRedirect(request.getContextPath() + path);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Processes the request and is redirected to another page without information from the request,
     * processes the request on the client side.
     *
     * @param request with information for processing
     * @param response with processed information from the request
     * @param path where to redirect the user after processing the data
     */
    default void forward(HttpServletRequest request, HttpServletResponse response, String path) {
        try {
            request.getServletContext().getRequestDispatcher(path).forward(request, response);
        } catch (ServletException | IOException exception) {
            exception.printStackTrace();
        }
    }
}
