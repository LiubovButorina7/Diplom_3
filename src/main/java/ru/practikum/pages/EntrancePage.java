package ru.practikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.practikum.util.Constants;
import java.time.Duration;

public class EntrancePage {
    protected final WebDriver driver;
    private final By registerButton = By.xpath(".//a[text() = 'Зарегистрироваться']");
    private final By enterButton = By.xpath(".//button[text() = 'Войти']");
    private final By emailField = By.xpath(".//label[text() = 'Email']/parent::*/input");
    private final By passwordField = By.xpath(".//label[text() = 'Пароль']/parent::*/input");

    public EntrancePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click on Register Button")
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    @Step("Check is Enter Button displayed")
    public void checkEnterButtonIsDisplayed() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(Constants.WAITING_SECONDS)).until(ExpectedConditions.visibilityOfElementLocated(enterButton));
        } catch (Exception e) {
            throw new RuntimeException("Кнопка 'Войти' не найдена на странице: " + e.getMessage());
        }
    }

    @Step("Enter email and password of authorized user")
    public void enterRegistrationData(String email, String password) {
        setUserEmail(email);
        setUserPassword(password);
        clickEnterButton();
    }

    @Step("Enter value in Email field")
    public void setUserEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Enter value in Password field")
    public void setUserPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Click on Enter Button")
    public void clickEnterButton() {
        driver.findElement(enterButton).click();
    }
}
