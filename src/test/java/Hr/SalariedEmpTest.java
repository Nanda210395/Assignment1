package Hr;

import com.zucitech.consoleapp.HR;
import com.zucitech.consoleapp.SalariedEmp;
import com.zucitech.consoleapp.UseHr;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.LocalDate;

public class SalariedEmpTest {
    String path=System.getProperty("user.dir");
    String filePath=path+"/src/main/resources/Json.json";
    HR hr=new HR();

    /*execute before test method*/
    @BeforeTest
    public void setUp() {
        UseHr.viewMenu();
    }

    /*execute after test method*/
    @AfterTest
    public void afterTest(){
        System.exit(0);
    }

    /*add salaried employee test method*/
    @Test(dataProvider = "salaried_emp_data",dataProviderClass = Data.class)
    public void addSalariedEmp(int id, String firstname, String lastname, LocalDate hired_date,int experience) throws IOException, ParseException {
        SalariedEmp emp = new SalariedEmp(id, firstname, lastname, hired_date, experience);
        hr.addEmp(emp,filePath);
    }
}
