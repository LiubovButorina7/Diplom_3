package ru.practikum.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.practikum.pages.MainPageConstructor;

import static org.junit.Assert.assertTrue;

public class ConstructorTests {
    @Rule
    public DriverFactory driverFactory = new DriverFactory();
    private WebDriver driver;
    MainPageConstructor mainPageObj;

    @Before
    public void setUp() {
        driver = driverFactory.getDriver();
        mainPageObj = new MainPageConstructor(driver);
        mainPageObj.openMainPageConstructor();
        mainPageObj.waitLoadingSectionConstructor();
    }

    @Test
    @DisplayName("Go to section 'Buns' in Constructor")
    @Description("Test go to section 'Buns' via click on tab Buns")
    public void goToSectionBunsTest() throws InterruptedException {
        mainPageObj.clickTabSauces();
        mainPageObj.waitLoadingTabSauces();
        mainPageObj.clickTabBuns();
        mainPageObj.waitLoadingTabBuns();
        assertTrue("Вкладка 'Булки' неактивна", mainPageObj.checkIfSectionBunsIsActive());
    }

    @Test
    @DisplayName("Go to section 'Sauces' in Constructor")
    @Description("Test go to section 'Sauces' via click on tab Sauces")
    public void goToSectionSaucesTest() throws InterruptedException {
        mainPageObj.clickTabSauces();
        mainPageObj.waitLoadingTabSauces();
        assertTrue("Вкладка 'Соусы' неактивна", mainPageObj.checkIfSectionSaucesIsActive());
    }

    @Test
    @DisplayName("Go to section 'Fillings' in Constructor")
    @Description("Test go to section 'Fillings' via click on tab Sauces")
    public void goToSectionFillingsTest() throws InterruptedException {
        mainPageObj.clickTabFillings();
        mainPageObj.waitLoadingTabFillings();
        assertTrue("Вкладка 'Соусы' неактивна", mainPageObj.checkIfSectionFillingsIsActive());
    }
}
