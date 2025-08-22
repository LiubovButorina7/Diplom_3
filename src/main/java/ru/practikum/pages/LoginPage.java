package ru.practikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.practikum.util.Constants;
import java.time.Duration;

public class LoginPage {
    protected final WebDriver driver;
    private final By loginButton = By.xpath(".//button[text() = 'Войти']");
    private final By emailField = By.xpath(".//label[text() = 'Email']/parent::*/input");
    private final By passwordField = By.xpath(".//label[text() = 'Пароль']/parent::*/input");
    private final By recoverPasswordButton = By.xpath(".//a[text() = 'Восстановить пароль']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Open login page")
    public void openLoginPage() {
        driver.get(Constants.RESOURCE_URL_LOGIN);
    }

    @Step("Check is Login Button displayed in Login Page")
    public void checkLoginButtonIsDisplayed() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(Constants.WAITING_SECONDS)).until(ExpectedConditions.visibilityOfElementLocated(loginButton));
        } catch (Exception e) {
            throw new RuntimeException("Кнопка 'Войти' не найдена на странице Login Page: " + e.getMessage());
        }
    }

    @Step("Login a user in Login Page")
    public void loginUser(String email, String password) {
        setUserEmail(email);
        setUserPassword(password);
        clickLoginButton();
    }

    @Step("Enter value in Email field")
    public void setUserEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Enter value in Password field")
    public void setUserPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Click on Login Button in Login Page")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Click on RecoverPassword Button in Login Page")
    public void clickRecoverPasswordButton() {
        driver.findElement(recoverPasswordButton).click();
    }
}
