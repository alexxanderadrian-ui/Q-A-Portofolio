package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(xpath = "//a[contains(text(), 'Sign In')]")
    private WebElement signInButton;

    public void openHomePage() {
        driver.get("https://wegodelivery.app/en");
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public boolean isSignInButtonVisible() {
        return signInButton.isDisplayed();
    }
}