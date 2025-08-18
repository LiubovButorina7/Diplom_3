package ru.practikum.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.practikum.pages.MainPageConstructor;
import ru.practikum.util.Constants;

import java.time.Duration;

public class ConstructorTests {
    @Rule
    public DriverFactory driverFactory = new DriverFactory();
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = driverFactory.getDriver();
    }

    @Test
    @DisplayName("Go to section 'Buns' in Constructor")
    @Description("Test go to section 'Buns' via click on tab Buns")
    public void goToSectionBunsTest() throws InterruptedException {
        MainPageConstructor mainPageObj = new MainPageConstructor(driver);
        mainPageObj.openMainPageConstructor();
        new WebDriverWait(driver, Duration.ofSeconds(Constants.WAITING_SECONDS));
        mainPageObj.clickTabSauces();
        new WebDriverWait(driver, Duration.ofSeconds(Constants.WAITING_SECONDS));
        mainPageObj.clickTabBuns();
        mainPageObj.waitLoadingTabSauces();
        mainPageObj.checkIfSectionBunsIsActive();
    }

    @Test
    @DisplayName("Go to section 'Sauces' in Constructor")
    @Description("Test go to section 'Sauces' via click on tab Sauces")
    public void goToSectionSaucesTest() throws InterruptedException {
        MainPageConstructor mainPageObj = new MainPageConstructor(driver);
        mainPageObj.openMainPageConstructor();
        new WebDriverWait(driver, Duration.ofSeconds(Constants.WAITING_SECONDS));
        mainPageObj.clickTabSauces();
        mainPageObj.waitLoadingTabBuns();
        mainPageObj.checkIfSectionSaucesIsActive();
    }

    @Test
    @DisplayName("Go to section 'Fillings' in Constructor")
    @Description("Test go to section 'Fillings' via click on tab Sauces")
    public void goToSectionFillingsTest() throws InterruptedException {
        MainPageConstructor mainPageObj = new MainPageConstructor(driver);
        mainPageObj.openMainPageConstructor();
        new WebDriverWait(driver, Duration.ofSeconds(Constants.WAITING_SECONDS));
        mainPageObj.clickTabFillings();
        mainPageObj.waitLoadingTabBuns();
        mainPageObj.checkIfSectionFillingsIsActive();
    }

}
