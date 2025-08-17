package ru.practikum.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.practikum.pages.EntrancePage;
import ru.practikum.pages.MainPageConstructor;
import ru.practikum.pages.RegistrationPage;

public class EnterSiteTests {
    @Rule
    public DriverFactory driverFactory = new DriverFactory();
    WebDriver driver;
    private String email;
    private String password;

    @Before
    public void setUp() {
        driver = driverFactory.getDriver();

        email =  RandomStringUtils.randomAlphanumeric(8) + "@test.ru";
        password = RandomStringUtils.randomAlphanumeric(8);

        RegistrationPage registrationPageObj = new RegistrationPage(driver);
        registrationPageObj.openRegistrationPage();
        registrationPageObj.enterRegistrationData(RandomStringUtils.randomAlphanumeric(6), email, password);
    }

    @Test
    @DisplayName("Enter site on click EnterAccount button in Main Page")
    @Description("Test successful site enter via EnterAccount button")
    public void enterSiteOnEnterAccountButtonInMainPageTest() throws InterruptedException {
        MainPageConstructor mainPageObj = new MainPageConstructor(driver);
        mainPageObj.openMainPageConstructor();
        mainPageObj.clickEnterAccountButton();
        EntrancePage entrancePageObj = new EntrancePage(driver);
        entrancePageObj.enterRegistrationData(email, password);
        mainPageObj.checkMainPageIsDisplayed();
    }
}
