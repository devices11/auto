
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.ChoicePolicy;
import pages.MainPage;
import pages.Formalize;
import pages.TravelInsurancePage;

import static org.junit.Assert.*;

public class InsuranceTest extends BaseTest{


    @Test
    public void testInsurance() {
        MainPage mainPage = new MainPage(driver);
        mainPage.selectMainMenu("Застраховать себя");
        mainPage.selectSubMenu("Страхование путешественников");

        Wait<WebDriver> wait = new WebDriverWait(driver, 5, 1000);

        WebElement title = driver.findElement(By.xpath("//div[@class='sbrf-rich-outer']/h1"));
        wait.until(ExpectedConditions.visibilityOf(title));
        assertEquals("Страхование путешественников", title.getText());


        TravelInsurancePage travelInsurancePage = new TravelInsurancePage(driver);


        travelInsurancePage.sendButton("img[contains(@src,'id=f6c836e1-5c5c-4367-b0d0-bbfb96be9c53')]");
        new TravelInsurancePage(driver).sendButton.click();

        nextTab();

        ChoicePolicy choicePolicy = new ChoicePolicy(driver);
        choicePolicy.selectSetInsurance("Минимальная");
        choicePolicy.selectButtonFormalize("Оформить");

        Formalize formalize = new Formalize(driver);
        formalize.fillField("Фамилия застрахованного","Ivanov");
        formalize.fillField("Имя застрахованного","Ivan");
        formalize.fillField("Дата рождения застрахованного","15.12.1985");
        formalize.fillField("Фамилия","Иванов");
        formalize.fillField("Имя","Иван");
        formalize.fillField("Отчество","Иванович");
        driver.findElement(By.xpath("//*[@name='birthDate']")).click();
        formalize.fillField("Дата рождения","15.12.1985");
        formalize.fillField("Серия паспорта","5499");
        formalize.fillField("Номер паспорта","547852");
        formalize.fillField("Дата выдачи","16.12.2003");
        formalize.fillField("Кем выдан","Отделением УФМС по г.Москва");

        formalize.selectButton("Продолжить");

        formalize.checkFieldErrorMessage("Заполнены не все обязательные поля");
    }

}