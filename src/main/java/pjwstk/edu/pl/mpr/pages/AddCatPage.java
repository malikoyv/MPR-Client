package pjwstk.edu.pl.mpr.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddCatPage {
    private WebDriver webDriver;

    @FindBy(id = "cat name input")
    private WebElement catNameInput;

    @FindBy(id = "cat age input")
    private WebElement catAgeInput;

    @FindBy(id = "save new cat button")
    private WebElement saveCatButton;

    public AddCatPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void setCatNameInput(String catName) {
        catNameInput.sendKeys(catName);
    }

    public void setCatAgeInput(String catAge) {
        catAgeInput.sendKeys(catAge);
    }

    public void clickSaveCatButton() {
        saveCatButton.click();
    }

    public void clearCatAgeInput() {
        catAgeInput.clear();
    }
}
