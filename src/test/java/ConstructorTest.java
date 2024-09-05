import io.qameta.allure.junit4.DisplayName;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.example.Pages.HomePage;
import org.example.Constants.SectionName;
import static org.junit.Assert.assertTrue;
import static org.example.Constants.SectionName.*;

@Slf4j
@RunWith(Parameterized.class)
public class ConstructorTest {
    private static final String SITE = "https://stellarburgers.nomoreparties.site";
    private WebDriver driver;
    private HomePage homePage;
    private SectionName sectionName;
    private String attribute = "tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect";

    public ConstructorTest(SectionName sectionName) {

        this.sectionName = sectionName;
    }

    @Parameterized.Parameters
    public static Object[][] geParameters() {
        return new Object[][]{
                {BUN},
                {SAUCE},
                {FILLING}
        };
    }

    @Before
    public void create() {
        driver = new ChromeDriver();
        homePage = new HomePage(driver);
        driver.get(SITE);
    }

    @After
    public void quite() {

        driver.quit();
    }

    @Test
    @DisplayName("Переход по разделам Конструктора")
    public void clickSectionTest() {
        homePage.clickSection(sectionName);

        String actual = homePage.getClassName(sectionName);

        assertTrue(actual.contains(attribute));
    }
}
