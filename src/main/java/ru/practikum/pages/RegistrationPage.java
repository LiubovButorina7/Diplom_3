package ru.practikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {
    protected final WebDriver driver;

    private final By nameField = By.xpath(".//label[text() = 'Имя']/parent::*/input");
    private final By emailField = By.xpath(".//label[text() = 'Email']/parent::*/input");
    private final By passwordField = By.xpath(".//label[text() = 'Пароль']/parent::*/input");
    private final By registerButton = By.cssSelector(".button_button__33qZ0");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Enter value in userName field")
    public void setUserName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    @Step("Enter value in userEmail field")
    public void setUserEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Enter value in userPasswordField field")
    public void setUserPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Click on Register Button")
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    @Step("Accept email, password, name values")
    public void enterRegistrationData(String name, String email, String password) {
        setUserName(name);
        setUserEmail(email);
        setUserPassword(password);
        clickRegisterButton();
    }
}
