package ru.practikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static org.junit.Assert.assertTrue;

public class EntrancePage {
    protected final WebDriver driver;
    private final By registerButton = By.xpath(".//a[text() = 'Зарегистрироваться']");
    private final By enterButton = By.xpath(".//button[text() = 'Войти']");

    public EntrancePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click on Register Button")
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    @Step("Check is Enter Button displayed")
    public void checkEnterButtonIsDisplayed() {
        assertTrue("Кнопка 'Войти' не отображается на странице", driver.findElement(enterButton).isDisplayed());
    }

}
