import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class PersonalTest extends BaseTest {

    @Test
    @DisplayName("Переход в Личный кабинет авторизованным пользователем")
    public void personalButtonWithAuthUser() {
        autorizationLK();

        homePage.clickLk();
        userPage.waitLoadingPage();

        assertEquals(user.getName(), userPage.getUserName());
        assertEquals(user.getEmail(), userPage.getUserLogin());
    }

    @Test
    @DisplayName("Выход из Личного кабинета")
    public void exitFromLk() {
        autorizationLK();
        toLkAfterAutorization();

        userPage.clickExit();
        loginPage.waitLoadHeader();

        String expectedUrl = "https://stellarburgers.nomoreparties.site/login";

        assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    @Step("Авторизация")
    private void autorizationLK() {
        homePage.clickLk();
        loginPage.waitLoadHeader()
                .setEmail(user.getEmail())
                .setPassword(user.getPassword())
                .clickLogin();
    }

    @Step("Переход в личный кабинет после авторизации")
    private void toLkAfterAutorization() {
        homePage.clickLk();
        userPage.waitLoadingPage();
    }
}
