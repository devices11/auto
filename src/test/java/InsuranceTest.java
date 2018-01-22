import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class InsuranceTest {
    WebDriver driver;
    String baseUrl;

    @Before
    public void beforeTest(){
        System.setProperty("webdriver.chrome.driver", "drv/chromedriver.exe");
        baseUrl = "http://www.sberbank.ru/ru/person";
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);
    }

    @Test
    public void testInsurance(){
        driver.findElement(By.xpath("//ul[contains(@aria-labelledby,'alt-menu-mid__header4')]/li/a[contains(@aria-label,'Застраховать себя ')]")).click();
        driver.findElement(By.xpath("//ul[contains(@aria-labelledby,'alt-menu-mid__header4')]/li/div/div/div//a[contains(text(),'Страхование путешественников')]")).click();

        Wait<WebDriver> wait = new WebDriverWait(driver, 5, 1000);

        WebElement title = driver.findElement(By.xpath("//div[@class='sbrf-rich-outer']/h1"));
        wait.until(ExpectedConditions.visibilityOf(title));
        assertEquals("Страхование путешественников", title.getText());
        driver.findElement(By.xpath("//div[contains(@class,'sbrf-rich-outer')]/p/a/img")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        ArrayList tabs2 = new ArrayList(driver.getWindowHandles());
        driver.switchTo().window((String) tabs2.get(1));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[contains(@ng-class,'b-form-prog-box')]//*[contains(text(),'Минимальная')]/..")))).click();

        driver.findElement(By.xpath("//*[contains(text(),'Минимальная')]")).click();
        driver.findElement(By.xpath("//span[contains(@class,'b-button-block-center')]/*[contains(text(),'Оформить')]")).click();

        fillField(By.name("insured0_surname"),"Ivanov");
        fillField(By.name("insured0_name"),"Ivan");
        fillField(By.name("insured0_birthDate"),"15.12.1985");
        fillField(By.name("surname"),"Иванов");
        fillField(By.name("name"),"Иван");
        fillField(By.name("middlename"),"Иванович");
        driver.findElement(By.xpath("//*[@name='birthDate']")).click();
        fillField(By.name("birthDate"),"15.12.1985");
        fillField(By.name("passport_series"),"1123");
        fillField(By.name("passport_number"),"222555");
        fillField(By.name("issueDate"),"16.12.2003");
        fillField(By.name("issuePlace"),"Отделением УФМС по г.Москва");

        driver.findElement(By.xpath("//*[contains(text(),'Продолжить')]")).click();

        assertEquals("Заполнены не все обязательные поля",
                driver.findElement(By.xpath("//div[contains(@ng-show,'tryNext && myForm.$invalid')]")).getText());

        assertEquals("Ivanov",
                driver.findElement(By.name("insured0_surname")).getAttribute("value"));

        assertEquals("Ivan",
                driver.findElement(By.name("insured0_name")).getAttribute("value"));

        assertEquals("15.12.1985",
                driver.findElement(By.name("insured0_birthDate")).getAttribute("value"));

        assertEquals("Иванов",
                driver.findElement(By.name("surname")).getAttribute("value"));

        assertEquals("Иван",
                driver.findElement(By.name("name")).getAttribute("value"));

        assertEquals("Иванович",
                driver.findElement(By.name("middlename")).getAttribute("value"));

        assertEquals("15.12.1985",
                driver.findElement(By.name("birthDate")).getAttribute("value"));

        assertEquals("1123",
                driver.findElement(By.name("passport_series")).getAttribute("value"));

        assertEquals("222555",
                driver.findElement(By.name("passport_number")).getAttribute("value"));

        assertEquals("16.12.2003",
                driver.findElement(By.name("issueDate")).getAttribute("value"));

        assertEquals("Отделением УФМС по г.Москва",
                driver.findElement(By.name("issuePlace")).getAttribute("value"));

    }

    public void fillField(By locator, String value){
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(value);
    }

    @After
    public void afterTest(){
        driver.quit();
    }
}