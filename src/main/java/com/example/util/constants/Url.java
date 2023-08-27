package com.example.util.constants;

public final class Url {
    private Url(){}

    public static final String MAIN_DEPARTMENT = "/department";
    public static final String MAIN_EMPLOYEE = "/department/{deptId}/employee";

    public static final String UPDATE_DATA = "/update-data/{id}";
    public static final String UPDATE_PAGE = "/update/{id}";

    public static final String CREATE_PAGE = "/create";
    public static final String CREATE_DATA = "/create-data";

    public static final String DELETE = "/delete/{id}";
}
