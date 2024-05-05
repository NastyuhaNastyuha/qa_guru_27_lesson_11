import org.junit.jupiter.api.Test;
import pages.TextBoxPage;

public class TextBoxTests extends TestBase {

    TextBoxPage textBoxPage = new TextBoxPage();

    @Test
    void fillFormTest() {
        textBoxPage.openPage()
                   .setUserName("Tusik")
                   .setUserEmail("tusik@user.com")
                   .setCurrentAddress("Some street 1")
                   .setPermanentAddress("Another street 2")
                   .submitForm()

                   .checkResult("Name:", "Tusik")
                   .checkResult("Email:", "tusik@user.com")
                   .checkResult("Current Address :", "Some street 1")
                   .checkResult("Permananet Address :", "Another street 2");
    }
}
