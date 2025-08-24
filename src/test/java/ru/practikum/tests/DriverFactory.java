package ru.practikum.tests;

import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.practikum.util.Constants;

import java.time.Duration;

public class DriverFactory extends ExternalResource {
    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public void initDriver() {
        if ("yandex".equals(System.getProperty("browser"))) {
            startYandex();
        } else {
            startChrome();
        }
    }

    private void startChrome() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.WAITING_SECONDS));
        driver.manage().window().maximize();
    }

    private void startYandex() {
        System.setProperty("webdriver.chrome.driver","src/test/resources/yandexdriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:/Users/TotoCutugno/AppData/Local/Yandex/YandexBrowser/Application/browser.exe");
        driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.WAITING_SECONDS));
        driver.manage().window().maximize();
    }

    @Override
    protected void before() throws Throwable {
        initDriver();
    }

    @Override
    protected void after() {
        driver.quit();
    }

}