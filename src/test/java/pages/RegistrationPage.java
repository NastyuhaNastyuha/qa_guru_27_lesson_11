package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.TableResultsComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {
    private SelenideElement firstNameInput = $("#firstName"),
        lastNameInput = $("#lastName"),
        userEmailInput = $("#userEmail"),
        genderWrapper = $("#genterWrapper"),
        userNumberInput = $("#userNumber"),
        birthDateInput = $("#dateOfBirthInput"),
        subjectsInput = $("#subjectsInput"),
        hobbiesWrapper = $("#hobbiesWrapper"),
        fileInput = $("#uploadPicture"),
        currentAddressInput = $("#currentAddress"),
        stateAndCityWrapper = $("#stateCity-wrapper"),
        stateInput = $("#state"),
        cityInput = $("#city"),
        submitButton = $("#submit"),
        closeModalButton = $("#closeLargeModal"),
        formHeader = $(".practice-form-wrapper");

    public RegistrationPage openPage() {
        open("/automation-practice-form");
        formHeader.shouldHave(text("Student Registration Form"));
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }

    public RegistrationPage setFirstName(String value) {
        firstNameInput.setValue(value);
        return this;
    }

    public RegistrationPage setLastName(String value) {
        lastNameInput.setValue(value);
        return this;
    }

    public RegistrationPage setUserEmail(String value) {
        userEmailInput.setValue(value);
        return this;
    }

    public RegistrationPage setUserGender(String value) {
        genderWrapper.$(byText(value)).click();
        return this;
    }

    public RegistrationPage setUserNumber(String value) {
        userNumberInput.setValue(value);
        return this;
    }

    public RegistrationPage setBirthDate(String day, String month, String year) {
        birthDateInput.click();
        new CalendarComponent().setDate(day, month, year);
        return this;
    }

    public RegistrationPage setSubjects(String[] args) {
        for(int i = 0; i < args.length; i++) {
            subjectsInput.setValue(args[i]).pressEnter();
        }
        return this;
    }

    public RegistrationPage setHobbies(String[] args) {
        for (int i = 0; i < args.length; i++) {
            hobbiesWrapper.$(byText(args[i])).click();
        }
        return this;
    }

    public RegistrationPage uploadFile(String value) {
        fileInput.uploadFromClasspath(value);
        return this;
    }

    public RegistrationPage setCurrentAddress(String value) {
        currentAddressInput.setValue(value);
        return this;
    }

    public RegistrationPage setState(String value) {
        stateInput.click();
        stateAndCityWrapper.$(byText(value)).click();
        return this;
    }

    public RegistrationPage setCity(String value) {
        cityInput.click();
        stateAndCityWrapper.$(byText(value)).click();
        return this;
    }

    public RegistrationPage submitForm() {
        submitButton.click();
        return this;
    }

    public RegistrationPage checkTableResult(String key, String value) {
        new TableResultsComponent().checkResult(key, value);
        return this;
    }

    public RegistrationPage closeResultsTable() {
        closeModalButton.click();
        return this;
    }

    public RegistrationPage checkFormHeader() {
        formHeader.shouldBe(visible);
        return this;
    }
}
