package ru.practikum.tests;

import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.practikum.api.UserApiSteps;
import ru.practikum.models.User;
import ru.practikum.pages.LoginPage;
import ru.practikum.pages.RegisterPage;
import org.apache.commons.lang3.RandomStringUtils;

@RunWith(Parameterized.class)
public class SuccessfulRegistrationTests extends BaseTest{
    @Rule
    public DriverFactory driverFactory = new DriverFactory();
    WebDriver driver;
    UserApiSteps apiSteps;
    User user;

    private final String userName;
    private final String email;
    private final String password;
    private final int passwordSymbolsNumber;

    public SuccessfulRegistrationTests(String userName, String email, String password, int passwordSymbolsNumber) {
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

    @Before
    public void setUp() {
        driver = driverFactory.getDriver();
        apiSteps = new UserApiSteps();
        user = new User();
        apiSteps.setRegistrationDataApi(user, email, password, userName);
    }

    @Test
    @Description("Register user with correct email, password and name")
    public void registerUserSuccessfullyTest() throws InterruptedException {
        RegisterPage registerPageObj = new RegisterPage(driver);
        registerPageObj.openRegisterPage();
        registerPageObj.registerUser(userName, email, password);

        ValidatableResponse response = apiSteps.loginUserApi(user);
        apiSteps.setAccessTokenApi(user, response);

        LoginPage loginPageObj = new LoginPage(driver);
        loginPageObj.checkLoginButtonIsDisplayed();
    }

    @After
    public void tearDown() {
        if (user.getAccessToken() != null) {
            apiSteps.deleteUserApi(user);
        }
    }
}
