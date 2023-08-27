package com.example.util.constants;

/**
 * Regex for input.
 */
public final class Regex {
    private Regex() { }

    /** Regex constant. */
    public static final String PHONE_REGEX = "^(\\s*)?(\\+)?([- _():=+]?\\d[- _():=+]?){10,14}(\\s*)?$";
}
