import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.example.Constants.ButtonForConstructor;
import static org.junit.Assert.assertEquals;
import static org.example.Constants.ButtonForConstructor.*;


@RunWith(Parameterized.class)
public class PersonalConstructorTest extends BaseTest {
    private ButtonForConstructor buttonN;

    public PersonalConstructorTest(ButtonForConstructor buttonN) {

        this.buttonN = buttonN;
    }

    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][]{
                {CONSTRUCTOR},
                {LOGO_STELLAR_BURGER}
        };
    }

    @Test
    @DisplayName("Переход из ЛК в Конструктор")
    public void transitionToConstructorFromLk() {
        transitionToLk();

        userPage.waitLoadingPage()
                .changeButton(buttonN);

        String expectedUrl = "https://stellarburgers.nomoreparties.site/";

        assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    @Step("Переход в ЛК")
    private void transitionToLk() {
        homePage.clickLk();
        loginPage.waitLoadHeader()
                .setEmail(user.getEmail())
                .setPassword(user.getPassword())
                .clickLogin();
        homePage.clickLk();
    }
}
