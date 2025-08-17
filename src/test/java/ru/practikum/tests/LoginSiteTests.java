package ru.practikum.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.practikum.pages.ForgotPasswordPage;
import ru.practikum.pages.LoginPage;
import ru.practikum.pages.MainPageConstructor;
import ru.practikum.pages.RegisterPage;

public class LoginSiteTests {
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

        RegisterPage registerPageObj = new RegisterPage(driver);
        registerPageObj.openRegisterPage();
        registerPageObj.registerUser(RandomStringUtils.randomAlphanumeric(6), email, password);
    }

    @Test
    @DisplayName("Login site on click LoginAccount button in Main Page")
    @Description("Test successful site login via LoginAccount button")
    public void loginSiteOnLoginAccountButtonInMainPageTest() throws InterruptedException {
        MainPageConstructor mainPageObj = new MainPageConstructor(driver);
        mainPageObj.openMainPageConstructor();
        mainPageObj.clickLoginAccountButton();
        LoginPage loginPageObj = new LoginPage(driver);
        loginPageObj.loginUser(email, password);
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
        loginPageObj.loginUser(email, password);
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
        loginPageObj.loginUser(email, password);
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
        loginPageObj.loginUser(email, password);
        MainPageConstructor mainPageObj = new MainPageConstructor(driver);
        mainPageObj.checkMainPageIsDisplayed();
    }
}
