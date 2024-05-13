package tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import utils.RandomDate;
import utils.RandomStateAndCity;

import static utils.RandomUtils.*;

public class PracticeFormWithPageObjectsTests extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    void successfulFillFormTest() {
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
        String file = "file.png";
        String day = randomDate.day;
        String month = randomDate.month;
        String year = randomDate.year;
        String subjects = getRandomSubject();
        String hobbies = getRandomHobbie();
        String state = randomStateAndCity.state;
        String city = randomStateAndCity.city;

        registrationPage.openPage()
                        .setFirstName(firstName)
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
                        .submitForm()

                        .checkTableResult("Student Name", firstName + " " + lastName)
                        .checkTableResult("Student Email", email)
                        .checkTableResult("Gender", gender)
                        .checkTableResult("Mobile", phoneNumber)
                        .checkTableResult("Date of Birth", day + " " + month + "," + year)
                        .checkTableResult("Subjects", subjects)
                        .checkTableResult("Hobbies", hobbies)
                        .checkTableResult("Picture", file)
                        .checkTableResult("Address", address)
                        .checkTableResult("State and City", state + " " + city)
                        .closeResultsTable();
    }

    @Test
    void partialFillFormTest() {
        Faker faker = new Faker();
        RandomDate randomDate = new RandomDate();
        randomDate.getRandomBirthDate();

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String gender = getRandomGender();
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
}