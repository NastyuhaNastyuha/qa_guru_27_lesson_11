package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import helpers.Attach;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.TextBoxPage;

import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TextBoxRemoteTests {

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 10000;
        WebDriverManager.chromedriver().setup();
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    void afterEach() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();
    }

    TextBoxPage textBoxPage = new TextBoxPage();

    @Test
    @Tag("textbox")
    void fillFormTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        Faker faker = new Faker();
        String name = faker.name().fullName();
        String email = faker.internet().emailAddress();
        String address1 = faker.address().fullAddress();
        String address2 = faker.address().fullAddress();

        textBoxPage.openPage()
                   .setUserName(name)
                   .setUserEmail(email)
                   .setCurrentAddress(address1)
                   .setPermanentAddress(address2)
                   .submitForm()

                   .checkResult("Name:", name)
                   .checkResult("Email:", email)
                   .checkResult("Current Address :", address1)
                   .checkResult("Permananet Address :", address2);
    }
}
