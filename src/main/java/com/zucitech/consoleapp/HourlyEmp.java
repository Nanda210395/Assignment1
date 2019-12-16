package com.zucitech.consoleapp;

import java.time.LocalDate;
import java.util.InputMismatchException;

public class HourlyEmp extends Employee {

    protected static int rate;
    final int default_rate_per_hour = 100;
    final int default_hours_per_month = 110;
    private double salary;

    /*constructor for Hourly Employee, with the HR defined Rate for employee*/
    public HourlyEmp(int id, String firstname, String lastname, LocalDate hired_date, int rate)throws IllegalArgumentException,InputMismatchException,NullPointerException {
        super(id, firstname, lastname, hired_date, EmployeeType.HOURLY);
        this.rate = rate;
        setSalary();
    }

    /*constructor for Hourly Employee, with the Default Rate for employee*/
    public HourlyEmp(int id, String firstname, String lastname,  LocalDate hired_date)throws IllegalArgumentException,InputMismatchException,NullPointerException {
        super(id, firstname, lastname, hired_date, EmployeeType.HOURLY);
        rate = default_rate_per_hour;
        setSalary();
    }

    /*get the salary of employee*/
    public double getSalary()
    {
        return salary;
    }

    /*set the salary of employee by using rate and hours*/
    public void setSalary()throws NullPointerException{
            salary = rate * default_hours_per_month;
            if(salary==0){
                throw new NullPointerException("Salary can not be zero");
            }
    }

    @Override
    public int getExperience() {
        return 0;
    }

    @Override
    public int getRate() {
        return rate;
    }

    @Override
    public String toString() {
        return (this.id + " " + this.firstname + " " + this.lastname + " " + this.hired_date + " " + this.emptype + " " + this.rate);
    }

}
