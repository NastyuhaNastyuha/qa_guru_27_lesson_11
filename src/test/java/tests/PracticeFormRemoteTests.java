package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import pages.RegistrationPage;
import utils.RandomDate;
import utils.RandomStateAndCity;
import io.qameta.allure.selenide.AllureSelenide;



import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;

import static io.qameta.allure.Allure.step;
import static utils.RandomUtils.*;


public class PracticeFormRemoteTests extends TestBase {
    RegistrationPage registrationPage = new RegistrationPage();

    @CsvSource(value = {
            "file.png, 1, 1",
            "file.pdf, 3, 2",
            "file.JPG, 7, 3"
    })

    @ParameterizedTest(name = "При полном заполнении формы с файлом {0}, количеством предметов - {1}, количеством хобби - {2} происходит успешная регистрация")
    @Tag("REGRESSION")
    @Tag("CRITICAL")
    @Tag("demoqa1")
    void successfulFillFormTest(String file, int numberOfSubjects, int numberOfHobbies) {
        SelenideLogger.addListener("allure", new AllureSelenide());

        Faker faker = new Faker();
        RandomDate randomDate = new RandomDate();
        randomDate.getRandomBirthDate();
        RandomStateAndCity randomStateAndCity = new RandomStateAndCity();
        randomStateAndCity.getRandomStateAndCity();

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String address = faker.address().fullAddress();
        String gender = getRandomGender();
        String phoneNumber = getRandomNumberString(10);
        String day = randomDate.day;
        String month = randomDate.month;
        String year = randomDate.year;
        String[] subjects = getRandomSubject(numberOfSubjects);
        String[] hobbies = getRandomHobbies(numberOfHobbies);
        String state = randomStateAndCity.state;
        String city = randomStateAndCity.city;

        step("Открыть страницу формы", () -> {
            registrationPage.openPage();
        });

        step("Заполнить поля формы", () -> {
            registrationPage.setFirstName(firstName)
                    .setLastName(lastName)
                    .setUserEmail(email)
                    .setUserGender(gender)
                    .setUserNumber(phoneNumber)
                    .setBirthDate(day, month, year)
                    .setSubjects(subjects)
                    .setHobbies(hobbies)
                    .uploadFile(file)
                    .setCurrentAddress(address)
                    .setState(state)
                    .setCity(city)
                    .submitForm();
        });

        step("Проверить результат отправки формы", () -> {
            registrationPage.checkTableResult("Student Name", firstName + " " + lastName)
                    .checkTableResult("Student Email", email)
                    .checkTableResult("Gender", gender)
                    .checkTableResult("Mobile", phoneNumber)
                    .checkTableResult("Date of Birth", day + " " + month + "," + year)
                    .checkTableResult("Subjects", Arrays.toString(subjects)
                            .replace("[", "")
                            .replace("]", ""))
                    .checkTableResult("Hobbies", Arrays.toString(hobbies)
                            .replace("[", "")
                            .replace("]", ""))
                    .checkTableResult("Picture", file)
                    .checkTableResult("Address", address)
                    .checkTableResult("State and City", state + " " + city)
                    .closeResultsTable();
        });
    }

        @CsvFileSource(resources = "/partialFillFormTest.csv")
        @Tag("REGRESSION")
        @Tag("BLOCKER")
        @ParameterizedTest(name = "При частичном заполнении формы с возрастом {0} и полом {1} происходит успешная регистрация")
        void partialFillFormTest(int age, String gender) {
        Faker faker = new Faker();
        RandomDate randomDate = new RandomDate();
        randomDate.getRandomBirthDateForAge(age);

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String phoneNumber = getRandomNumberString(10);
        String day = randomDate.day;
        String month = randomDate.month;
        String year = randomDate.year;

        registrationPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setUserGender(gender)
                .setUserNumber(phoneNumber)
                .setBirthDate(day, month, year)
                .submitForm()

                .checkTableResult("Student Name", firstName + " " + lastName)
                .checkTableResult("Gender", gender)
                .checkTableResult("Mobile", phoneNumber)
                .checkTableResult("Date of Birth", day + " " + month + "," + year)
                .closeResultsTable();
    }

    @Test
    void negativeFillFormTest() {
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();

        registrationPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .submitForm()

                .checkFormHeader();
    }

    static Stream<Arguments> inCityFieldOnlyCitiesForSelectedStateShouldBeDisplayed() {
        RandomStateAndCity stateAndCity = new RandomStateAndCity();
        Iterator<String> states = stateAndCity.statesAndCities.keySet().iterator();
        String state;
        return Stream.of(
                Arguments.of(state = states.next(), stateAndCity.statesAndCities.get(state)),
                Arguments.of(state = states.next(), stateAndCity.statesAndCities.get(state)),
                Arguments.of(state = states.next(), stateAndCity.statesAndCities.get(state)),
                Arguments.of(state = states.next(), stateAndCity.statesAndCities.get(state))
        );
    }

    @MethodSource
    @Tag("REGRESSION")
    @Tag("MEDIUM")
    @ParameterizedTest(name = "При выборе штата {0} в поле Город должны отображаться города выбранного штата")
    void inCityFieldOnlyCitiesForSelectedStateShouldBeDisplayed(String state, String[] cities) {
        registrationPage.openPage()
                .setState(state)
                .checkField(cities);
    }
}