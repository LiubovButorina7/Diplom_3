package ru.practikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.practikum.util.Constants;

public class MainPageConstructor {
    protected final WebDriver driver;
    private final By personalAccountButton = By.xpath(".//p[text() = 'Личный Кабинет']");

    public MainPageConstructor(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Open main page (constructor)")
    public void openMainPageConstructor() {
        driver.get(Constants.RESOURCE_URL);
    }

    @Step("Click on PesonalAccount Button")
    public void clickPersonalAccountButton() {
        driver.findElement(personalAccountButton).click();
    }
}
