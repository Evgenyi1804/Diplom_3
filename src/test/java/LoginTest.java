import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.example.Constants.ButtonForLogin;
import static org.junit.Assert.assertEquals;
import static org.example.Constants.ButtonForLogin.*;

@RunWith(Parameterized.class)
public class LoginTest extends BaseTest {
    public static final String HOME_URL = "https://stellarburgers.nomoreparties.site/";
    private final ButtonForLogin ButtonLogin;

    public LoginTest(ButtonForLogin nameButtonLogin) {

        this.ButtonLogin = nameButtonLogin;
    }

    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][]{
                {LOGIN_ON_HOME_PAGE},
                {LOGIN_ON_LK},
                {LOGIN_ON_REGISTER_PAGE},
                {LOGIN_ON_RECOVERY_PASSWORD}
        };
    }

    @Test
    @DisplayName("Авторизация в ЛК")
    public void loginTest() {
        quiteButton(ButtonLogin);

        loginPage.waitLoadHeader()
                .setEmail(user.getEmail())
                .setPassword(user.getPassword())
                .clickLogin();

        homePage.waitLoadHeader();

        String actualUrl = driver.getCurrentUrl();

        assertEquals(HOME_URL, actualUrl);
    }

}