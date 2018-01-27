package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class Formalize extends BasePage{

    @FindBy(xpath = "//*[contains(text(),'Оформление')]")
    WebElement title;

    @FindBy(name = "insured0_surname")
    WebElement insured0_surname;

    @FindBy(name = "insured0_name")
    WebElement insured0_name;

    @FindBy(name = "insured0_birthDate")
    WebElement insured0_birthDate;

    @FindBy(name = "surname")
    WebElement surname;

    @FindBy(name = "name")
    WebElement name;

    @FindBy(name = "middlename")
    WebElement middleName;

    @FindBy(name = "birthDate")
    WebElement birthDate;

    @FindBy(name = "passport_series")
    WebElement passport_series;

    @FindBy(name = "passport_number")
    WebElement passport_number;

    @FindBy(name = "issueDate")
    WebElement issueDate;

    @FindBy(name = "issuePlace")
    WebElement issuePlace;

    @FindBy(xpath = "//div[contains(@class,'b-button-block-center')]")
    public WebElement sendButton;

    @FindBy(xpath = "//section[contains(@class,'b-form-section')]")
    WebElement fieldErrorMessage;


    public Formalize(WebDriver driver) {
        PageFactory.initElements(driver, this);
        (new WebDriverWait(driver, 10))
            .until(ExpectedConditions.visibilityOf(title));
    }

    public void fillField(String fieldName, String value){
        switch (fieldName){
            case  "Фамилия застрахованного":
                fillField(insured0_surname, value);
                break;
            case  "Имя застрахованного":
                fillField(insured0_name, value);
                break;
            case  "Дата рождения застрахованного":
                fillField(insured0_birthDate, value);
                break;
            case  "Фамилия":
                fillField(surname, value);
                break;
            case  "Имя":
                fillField(name, value);
                break;
            case  "Отчество":
                fillField(middleName, value);
                break;
            case  "Дата рождения":
                fillField(birthDate, value);
                break;
            case  "Серия паспорта":
                fillField(passport_series, value);
                break;
            case  "Номер паспорта":
                fillField(passport_number, value);
                break;
            case  "Дата выдачи":
                fillField(issueDate, value);
                break;
            case  "Кем выдан":
                fillField(issuePlace, value);
                break;
        }
    }



    public void selectButton (String menuItem) {
        sendButton.findElement(By.xpath(".//*[contains(text(),'"+menuItem+"')]")).click();
    }

    public void checkFieldErrorMessage(String errorMessage){
        assertEquals(""+errorMessage+"",
                fieldErrorMessage.findElement(By.xpath("//*[contains(text(),'"+errorMessage+"')]")).getText());
    }
}
