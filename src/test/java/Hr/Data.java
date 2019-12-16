package Hr;

import org.testng.annotations.DataProvider;
import java.time.LocalDate;

public class Data {
    int id;
    String firstname;
    String lastname;
    LocalDate hired_date;
    int rate;

    /*Provide hourly employee data to add employee with rate*/
    @DataProvider(name="hourly_emp_data_with_rate")
    public Object[][] hourlyEmpDataWithrate(){
        hired_date= LocalDate.now();
        return new Object[][]{{0,"emp1","s",hired_date,0},{3,"emp2","a",hired_date,150},{4,"emp3","a",hired_date,600}};
    }

    /*Provide hourly employee data to add employee without rate*/
    @DataProvider(name="hourly_emp_data_without_rate")
    public Object[][] hourlyEmpDataWithoutRate(){
        hired_date= LocalDate.now();
        return new Object[][]{{0,"emp1","s",hired_date},{3,"emp2","a",hired_date},{4,"emp3","a",hired_date}};
    }

    /*Provide salaried employee data to add employee with experience*/
    @DataProvider(name="salaried_emp_data_with_exprience")
    public Object[][] salariedEmpDataWithExp(){
        hired_date=LocalDate.now();
        return new Object[][]{{0,"emp4","s",hired_date,0},{5,"emp5","a",hired_date,3},{6,"emp5","a",hired_date,50}};
    }

    /*Provide salaried employee data to add employee without experience*/
    @DataProvider(name="salaried_emp_data_without_exprience")
    public Object[][] salariedEmpDataWithoutExp(){
        hired_date=LocalDate.now();
        return new Object[][]{{0,"emp4","s",hired_date},{5,"emp5","a",hired_date},{6,"emp5","a",hired_date}};
    }

    /*Provide id to remove employee or display employee details or display salary of the employee */
    @DataProvider(name="test_id")
    public Object[][] useCase() {
        return new Object[][]{{0},{2}};
    }

    /*Provide salaried employee data to add employee*/
    @DataProvider(name="salaried_emp_data")
    public Object[][] salariedEmpData(){
        LocalDate hired_date = LocalDate.now();
        return new Object[][]{{0,"emp4","s",hired_date,0},{4,"emp5","a",hired_date,0},{5,"emp5","a",hired_date,0}};
    }

    /*Provide hourly employee data to add employee*/
    @DataProvider(name="hourly_emp_data")
    public Object[][] hourlyEmpData(){
        LocalDate hired_date = LocalDate.now();
        return new Object[][]{{0,"emp1","s",hired_date,0},{2,"emp2","a",hired_date,0},{3,"emp3","a",hired_date,0}};
    }
}
