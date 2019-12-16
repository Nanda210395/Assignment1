package com.zucitech.consoleapp;

public class EmployeePojo
{
    private String emptype;
    private String firstname;
    private String lastname;
    private int id;
    private String hired_date;
    private double salary;

    public void setEmptype(String emptype) {
        this.emptype = emptype;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHired_date(String hired_date) {
        this.hired_date = hired_date;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmptype() {
        return emptype;
    }

    public String getFirstname() {
        return firstname;
    }

    public int getId() {
        return id;
    }

    public String getHired_date() {
        return hired_date;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return (id + " " + firstname + " " + lastname + " " + hired_date + " " + emptype + " " + salary);
    }
}
