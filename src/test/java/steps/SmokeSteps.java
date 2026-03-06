package steps;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import pages.HomePage;
import utils.DriverManager;

public class SmokeSteps {
    HomePage homePage = new HomePage();
    Response apiResponse;

    // --- UI STEPS ---
    @Given("I open the Wego Delivery homepage")
    public void i_open_the_homepage() {
        homePage.openHomePage();
    }

    @Given("I am on the homepage")
    public void i_am_on_the_homepage() {
        homePage.openHomePage();
    }

    @Then("the page title should contain {string}")
    public void check_title(String expectedTitle) {
        Assert.assertTrue(homePage.getPageTitle().contains(expectedTitle));
    }

    @Then("the {string} button should be visible")
    public void check_button(String buttonText) {
        Assert.assertTrue(homePage.isSignInButtonVisible());
    }

    @When("I click on the {string} button")
    public void click_button(String buttonText) {
        // Aici am putea adăuga metoda click în HomePage, dar momentan folosim driver-ul direct din utils dacă e nevoie
        DriverManager.getDriver().findElement(org.openqa.selenium.By.xpath("//a[contains(text(), '" + buttonText + "')]")).click();
    }

    @Then("I should be redirected to the login screen")
    public void check_redirect() {
        String currentUrl = DriverManager.getDriver().getCurrentUrl();
        Assert.assertTrue("Nu am ajuns pe login! URL actual: " + currentUrl, currentUrl.contains("login"));
    }

    // --- API STEPS ---
    @Given("the Wego API is available")
    public void api_setup() {
        RestAssured.baseURI = "https://wegodelivery.app/api";
    }

    @When("I send a GET request to {string}")
    public void send_get(String path) {
        apiResponse = RestAssured.get(path);
    }

    @Then("the response status should be {int} or {int}")
    public void check_api_status(Integer s1, Integer s2) {
        int actual = apiResponse.getStatusCode();
        Assert.assertTrue("Status neașteptat: " + actual, actual == s1 || actual == s2);
    }
}