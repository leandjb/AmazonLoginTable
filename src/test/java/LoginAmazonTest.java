import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;

import java.io.FileReader;
import java.io.IOException;

public class LoginAmazonTest {

    private WebDriver driver;
    String CSV_PATH = "src\\test\\resources\\TestData.csv";

    String[] csvCell;
    private CSVReader csvReader;

    @Before
    public void setup() {

        System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromeDriver_v94_win32.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://amazon.com");
    }

    @Test
    public void dataRead_CSV() throws IOException, CsvValidationException {
        //Create an object of CSVReader
        csvReader = new CSVReader(new FileReader(CSV_PATH));

        driver.findElement(By.id("nav-link-accountList")).click();
        driver.findElement(By.id("createAccountSubmit")).click();

        //You can use while loop like below, It will be executed until the last line in CSV used.
        while ((csvCell = csvReader.readNext()) != null) {
            String CustomerName = csvCell[0];
            String CustomerEmail = csvCell[1];
            String CustomerPassword = csvCell[2];
            String CustomerConfirmPassword = csvCell[3];
            driver.findElement(By.id("ap_customer_name")).clear();
            driver.findElement(By.id("ap_customer_name")).sendKeys(CustomerName);
            driver.findElement(By.id("ap_email")).clear();
            driver.findElement(By.id("ap_email")).sendKeys(CustomerEmail);
            driver.findElement(By.id("ap_password")).clear();
            driver.findElement(By.id("ap_password")).sendKeys(CustomerPassword);
            driver.findElement(By.id("ap_password_check")).clear();
            driver.findElement(By.id("ap_password_check")).sendKeys(CustomerConfirmPassword);
            driver.findElement(By.id("continue")).click();
        }
    }

    @AfterTest
    public void exit() {
        driver.close();
        driver.quit();
    }



}
