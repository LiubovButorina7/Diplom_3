package ru.practikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.practikum.util.Constants;
import java.time.Duration;

public class RegisterPage {
    protected final WebDriver driver;

    private final By nameField = By.xpath(".//label[text() = 'Имя']/parent::*/input");
    private final By emailField = By.xpath(".//label[text() = 'Email']/parent::*/input");
    private final By passwordField = By.xpath(".//label[text() = 'Пароль']/parent::*/input");
    private final By registerButton = By.cssSelector(".button_button__33qZ0");
    private final By passwordFieldError = By.xpath(".//p[text() = 'Некорректный пароль']");
    private final By loginButton = By.cssSelector(".Auth_link__1fOlj");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Open register page")
    public void openRegisterPage() {
        driver.get(Constants.RESOURCE_URL_REGISTRATION);
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

    @Step("Click on Register Button in Register Page")
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    @Step("Register a new user in Register Page")
    public void registerUser(String name, String email, String password) {
        setUserName(name);
        setUserEmail(email);
        setUserPassword(password);
        clickRegisterButton();
    }

    @Step("Wait until error message will be visible in Register Page")
    public void waitLoadingErrorMessage() {
        new WebDriverWait(driver, Duration.ofSeconds(Constants.WAITING_SECONDS)).until(ExpectedConditions.visibilityOfElementLocated(passwordFieldError));
    }

    @Step("Check error message under password field in Register Page")
    public boolean checkErrorMessageIsDisplayed() {
        waitLoadingErrorMessage();
        return driver.findElement(passwordFieldError).isDisplayed();
    }

    @Step("Click on Login Button in Register Page")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }
}
