package Hr;

import com.zucitech.consoleapp.HR;
import com.zucitech.consoleapp.HourlyEmp;
import com.zucitech.consoleapp.UseHr;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.LocalDate;

public class HourlyEmpTest {
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

    /*add hourly employee*/
    @Test(dataProvider ="hourly_emp_data",dataProviderClass = Data.class)
    public void hourlyEmpWithRate(int id, String firstname, String lastname, LocalDate hired_date, int rate) throws IOException, ParseException {
        HourlyEmp emp = new HourlyEmp(id, firstname, lastname, hired_date, rate);
        hr.addEmp(emp,filePath);
    }
}
