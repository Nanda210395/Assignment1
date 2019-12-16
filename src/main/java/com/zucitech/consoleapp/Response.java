package com.zucitech.consoleapp;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {

    private List<EmployeePojo> employee=new ArrayList<EmployeePojo>();

    public List<EmployeePojo> getEmployee() {
        return employee;
    }

    public void setEmployee(List<EmployeePojo> employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "[employee=" + employee + "]";
    }
}
