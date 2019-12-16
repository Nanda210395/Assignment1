package com.zucitech.consoleapp;

import java.time.LocalDate;

abstract public class Employee {

    public int id;
    public String firstname;
    public String lastname;
    public LocalDate hired_date;
    public EmployeeType emptype;

    /*get the employee Id*/
    public int getId() {
        return id;
    }

    /*get the employee first name */
    public String getFirstname() {
        return firstname;
    }

    /*get the employee last name*/
    public String getLastname() {
        return lastname;
    }

    /*get the employee hired_date*/
    public LocalDate getHired_date() {
        return hired_date;
    }

    /*get the employee Employee Type*/
    public EmployeeType getEmptype() {
        return emptype;
    }

    public Employee(int id, String firstname, String lastname,  LocalDate hired_date, EmployeeType emptype) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.hired_date = hired_date;
        this.emptype = emptype;
    }

    /*Abstract method to getSalary method for Hourly and Salaried employee */
    abstract public double getSalary();

    /*Abstract method to getExprience method for Salaried employee*/
    abstract public int getExperience();

    /*Abstract method to getRate method for Hourly employee*/
    abstract public int getRate();

    @Override
    public String toString() {
        return (this.id + " " + this.firstname + " " + this.lastname + " " + this.hired_date + " " + this.emptype);
    }
}