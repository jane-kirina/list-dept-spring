package com.example.util.validation;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Validation {
    private Validation() {}

    public static LocalDate getValidDate(String dateString) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(false);

        LocalDate localDate = null;
        try {
            localDate = LocalDate.parse(dateString);
        } catch (DateTimeParseException exception) {
            exception.printStackTrace();
        }

        return localDate;
    }
}
