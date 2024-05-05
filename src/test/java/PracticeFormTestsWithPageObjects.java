import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

public class PracticeFormTestsWithPageObjects extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();
    String[] subjects = {"Computer Science", "Arts"};
    String[] hobbies = {"Sports", "Music"};

    @Test
    void successfulFillFormTest() {

        registrationPage.openPage()
                        .setFirstName("Jon")
                        .setLastName("Snow")
                        .setUserEmail("jon.snow@winterfell.com")
                        .setUserGender("Male")
                        .setUserNumber("1234567890")
                        .setBirthDate("10", "July", "1998")
                        .setSubjects(subjects)
                        .setHobbies(hobbies)
                        .uploadFile("file.png")
                        .setCurrentAddress("Castle Black, main stronghold of the Night's Watch\n" + "room 123")
                        .setState("Rajasthan")
                        .setCity("Jaiselmer")
                        .submitForm()

                        .checkTableResult("Student Name", "Jon Snow")
                        .checkTableResult("Student Email", "jon.snow@winterfell.com")
                        .checkTableResult("Gender", "Male")
                        .checkTableResult("Mobile", "1234567890")
                        .checkTableResult("Date of Birth", "10 July,1998")
                        .checkTableResult("Subjects", String.join(", ", subjects))
                        .checkTableResult("Hobbies", String.join(", ", hobbies))
                        .checkTableResult("Picture", "file.png")
                        .checkTableResult("Address", "Castle Black, main stronghold of the Night's Watch room 123")
                        .checkTableResult("State and City", "Rajasthan Jaiselmer")
                        .closeResultsTable();
    }

    @Test
    void partialFillFormTest() {
        registrationPage.openPage()
                .setFirstName("Jane")
                .setLastName("Dow")
                .setUserGender("Female")
                .setUserNumber("9876543210")
                .setBirthDate("30", "September", "2001")
                .submitForm()

                .checkTableResult("Student Name", "Jane Dow")
                .checkTableResult("Gender", "Female")
                .checkTableResult("Mobile", "9876543210")
                .checkTableResult("Date of Birth", "30 September,2001")
                .closeResultsTable();
    }

    @Test
    void negativeFillFormTest() {
        registrationPage.openPage()
                .setFirstName("Bip")
                .setLastName("Bop")
                .submitForm()

                .checkFormHeader();
    }
}