package com.zucitech.consoleapp;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.InputMismatchException;

public class SalariedEmp extends Employee {
    final double defaultSalary = 30000;
    int experience;
    private double salary;

    /*constructor for Hourly Employee, with the HR defined experience for employee*/
    public SalariedEmp(int id, String firstname, String lastname, LocalDate hired_date, int experience)throws IllegalArgumentException, IOException, ParseException, InputMismatchException,NullPointerException {
        super(id, firstname, lastname, hired_date,EmployeeType.SALARIED);
        this.experience = experience;
        setSalary();
    }

    /*constructor for Hourly Employee, with the zero year experience of employee*/
    public SalariedEmp(int id, String firstname, String lastname,  LocalDate hired_date)throws IllegalArgumentException, IOException, ParseException, InputMismatchException,NullPointerException {
        super(id, firstname, lastname, hired_date,EmployeeType.SALARIED);
        experience = 0;
        setSalary();
    }

    /*get the salary of employee*/
    public double getSalary() {
        return salary;
    }

    /*set the salary of employee by using experience*/
    public void setSalary() throws NullPointerException{
            salary = defaultSalary + (0.1 * experience * defaultSalary);
            if(salary==0){
                throw new NullPointerException("Salary can not zero");
            }
    }

    @Override
    public String toString() {
        return (this.id + " " + this.firstname + " " + this.lastname + " " + this.hired_date + " " + this.emptype + " " + this.experience);
    }

    @Override
    public int getExperience() {
        return experience;
    }

    @Override
    public int getRate() {
        return 0;
    }

}
