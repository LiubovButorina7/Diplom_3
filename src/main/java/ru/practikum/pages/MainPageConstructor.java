package ru.practikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.practikum.util.Constants;
import java.time.Duration;

public class MainPageConstructor {
    protected final WebDriver driver;

    private final By loginAccountButton = By.xpath(".//button[text() = 'Войти в аккаунт']");
    private final By personalAccountButton = By.xpath(".//p[text() = 'Личный Кабинет']");
    private final By sectionConstructor = By.cssSelector(".BurgerIngredients_ingredients__menuContainer__Xu3Mo");
    private final By tabBuns = By.xpath(".//div/span[text() = 'Булки']/parent::div");
    private final By tabSauces = By.xpath(".//div/span[text() = 'Соусы']/parent::div");
    private final By tabFillings = By.xpath(".//div/span[text() = 'Начинки']/parent::div");

    public MainPageConstructor(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Open main page (constructor)")
    public void openMainPageConstructor() {
        driver.get(Constants.RESOURCE_URL);
    }

    @Step("Click on LoginAccount Button in Main Page")
    public void clickLoginAccountButton() {
        driver.findElement(loginAccountButton).click();
    }

    @Step("Click on PersonalAccount Button in Site Heading")
    public void clickPersonalAccountButton() {
        driver.findElement(personalAccountButton).click();
    }

    @Step("Click on Tab Buns in Constructor")
    public void clickTabBuns() {
        driver.findElement(tabBuns).click();
    }

    @Step("Click on Tab Sauces in Constructor")
    public void clickTabSauces() {
        driver.findElement(tabSauces).click();
    }

    @Step("Click on Tab Fillings in Constructor")
    public void clickTabFillings() {
        driver.findElement(tabFillings).click();
    }

    @Step("Check Buns Section Activity")
    public boolean checkIfSectionBunsIsActive() {
        WebElement element = driver.findElement(tabBuns);
        String attribute = element.getAttribute("class");
        return attribute.contains("tab_tab_type_current__2BEPc");
    }

    @Step("Check Sauces Section Activity")
    public boolean checkIfSectionSaucesIsActive() {
        WebElement element = driver.findElement(tabSauces);
        String attribute = element.getAttribute("class");
        return attribute.contains("tab_tab_type_current__2BEPc");
    }

    @Step("Check Fillings Section Activity")
    public boolean checkIfSectionFillingsIsActive() {
        WebElement element = driver.findElement(tabFillings);
        String attribute = element.getAttribute("class");
        return attribute.contains("tab_tab_type_current__2BEPc");
    }

    @Step("Wait until Main Page will be loaded")
    public void waitLoadingMainPage() {
        new WebDriverWait(driver, Duration.ofSeconds(Constants.WAITING_SECONDS))
                         .until(ExpectedConditions.urlToBe(Constants.RESOURCE_URL));
    }

    @Step("Wait until tab Buns will be active")
    public void waitLoadingTabBuns() {
        new WebDriverWait(driver, Duration.ofSeconds(Constants.WAITING_SECONDS)).until(ExpectedConditions.attributeContains(tabBuns, "class","tab_tab_type_current__2BEPc"));
    }

    @Step("Wait until tab Sauces will be active")
    public void waitLoadingTabSauces() {
        new WebDriverWait(driver, Duration.ofSeconds(Constants.WAITING_SECONDS)).until(ExpectedConditions.attributeContains(tabSauces, "class", "tab_tab_type_current__2BEPc"));
    }

    @Step("Wait until tab Fillings will be active")
    public void waitLoadingTabFillings() {
        new WebDriverWait(driver, Duration.ofSeconds(Constants.WAITING_SECONDS)).until(ExpectedConditions.attributeContains(tabFillings, "class", "tab_tab_type_current__2BEPc"));
    }

    @Step("Check if Main Page is displayed")
    public String checkMainPageIsDisplayed() {
        waitLoadingMainPage();
        return driver.getCurrentUrl();
    }

    @Step("Wait until section Constructor will be visible & clickable")
    public void waitLoadingSectionConstructor() {
        new WebDriverWait(driver, Duration.ofSeconds(Constants.WAITING_SECONDS)).until(ExpectedConditions.and(ExpectedConditions.visibilityOfElementLocated(sectionConstructor),ExpectedConditions.elementToBeClickable(sectionConstructor)));
    }
}
