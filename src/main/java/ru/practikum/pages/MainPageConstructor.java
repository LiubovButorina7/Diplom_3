package ru.practikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.practikum.util.Constants;

import java.time.Duration;

public class MainPageConstructor {
    protected final WebDriver driver;
    private final By enterAccountButton = By.xpath(".//button[text() = 'Войти в аккаунт']");
    private final By personalAccountButton = By.xpath(".//p[text() = 'Личный Кабинет']");
    private final By sectionBurgers = By.cssSelector(".BurgerIngredients_ingredients__1N8v2");

    public MainPageConstructor(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Open main page (constructor)")
    public void openMainPageConstructor() {
        driver.get(Constants.RESOURCE_URL);
    }

    @Step("Click on EnterAccount Button")
    public void clickEnterAccountButton() {
        driver.findElement(enterAccountButton).click();
    }

    @Step("Click on PesonalAccount Button")
    public void clickPersonalAccountButton() {
        driver.findElement(personalAccountButton).click();
    }

    @Step("Check is Main Page (Constructor) displayed")
    public void checkMainPageIsDisplayed() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(Constants.WAITING_SECONDS)).until(ExpectedConditions.visibilityOfElementLocated(sectionBurgers));
        } catch (Exception e) {
            throw new RuntimeException("Конструктор бургеров не найден на странице: " + e.getMessage());
        }
    }
}
