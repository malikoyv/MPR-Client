package pjwstk.edu.pl.mpr.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private WebDriver webDriver;

    @FindBy(id = "add cat button")
    private WebElement addCatButton;

    @FindBy(id = "cats name field")
    private WebElement catsNameField;

    @FindBy(id = "cats age field")
    private WebElement catsAgeField;

    @FindBy(id = "cat update button")
    private WebElement updateCatButton;

    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void clickAddCatButton() {
        addCatButton.click();
    }

    public String getCatsName() {
        return catsNameField.getText();
    }

    public String getCatsAge() {
        return catsAgeField.getText();
    }

    public String getUpdateUrl() {
        return updateCatButton.getAttribute("href");
    }
}
