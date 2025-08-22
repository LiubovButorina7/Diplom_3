package ru.practikum.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.practikum.api.UserApiSteps;
import ru.practikum.models.User;
import ru.practikum.pages.ForgotPasswordPage;
import ru.practikum.pages.LoginPage;
import ru.practikum.pages.MainPageConstructor;
import ru.practikum.pages.RegisterPage;

public class LoginSiteTests extends BaseTest{
    @Rule
    public DriverFactory driverFactory = new DriverFactory();
    private WebDriver driver;
    UserApiSteps apiSteps;

    private User user;

    @Before
    public void setUp() {
        driver = driverFactory.getDriver();
        apiSteps = new UserApiSteps();

        user = new User();
        apiSteps.setRegistrationDataApi(user, RandomStringUtils.randomAlphanumeric(8), RandomStringUtils.randomAlphanumeric(8), RandomStringUtils.randomAlphabetic(6));
        ValidatableResponse response = apiSteps.registerUserApi(user);
        apiSteps.setAccessTokenApi(user, response);
    }

    @Test
    @DisplayName("Login site on click LoginAccount button in Main Page")
    @Description("Test successful site login via LoginAccount button")
    public void loginSiteOnLoginAccountButtonInMainPageTest() throws InterruptedException {
        MainPageConstructor mainPageObj = new MainPageConstructor(driver);
        mainPageObj.openMainPageConstructor();
        mainPageObj.clickLoginAccountButton();
        LoginPage loginPageObj = new LoginPage(driver);
        loginPageObj.loginUser(user.getEmail(), user.getPassword());
        mainPageObj.checkMainPageIsDisplayed();
    }

    @Test
    @DisplayName("Login site on click PersonalAccount button in Site Header")
    @Description("Test successful site login via PersonalAccount button")
    public void loginSiteOnPersonalAccountButtonInSiteHeaderTest() throws InterruptedException {
        MainPageConstructor mainPageObj = new MainPageConstructor(driver);
        mainPageObj.openMainPageConstructor();
        mainPageObj.clickPersonalAccountButton();
        LoginPage loginPageObj = new LoginPage(driver);
        loginPageObj.loginUser(user.getEmail(), user.getPassword());
        mainPageObj.checkMainPageIsDisplayed();
    }

    @Test
    @DisplayName("Login site on click Login button in Register Page")
    @Description("Test successful site login via Login button in Register Page")
    public void loginSiteOnLoginButtonInRegisterPageTest() throws InterruptedException {
        RegisterPage registerPageObj = new RegisterPage(driver);
        registerPageObj.openRegisterPage();
        registerPageObj.clickLoginButton();
        LoginPage loginPageObj = new LoginPage(driver);
        loginPageObj.loginUser(user.getEmail(), user.getPassword());
        MainPageConstructor mainPageObj = new MainPageConstructor(driver);
        mainPageObj.checkMainPageIsDisplayed();
    }

    @Test
    @DisplayName("Login site on click Login button in Forgot Password Page")
    @Description("Test successful site login via Login button in Forgot Password Page")
    public void loginSiteOnLoginButtonInForgotPasswordPageTest() throws InterruptedException {
        LoginPage loginPageObj = new LoginPage(driver);
        loginPageObj.openLoginPage();
        loginPageObj.clickRecoverPasswordButton();
        ForgotPasswordPage forgotPasswordPageObj = new ForgotPasswordPage(driver);
        forgotPasswordPageObj.clickLoginButton();
        loginPageObj.loginUser(user.getEmail(), user.getPassword());
        MainPageConstructor mainPageObj = new MainPageConstructor(driver);
        mainPageObj.checkMainPageIsDisplayed();
    }

    @After
    public void tearDown() {
        if (user.getAccessToken() != null) {
            apiSteps.deleteUserApi(user);
        }
    }
}
