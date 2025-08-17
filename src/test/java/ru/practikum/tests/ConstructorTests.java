package ru.practikum.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.practikum.pages.MainPageConstructor;

public class ConstructorTests {
    @Rule
    public DriverFactory driverFactory = new DriverFactory();
    WebDriver driver;

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
        mainPageObj.waitLoadingConstructor();
        mainPageObj.waitLoadingTabSauces();
        mainPageObj.clickTabSauces();
        mainPageObj.waitLoadingTabBuns();
        mainPageObj.checkIfTabSaucesIsActive();
        mainPageObj.clickTabBuns();
        mainPageObj.waitLoadingTabSauces();
        mainPageObj.checkIfTabBunsIsActive();
    }

    @Test
    @DisplayName("Go to section 'Sauces' in Constructor")
    @Description("Test go to section 'Sauces' via click on tab Buns")
    public void goToSectionSaucesTest() throws InterruptedException {
        MainPageConstructor mainPageObj = new MainPageConstructor(driver);
        mainPageObj.openMainPageConstructor();
        mainPageObj.waitLoadingConstructor();
        mainPageObj.checkIfTabBunsIsActive();
        mainPageObj.waitLoadingTabSauces();
        mainPageObj.clickTabSauces();
        mainPageObj.waitLoadingTabBuns();
        mainPageObj.checkIfTabSaucesIsActive();
    }

}
