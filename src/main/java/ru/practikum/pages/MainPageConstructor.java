package ru.practikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.practikum.util.Constants;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class MainPageConstructor {
    protected final WebDriver driver;

    private final By loginAccountButton = By.xpath(".//button[text() = 'Войти в аккаунт']");
    private final By personalAccountButton = By.xpath(".//p[text() = 'Личный Кабинет']");
    private final By sectionConstructor = By.cssSelector(".BurgerIngredients_ingredients__1N8v2");
    private final By tabBuns = By.xpath(".//span[text() = 'Булки']/parent::div");
    private final By tabSauces = By.xpath(".//span[text() = 'Соусы']/parent::div");
    private final By tabFillings = By.xpath(".//span[text() = 'Начинки']/parent::div");
    private final By menu = By.cssSelector(".BurgerIngredients_ingredients__menuContainer__Xu3Mo");

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

    @Step("Check Tab Buns Activity")
    public void checkIfTabBunsIsActive() {
        WebElement element = driver.findElement(tabBuns);
        String attribute = element.getAttribute("class");
        System.out.println(attribute);
        assertTrue("Вкладка 'Булки' неактивна", attribute.contains("tab_tab_type_current__2BEPc"));
    }

    @Step("Check Tab Sauces Activity")
    public void checkIfTabSaucesIsActive() {
        WebElement element = driver.findElement(tabSauces);
        String attribute = element.getAttribute("class");

        assertTrue("Вкладка 'Соусы' неактивна", attribute.contains("tab_tab_type_current__2BEPc"));
    }

    @Step("Check Tab Fillings Activity")
    public void checkIfTabFillingsIsActive() {
        WebElement element = driver.findElement(tabFillings);
        String attribute = element.getAttribute("class");
        assertTrue("Вкладка 'Начинки' неактивна", attribute.contains("tab_tab_type_current__2BEPc"));
    }

    @Step("Wait until Constructor will be loaded")
    public void waitLoadingConstructor() {
        new WebDriverWait(driver, Duration.ofSeconds(Constants.WAITING_SECONDS))
                .until(ExpectedConditions.and(
                        ExpectedConditions.elementToBeClickable(sectionConstructor),
                        ExpectedConditions.visibilityOfElementLocated(sectionConstructor)));
    }

    @Step("Wait until tab Buns will be clickable")
    public void waitLoadingTabBuns() {
        new WebDriverWait(driver, Duration.ofSeconds(Constants.WAITING_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(tabBuns));
    }

    @Step("Wait until tab Sauces will be clickable")
    public void waitLoadingTabSauces() {
        new WebDriverWait(driver, Duration.ofSeconds(Constants.WAITING_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(tabSauces));

    }
    @Step("Wait until tab Fillings will be clickable")
    public void waitLoadingTabFillings() {
        new WebDriverWait(driver, Duration.ofSeconds(Constants.WAITING_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(tabFillings));
    }

    @Step("Check is Main Page (Constructor) displayed")
    public void checkMainPageIsDisplayed() {
        try {
            waitLoadingConstructor();
        } catch (Exception e) {
            throw new RuntimeException("Конструктор не найден на странице: " + e.getMessage());
        }
    }

}
