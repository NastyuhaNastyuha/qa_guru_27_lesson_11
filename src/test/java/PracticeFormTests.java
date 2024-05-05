import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTests {
    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void fillFormTest() {
        open("/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        $("#firstName").setValue("Jon");
        $("#lastName").setValue("Snow");
        $("#userEmail").setValue("jon.snow@winterfell.com");
        $("#gender-radio-1").parent().click();
        $("#userNumber").setValue("1234567890");

//        $("#dateOfBirthInput").click();
//        $(".react-datepicker__year-select > [value = '1998']").click();
//        $(".react-datepicker__month-select > [value = '6']").click();
//        $(".react-datepicker__month .react-datepicker__day--010").click();

        //вариант из разбора ДЗ:
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("July");
        $(".react-datepicker__year-select").selectOption("1998");
        $(".react-datepicker__day--010:not(.react-datepicker__day--outside-month)").click();


        $("#subjectsContainer").click();
        $("#subjectsInput").setValue("com");
        $("#react-select-2-option-0").click();
        $("#subjectsContainer").click();
        $("#subjectsInput").setValue("arts").pressEnter();

        $("#hobbies-checkbox-1").parent().click();
        $("#hobbies-checkbox-3").parent().click();
        $("#uploadPicture").uploadFromClasspath("file.png");

        $("#currentAddress").scrollIntoView(true).setValue("Castle Black, main stronghold of the Night's Watch\n" + "room 123");
        $("#state").click();
        $("#react-select-3-option-3").click();
        $("#city").click();
        $("#react-select-4-option-1").click();

        $("#submit").click();

        $x("//td[text()=\"Student Name\"]/../td[2]").shouldHave(text("Jon Snow"));
        $x("//td[text()=\"Student Email\"]/../td[2]").shouldHave(text("jon.snow@winterfell.com"));
        $x("//td[text()=\"Gender\"]/../td[2]").shouldHave(text("Male"));
        $x("//td[text()=\"Mobile\"]/../td[2]").shouldHave(text("1234567890"));
        $x("//td[text()=\"Date of Birth\"]/../td[2]").shouldHave(text("10 July,1998"));
        $x("//td[text()=\"Subjects\"]/../td[2]").shouldHave(text("Computer Science, Arts"));
        $x("//td[text()=\"Hobbies\"]/../td[2]").shouldHave(text("Sports, Music"));
        $x("//td[text()=\"Picture\"]/../td[2]").shouldHave(text("file.png"));
        $x("//td[text()=\"Address\"]/../td[2]").shouldHave(text("Castle Black, main stronghold of the Night's Watch room 123"));
        $x("//td[text()=\"State and City\"]/../td[2]").shouldHave(text("Rajasthan Jaiselmer"));

        $("#closeLargeModal").click();
    }
}