package com.zucitech.consoleapp;

import com.sun.tools.corba.se.idl.InvalidArgument;
import org.json.simple.parser.ParseException;
import org.testng.SkipException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class HR {
     int id;
     String firstname;
     String lastname;
     LocalDate hired_date;
     int experience;
     int rate;
     int operation;
     Scanner scan;
     boolean application=true;
     public List<Employee> listOfEmp = new ArrayList();
     String path=System.getProperty("user.dir");
     String filePath=path+"/src/main/resources/Json.json";

    /*Run the HR application to do different operations as defined in the Menu*/
    public void runHr() throws InvalidArgument, IOException, ParseException,InputMismatchException{
        while(application) {
            scan = new Scanner(System.in);
                UseHr.viewMenu();
                operation = scan.nextInt();
                switch (operation){
                    case 1:
                        Employee emp=takeInputsFromHR();
                        addEmp(emp,filePath);
                        break;
                    case 2:
                        System.out.println("Enter the Id of employee to remove");
                        int enteredEmpId = scan.nextInt();
                        removeEmployee(enteredEmpId);
                        break;
                    case 3:
                        System.out.println("Enter the Id of employee to display the details");
                        int employeeId = scan.nextInt();
                        EmployeePojo employeeDetails=knowTheEmpDetailsBasedOnId(employeeId);
                        System.out.println(employeeDetails);
                        break;
                    case 4:
                        System.out.println("Enter the Id of employee to display the salary");
                        int empId = scan.nextInt();
                        double employeeSalary=knowTheEmpSalary(empId);
                        System.out.println(employeeSalary);
                        break;
                    case 5:
                        System.out.println("All HourlyBased employee information is:");
                        displayHourlyEmployee();
                        break;
                    case 6:
                        System.out.println("All Salaried employee information is");
                        displaySalariedEmployee();
                        break;
                    case 7:
                        List<EmployeePojo>empList=printAllDetailsOfEmp(filePath);
                        System.out.println(empList);
                        break;
                    case 8:
                        closeTheApplication();
                }
            }
    }

    public Employee takeInputsFromHR() throws InvalidArgument, IOException, ParseException {
        Employee emp;
        System.out.println("Employee Id");
        id = ++id;
        System.out.println(id);
        System.out.println("Enter the first name: ");
        firstname = scan.next();
        System.out.println("Enter the last name: ");
        lastname = scan.next();
        System.out.println("Hired_date of employee");
        hired_date=LocalDate.now();
        System.out.println(hired_date);
        System.out.println("Enter 1.For HourlyBased Employee"+'\n'+"Enter any number to take Employee as Salaried");
        int empType = scan.nextInt();
        if(empType==1){
            emp=hourlyEmp(id,firstname,lastname,hired_date, rate);
        }
        else{
            emp=salariedEmp(id,firstname,lastname,hired_date, experience);
        }
        return emp;
    }

    /*validate hourly employee details*/
    public Employee hourlyEmp(int id, String firstname, String lastname, LocalDate hired_date, int rate) throws InvalidArgument, IOException, ParseException {
        Employee emp;
        System.out.println(EmployeeType.HOURLY.getValue());
        System.out.println("Enter 1.to give rate of Employee"+'\n'+"enter any integer key to take default rate of employee:");
        int checkRate = scan.nextInt();
        if (checkRate == 1) {
            System.out.println("Enter the rate of employee");
            rate = scan.nextInt();
            emp=validateEmpDetails(new HourlyEmp(id, firstname, lastname, hired_date,rate));
        }
        else{
            emp=validateEmpDetails(new HourlyEmp(id, firstname, lastname, hired_date));
        }
        return emp;
    }

    /*validate salaried employee details*/
    public Employee salariedEmp(int id, String firstname, String lastname, LocalDate hired_date,int experience) throws InvalidArgument, IOException, ParseException {
        Employee emp;
        System.out.println(EmployeeType.SALARIED.getValue());
        System.out.println("Enter the 1 to add experience of employee"+'\n'+ "Enter any key to take Employee as Fresher");
        int experienceCheck = scan.nextInt();
        if (experienceCheck == 1) {
            System.out.println("Enter the total number of experience");
            experience = scan.nextInt();
            emp=validateEmpDetails(new SalariedEmp(id, firstname, lastname, hired_date,experience));
        }
        else {
            emp=validateEmpDetails(new SalariedEmp(id, firstname, lastname, hired_date));
        }
        return emp;
    }

    /*validate the Employees details*/
    public Employee validateEmpDetails(Employee emp) throws InvalidArgument {
            if (emp.getId() == 0) {
                throw new InvalidArgument("Employee Id can not be zero");
            }
            if (emp.getEmptype().equals(EmployeeType.HOURLY)){
                if (emp.getRate()!= 0 && emp.getRate() < 500 && emp.getRate() > 50) {
                } else {
                    throw new IllegalArgumentException("Enter valid rate and should be in the range (50 to 500)");
                }
            }
            else{
                if (emp.getExperience()>=0 && emp.getExperience() <= 10){
                }
                else {
                    throw new IllegalArgumentException("Enter valid experience and should be in the range(0 to 10)");
                }
            }
        return emp;
    }

    /*Add the Employees*/
    public void addEmp(Employee emp,String filePath) throws IOException, ParseException {
        listOfEmp.add(emp);
        jsonHelper.write(listOfEmp,filePath);
    }

    /*print all details of employees*/
    public List<EmployeePojo> printAllDetailsOfEmp(String filePath) throws IOException {
        List<EmployeePojo> empList=jsonHelper.read(filePath);
        return empList;
    }

    /*print hourly employees*/
    public void displayHourlyEmployee() throws IOException {
        List<EmployeePojo> empList=jsonHelper.read(filePath);
        for(int i=0;i<empList.size();i++){
            if(empList.get(i).getEmptype().equals(EmployeeType.HOURLY.toString())){
                System.out.println(empList.get(i));
            }
        }
    }

    /*print salaried employees*/
    public void displaySalariedEmployee() throws IOException {
        List<EmployeePojo> empList=jsonHelper.read(filePath);
        for(int i=0;i<empList.size();i++){
            System.out.println(empList.get(i).getEmptype());
            if(empList.get(i).getEmptype().equals(EmployeeType.SALARIED.toString())){
                System.out.println(empList.get(i));
            }
        }
    }

    /*print employee details using Id*/
    public EmployeePojo knowTheEmpDetailsBasedOnId(int employeeId) throws IOException, InputMismatchException, IllegalArgumentException, SkipException {
        EmployeePojo employeeDetails = null;
            if(employeeId==0){
                throw new IllegalArgumentException("Employee Id can not be zero");
            }
               List<EmployeePojo> empList=jsonHelper.read(filePath);
               for(int i=0;i<empList.size();i++){
                   if(empList.get(i).getId()==employeeId){
                       employeeDetails=empList.get(i);
                       break;
                   }
               }
        return employeeDetails;
    }

    /*print employee salary using Id*/
    public double knowTheEmpSalary(int employeeId) throws InputMismatchException,IOException {
        double employeeSalary = 0;
             if(employeeId==0){
                 throw new IllegalArgumentException("Employee Id can not be zero");
             }
             else {
                 List<EmployeePojo> empList=jsonHelper.read(filePath);
                 for(int i=0;i<empList.size();i++){
                     if(empList.get(i).getId()==employeeId){
                         employeeSalary=empList.get(i).getSalary();
                     }
                 }
             }
         return employeeSalary;
    }

    /*remove employee using Id*/
    public void removeEmployee(int enteredEmpId) throws IOException, ParseException,IllegalArgumentException {
            if(enteredEmpId==0){
                throw new IllegalArgumentException("Employee Id can not be zero");
            }
            else{
                List<EmployeePojo> empList=jsonHelper.read(filePath);
                for(int i=0;i<empList.size();i++){
                    if(empList.get(i).getId()==enteredEmpId){
                        empList.remove(i);
                        jsonHelper.remove(filePath,enteredEmpId);
                    }
                }
            }
    }

    /*close the application*/
    public void closeTheApplication() {
        System.out.println("Application has been closed");
        application=false;
    }

}


