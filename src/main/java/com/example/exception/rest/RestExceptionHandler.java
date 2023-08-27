package com.example.exception.rest;

import com.example.exception.database.AddInDatabaseException;
import com.example.exception.database.DeleteInDatabaseException;
import com.example.exception.database.UpdateDataInDatabaseException;
import com.example.util.constants.Parameters;
import com.example.util.constants.Paths;
import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Main handler for exception.
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger LOG = Logger.getLogger(RestExceptionHandler.class);

    /**
     * Total control - setup a model and return the view name yourself. Or
     * consider subclassing ExceptionHandlerExceptionResolver (see below).
     */
    @ExceptionHandler(Exception.class)
    public String handleError(HttpServletRequest request, Exception exception, Model model) {
        LOG.error("Request failed: " + request.getRequestURL() + System.lineSeparator() + exception, exception);

        model.addAttribute(Parameters.EXCEPTION, exception);
        model.addAttribute(Parameters.URL, request.getRequestURL());

        return Paths.ERROR;
    }

    @ExceptionHandler(AddInDatabaseException.class)
    public String handleAddInDatabaseException(HttpServletRequest request, Exception exception, Model model) {
        LOG.error("Request to add data failed: " + request.getRequestURL() + System.lineSeparator() + exception, exception);

        model.addAttribute(Parameters.EXCEPTION, exception);
        model.addAttribute(Parameters.ERROR, "We were unable to add new data.");
        model.addAttribute(Parameters.URL, request.getRequestURL());

        return Paths.ERROR;
    }

    @ExceptionHandler(UpdateDataInDatabaseException.class)
    public String handleUpdateDataInDatabaseException(HttpServletRequest request, Exception exception, Model model) {
        LOG.error("Request to update data failed: " + request.getRequestURL() + System.lineSeparator() + exception, exception);

        model.addAttribute(Parameters.EXCEPTION, exception);
        model.addAttribute(Parameters.ERROR, "We were unable to update data.");
        model.addAttribute(Parameters.URL, request.getRequestURL());

        return Paths.ERROR;
    }

    @ExceptionHandler(DeleteInDatabaseException.class)
    public String handleDeleteInDatabaseException(HttpServletRequest request, Exception exception, Model model) {
        LOG.error("Request to delete data failed: " + request.getRequestURL() + System.lineSeparator() + exception, exception);

        model.addAttribute(Parameters.EXCEPTION, exception);
        model.addAttribute(Parameters.ERROR, "We were unable to delete data.");
        model.addAttribute(Parameters.URL, request.getRequestURL());

        return Paths.ERROR;
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException exception, HttpHeaders headers,
                                                                   HttpStatus status, WebRequest request) {
        super.handleNoHandlerFoundException(exception, headers, status, request);
        List<String> details = new ArrayList<>();
        details.add(exception.getLocalizedMessage());

        return new ResponseEntity<>(new ErrorResponse("No Handler Found", details), status);
    }
}