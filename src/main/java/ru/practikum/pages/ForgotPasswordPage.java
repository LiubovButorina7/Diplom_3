package ru.practikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {
    protected final WebDriver driver;
    private final By loginButton = By.cssSelector(".Auth_link__1fOlj");

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click on Login Button in Forgot Password Page")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }
}
