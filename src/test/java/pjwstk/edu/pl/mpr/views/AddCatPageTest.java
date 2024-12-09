package pjwstk.edu.pl.mpr.views;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pjwstk.edu.pl.mpr.pages.AddCatPage;
import pjwstk.edu.pl.mpr.pages.HomePage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddCatPageTest {
    private WebDriver driver;
    private AddCatPage addCatPage;
    private HomePage homePage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/addNewCat");
        addCatPage = new AddCatPage(driver);
        homePage = new HomePage(driver);
    }

    @Test
    public void testAddCat() {
        addCatPage.setCatNameInput("Cat");
        addCatPage.clearCatAgeInput();
        addCatPage.setCatAgeInput("12");
        addCatPage.clickSaveCatButton();

        assertEquals(driver.getTitle(), "Cats");


        assertEquals(homePage.getCatsName(), "Cat");
        assertEquals(homePage.getCatsAge(), "12");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
