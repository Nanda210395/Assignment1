package com.zucitech.consoleapp;

import com.sun.tools.corba.se.idl.InvalidArgument;
import org.json.simple.parser.ParseException;
import org.testng.log4testng.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;

public class UseHr {
    static HR hr=new HR();
    final static Logger LOGGER = Logger.getLogger(UseHr.class);

    /*Display the Menu to do the required operation*/
    public static void viewMenu() {
        System.out.println("-------------------------------------------------------------");
        System.out.println("||1.Add the details of Employee                            ||");
        System.out.println("||2.Remove/layoff the Employee                             ||");
        System.out.println("||3.Enter the Employee Id to Print employee details        ||");
        System.out.println("||4.Enter the Employee Id to know the salary               ||");
        System.out.println("||5.Print all hourly employees                             ||");
        System.out.println("||6.Print all salaried employees                           ||");
        System.out.println("||7.Print all the employees details                        ||");
        System.out.println("||8.Exit from Application                                  ||");
        System.out.println("-------------------------------------------------------------");
        System.out.println("Enter the valid number to do the above operation");
    }

    /*Main method to execute the Application*/
    public static void main(String[] args) throws InvalidArgument, IOException, InputMismatchException, ParseException {
        try{
            hr.runHr();
        } catch (NullPointerException n) {
            LOGGER.error("No Data");
            hr.runHr();
        } catch (InputMismatchException e) {
            LOGGER.error("Enter the valid input value");
            hr.runHr();
        } catch (FileNotFoundException f){
            LOGGER.error("File is not exist");
        } catch (IOException e) {
            LOGGER.error("Enter the valid data");
            hr.runHr();
        } catch (IllegalArgumentException i) {
            LOGGER.error("Enter the valid input");
            hr.runHr();
        }
    }
}
