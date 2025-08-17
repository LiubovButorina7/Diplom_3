package ru.practikum.tests;

import io.qameta.allure.Description;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.practikum.pages.EntrancePage;
import ru.practikum.pages.MainPageConstructor;
import ru.practikum.pages.RegistrationPage;
import org.apache.commons.lang3.RandomStringUtils;

@RunWith(Parameterized.class)
public class SuccessfulRegistrationTest {
    @Rule
    public DriverFactory driverFactory = new DriverFactory();
    private final String userName;
    private final String email;
    private final String password;
    private final int passwordSymbolsNumber;

    public SuccessfulRegistrationTest(String userName, String email, String password, int passwordSymbolsNumber) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.passwordSymbolsNumber = passwordSymbolsNumber;
    }

    @Parameterized.Parameters(name = " Symbols number in password - {3}")
    public static Object[][] getOrderData() {
        return new Object[][] {
                {"Иван", RandomStringUtils.randomAlphanumeric(8) + "@test.ru", RandomStringUtils.randomAlphanumeric(6), 6},
                {"Мария", RandomStringUtils.randomAlphanumeric(8) + "@test.ru", RandomStringUtils.randomAlphanumeric(7), 7},
                {"Анна", RandomStringUtils.randomAlphanumeric(8) + "@test.ru", RandomStringUtils.randomAlphanumeric(10), 10}
        };
    }

    @Test
    @Description("Register user with correct email, password and name")
    public void registerUserSuccessfullyTest() throws InterruptedException {
        WebDriver driver = driverFactory.getDriver();

        MainPageConstructor mainPageObj = new MainPageConstructor(driver);
        mainPageObj.openMainPageConstructor();
        mainPageObj.clickPersonalAccountButton();
        EntrancePage entrancePageObj = new EntrancePage(driver);
        entrancePageObj.clickRegisterButton();
        RegistrationPage registrationPageObj = new RegistrationPage(driver);
        registrationPageObj.enterRegistrationData(userName, email, password);
        entrancePageObj.checkEnterButtonIsDisplayed();
    }
}
