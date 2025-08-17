package ru.practikum.tests;

import io.qameta.allure.Description;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.practikum.pages.RegisterPage;

@RunWith(Parameterized.class)
public class IncorrectPasswordErrorTests {
    @Rule
    public DriverFactory driverFactory = new DriverFactory();

    private final String userName;
    private final String email;
    private final String password;
    private final int passwordSymbolsNumber;

    public IncorrectPasswordErrorTests(String userName, String email, String password, int passwordSymbolsNumber) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.passwordSymbolsNumber = passwordSymbolsNumber;
    }

    @Parameterized.Parameters(name = " Symbols number in password - {3}")
    public static Object[][] getOrderData() {
        return new Object[][] {
                {"Иван", RandomStringUtils.randomAlphanumeric(8) + "@test.ru", "", 0},
                {"Мария", RandomStringUtils.randomAlphanumeric(8) + "@test.ru", RandomStringUtils.randomAlphanumeric(1), 1},
                {"Анна", RandomStringUtils.randomAlphanumeric(8) + "@test.ru", RandomStringUtils.randomAlphanumeric(3), 3},
                {"Пётр", RandomStringUtils.randomAlphanumeric(8) + "@test.ru", RandomStringUtils.randomAlphanumeric(4), 4},
                {"Вера", RandomStringUtils.randomAlphanumeric(8) + "@test.ru", RandomStringUtils.randomAlphanumeric(5), 5},
        };
    }

    @Test
    @Description("Test an appearance of an error message when typing incorrect password in field")
    public void errorMessageOnIncorrectPasswordTest() throws InterruptedException {
        WebDriver driver = driverFactory.getDriver();

        RegisterPage registerPageObj = new RegisterPage(driver);
        registerPageObj.openRegisterPage();
        registerPageObj.registerUser(userName, email, password);
        registerPageObj.checkErrorMessageIsDisplayed();
    }
}
