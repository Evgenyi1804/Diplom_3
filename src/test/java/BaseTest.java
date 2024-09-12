import io.restassured.http.ContentType;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.example.Pages.*;
import org.example.Constants.ButtonForLogin;
import org.example.UserCreate;
import org.example.User;
import static io.restassured.RestAssured.given;

public class BaseTest {
    private static final String SITE = "https://stellarburgers.nomoreparties.site";
    protected WebDriver driver;
    private final UserCreate UserCreate = new UserCreate();
    protected HomePage homePage;
    protected LoginPage loginPage;
    protected RegisterPage registerPage;
    protected PasswordPage PasswordPage;
    protected UserPage userPage;
    protected User user;

    @Before
    public void create() {
        driver = new ChromeDriver();
        driver.get(SITE);
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        PasswordPage = new PasswordPage(driver);
        userPage = new UserPage(driver);
        createUser();
    }

    @After
    public void delete() {
        driver.quit();
        deleteUser();
    }

    private void createUser() {
        user = UserCreate.getUser();
        given().contentType(ContentType.JSON)
                .body(user)
                .post(SITE + "/api/auth/register");
    }

    private void deleteUser() {
        String accessToken = given()
                .contentType(ContentType.JSON)
                .body(user)
                .post(SITE + "/api/auth/login")
                .body().path("accessToken");
        given().contentType(ContentType.JSON)
                .header("Authorization", accessToken)
                .body(user).delete(SITE + "/api/auth/user");
    }

    public void quiteButton(ButtonForLogin buttonName) {
        switch (buttonName) {
            case LOGIN_ON_HOME_PAGE:
                homePage.clickLoginButton();
                break;
            case LOGIN_ON_LK:
                homePage.clickLk();
                break;
            case LOGIN_ON_REGISTER_PAGE:
                homePage.clickLk();
                loginPage.clickRegister();
                registerPage.clickLogin();
                break;
            case LOGIN_ON_RECOVERY_PASSWORD:
                homePage.clickLk();
                loginPage.clickPasswordButton();
                PasswordPage.clickLoginButton();
                break;
        }
    }
}