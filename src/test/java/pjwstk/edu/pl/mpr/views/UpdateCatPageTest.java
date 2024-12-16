package pjwstk.edu.pl.mpr.views;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pjwstk.edu.pl.mpr.pages.AddCatPage;
import pjwstk.edu.pl.mpr.pages.HomePage;
import pjwstk.edu.pl.mpr.pages.UpdateCatPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateCatPageTest {
    private WebDriver driver;
    private UpdateCatPage updateCatPage;
    private HomePage homePage;
    private AddCatPage addCatPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        updateCatPage = new UpdateCatPage(driver);
        homePage = new HomePage(driver);
        addCatPage = new AddCatPage(driver);

        driver.get("http://localhost:8080/addNewCat");

        addCatPage.setCatNameInput("Cat");
        addCatPage.clearCatAgeInput();
        addCatPage.setCatAgeInput("12");
        addCatPage.clickSaveCatButton();

        driver.get(homePage.getUpdateUrl());
    }

    @Test
    public void updateCat() {
        updateCatPage.clearFields();
        updateCatPage.setCatName("New cat");
        updateCatPage.setCatAge("11");
        updateCatPage.clickUpdateCatButton();

        assertEquals(driver.getTitle(), "Cats");

        assertEquals(driver.findElement(By.xpath("//td[text()='New cat']")).getText(), "New cat");



    }
}
