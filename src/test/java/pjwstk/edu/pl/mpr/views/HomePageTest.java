package pjwstk.edu.pl.mpr.views;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pjwstk.edu.pl.mpr.pages.HomePage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomePageTest {
    private WebDriver driver;
    private HomePage homePage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/");
        homePage = new HomePage(driver);


    }

    @Test
    public void redirectToAddCatPage() {
        homePage.clickAddCatButton();

        assertEquals(driver.getTitle(), "Add new cat");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
