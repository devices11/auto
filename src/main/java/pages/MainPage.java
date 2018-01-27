package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {

    @FindBy(xpath = "//ul[contains(@aria-labelledby,'alt-menu-mid__header4')]")
    WebElement mainMenu;

    @FindBy(xpath = "//li[5]//div[contains(@class,'alt-menu-collapser__hidder')]")
    WebElement subMenu;

    public MainPage (WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void selectMainMenu(String menuItem){
        mainMenu.findElement(By.xpath(".//li/a[contains(@aria-label,'"+menuItem+"')]")).click();
    }

    public void selectSubMenu(String menuItem){
        subMenu.findElement(By.xpath(".//div/div/a[contains(text(),'"+menuItem+"')]")).click();
    }
}
