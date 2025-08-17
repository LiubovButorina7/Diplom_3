package ru.practikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.practikum.util.Constants;
import java.time.Duration;


public class RegistrationPage {
    protected final WebDriver driver;

    private final By nameField = By.xpath(".//label[text() = 'Имя']/parent::*/input");
    private final By emailField = By.xpath(".//label[text() = 'Email']/parent::*/input");
    private final By passwordField = By.xpath(".//label[text() = 'Пароль']/parent::*/input");
    private final By registerButton = By.cssSelector(".button_button__33qZ0");
    private final By passwordFieldError = By.xpath(".//p[text() = 'Некорректный пароль']");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Enter value in Name field")
    public void setUserName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    @Step("Enter value in Email field")
    public void setUserEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Enter value in Password field")
    public void setUserPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Click on Register Button")
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    @Step("Accept email, password and name values")
    public void enterRegistrationData(String name, String email, String password) {
        setUserName(name);
        setUserEmail(email);
        setUserPassword(password);
        clickRegisterButton();
    }

    @Step("Check error message under password field")
    public void checkErrorMessageIsDisplayed() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(Constants.WAITING_SECONDS)).until(ExpectedConditions.visibilityOfElementLocated(passwordFieldError));
        } catch (Exception e) {
            throw new RuntimeException("Элемент с сообщением об ошибке в поле 'Пароль' не найден: " + e.getMessage());
        }
    }
}
