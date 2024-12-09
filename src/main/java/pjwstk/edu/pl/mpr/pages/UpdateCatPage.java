package pjwstk.edu.pl.mpr.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UpdateCatPage {
    private WebDriver driver;

    @FindBy(id="cat name update field")
    private WebElement catName;

    @FindBy(id="cat age update field")
    private WebElement catAge;

    @FindBy(id="update cat button")
    private WebElement updateCatButton;

    public UpdateCatPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setCatAge(String age) {
        catAge.sendKeys(age);
    }

    public void setCatName(String catName) {
        this.catName.sendKeys(catName);
    }

    public void clickUpdateCatButton() {
        updateCatButton.click();
    }

    public void clearFields() {
        catName.clear();
        catAge.clear();
    }
}
