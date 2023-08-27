package com.example.exception.rest;

import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Objects;

/**
 * Entity for rest Exception Handler.
 */
public class ErrorResponse {
    private HttpStatus status;
    private String message;
    private List<String> details;

    public ErrorResponse() {
    }

    public ErrorResponse(String message, List<String> details) {
        this.message = message;
        this.details = details;
    }

    public ErrorResponse(HttpStatus status, String message, List<String> details) {
        this.status = status;
        this.message = message;
        this.details = details;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        ErrorResponse that = (ErrorResponse) object;
        return status == that.status
                && Objects.equals(message, that.message)
                && Objects.equals(details, that.details);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, message, details);
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", details=" + details +
                '}';
    }
}
