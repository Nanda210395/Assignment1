package Hr;

import com.sun.tools.corba.se.idl.InvalidArgument;
import com.zucitech.consoleapp.Employee;
import com.zucitech.consoleapp.HR;
import com.zucitech.consoleapp.HourlyEmp;
import com.zucitech.consoleapp.SalariedEmp;
import com.zucitech.consoleapp.UseHr;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.InputMismatchException;

public class HrTest {
    static HR hr=new HR();
    String path=System.getProperty("user.dir");
    String filePath=path+"/src/main/resources/Json.json";

    @BeforeTest
    public void beforeTest(){
        UseHr.viewMenu();
    }

    /*execute after test method*/
    @AfterTest
    public void afterTest(){
        System.out.println("Application has been Closed");
        System.exit(0);
    }

    /*display emp details*/
    @Test(dataProvider = "test_id",dataProviderClass = Data.class)
    public void empDetailsTest(int id) throws IOException {
        System.out.println(hr.knowTheEmpDetailsBasedOnId(id));
    }

     /*remove employee*/
    @Test(dataProvider = "test_id", dataProviderClass = Data.class)
    public void removeEmp(int id) throws IOException, ParseException {
        hr.removeEmployee(id);
     }

     /*display salary*/
     @Test(dataProvider ="test_id", dataProviderClass = Data.class)
     public void displaySalaryOfEmp(int id) throws IOException {
         System.out.println(hr.knowTheEmpSalary(id));
     }

     /*add hourly employee test with rate method*/
     @Test(dataProvider ="hourly_emp_data_with_rate", dataProviderClass = Data.class)
     public void addHourlyEmp (int id, String firstname, String lastname, LocalDate hired_date, int rate) throws IllegalArgumentException, InputMismatchException, InvalidArgument, IOException, ParseException {
         Employee emp = hr.validateEmpDetails(new HourlyEmp(id, firstname, lastname, hired_date, rate));
         System.out.println(emp);
         hr.addEmp(emp,filePath);
     }

     /*add hourly employee test without rate method*/
    @Test(dataProvider ="hourly_emp_data_without_rate")
    public void addHourlyEmp(int id, String firstname, String lastname, LocalDate hired_date) throws InvalidArgument, IOException, ParseException {
        Employee emp = hr.validateEmpDetails(new HourlyEmp(id,firstname,lastname,hired_date));
        System.out.println(emp);
        hr.addEmp(emp,filePath);
    }

    /*add salaried employee with experience test method*/
    @Test(dataProvider = "salaried_emp_data_with_exprience", dataProviderClass = Data.class)
    public void addSalariedEmp(int id, String firstname, String lastname, LocalDate hired_date,int experience) throws IllegalArgumentException, InputMismatchException, InvalidArgument, IOException, ParseException {
        Employee emp = hr.validateEmpDetails(new SalariedEmp(id, firstname, lastname, hired_date,experience));
        System.out.println(emp);
        hr.addEmp(emp,filePath);
    }

    /*add salaried employee without experience test method*/
    @Test(dataProvider = "salaried_emp_data_without_exprience")
    public void addSalariedEmp(int id, String firstname, String lastname, LocalDate hired_date) throws InvalidArgument, IOException, ParseException {
        Employee emp = hr.validateEmpDetails(new SalariedEmp(id,firstname,lastname,hired_date));
        System.out.println(emp);
        hr.addEmp(emp,filePath);
    }
}
