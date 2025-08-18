package ru.practikum.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.practikum.models.User;
import ru.practikum.pages.ForgotPasswordPage;
import ru.practikum.pages.LoginPage;
import ru.practikum.pages.MainPageConstructor;
import ru.practikum.pages.RegisterPage;
import ru.practikum.util.Constants;

import static io.restassured.RestAssured.given;

public class LoginSiteTests extends BaseTest{
    @Rule
    public DriverFactory driverFactory = new DriverFactory();
    private WebDriver driver;

    private User user;

    @Before
    public void setUp() {
        driver = driverFactory.getDriver();

        user = new User();
        setRegistrationData(RandomStringUtils.randomAlphanumeric(8), RandomStringUtils.randomAlphanumeric(8), RandomStringUtils.randomAlphabetic(6));
        ValidatableResponse response = registerUser(user);
        setAccessToken(response);
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

    @Step("Register a new user ")
    public ValidatableResponse registerUser(User user) {
        return given()
                .body(user)
                .when()
                .post(Constants.REGISTER_USER)
                .then();
    }

    @Step("Delete a user")
    public void deleteUser(User user) {
        given()
                .header("Authorization", user.getAccessToken())
                .when()
                .post(Constants.DELETE_USER)
                .then();
    }

    @Step("Set registration data")
    public void setRegistrationData(String email, String password, String name) {
        user.setEmail(email + "@test.ru");
        user.setPassword(password);
        user.setName(name);
    }

    @Step("Set user access token")
    public void setAccessToken(ValidatableResponse response) {
        String userAccessToken = response.extract().body().path("accessToken");
        user.setAccessToken(userAccessToken);
    }

    @After
    public void tearDown() {
        if (user.getAccessToken() != null) {
            deleteUser(user);
        }
    }

}
