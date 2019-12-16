package com.zucitech.consoleapp;

public enum EmployeeType {
    HOURLY("hourly employee"),
    SALARIED("salaried employee");

    private String value;

    EmployeeType(String empType){
        this.value = empType;
}

    public String getValue(){
        return value;
    }
}
