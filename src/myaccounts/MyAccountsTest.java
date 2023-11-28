package myaccounts;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.List;

/**
 * ● Create package myaccounts
 * 1. Create the class MyAccountsTest
 * 1.1 create method with name "selectMyAccountOptions" it has one parameter name
 * "option" of type string
 * 1.2 This method should click on the options whatever name is passed as parameter.
 * (Hint: Handle List of Element and Select options)
 * Write the following test
 * 1. Test name verifyUserShouldNavigateToRegisterPageSuccessfully()
 * 1.1 Clickr on My Account Link.
 * 1.2 Call the method “selectMyAccountOptions” method and pass the parameter
 * “Register”
 * 1.3 Verify the text “Register Account”.
 * 2. Test name verifyUserShouldNavigateToLoginPageSuccessfully()
 * 2.1 Clickr on My Account Link.
 * 2.2 Call the method “selectMyAccountOptions” method and pass the parameter
 * “Login”
 * 2.3 Verify the text “Returning Customer”.
 * 3. Test name verifyThatUserRegisterAccountSuccessfully()
 * 3.1 Clickr on My Account Link.
 * 3.2 Call the method “selectMyAccountOptions” method and pass the parameter
 * “Register”
 * 3.3 Enter First Name
 * 3.4 Enter Last Name
 * 3.5 Enter Email
 * 3.6 Enter Telephone
 * 3.7 Enter Password
 * 3.8 Enter Password Confirm
 * 3.9 Select Subscribe Yes radio button
 * 3.10 Click on Privacy Policy check box
 * 3.11 Click on Continue button
 * 3.12 Verify the message “Your Account Has Been Created!”
 * 3.13 Click on Continue button
 * 3.14 Clickr on My Account Link.
 * 3.15 Call the method “selectMyAccountOptions” method and pass the parameter
 * “Logout”
 * 3.16 Verify the text “Account Logout”
 * 3.17 Click on Continue button
 * 4. Test name verifyThatUserShouldLoginAndLogoutSuccessfully()
 * 4.1 Clickr on My Account Link.
 * 4.2 Call the method “selectMyAccountOptions” method and pass the parameter
 * “Login”
 * 4.3 Enter Email address
 * 4.4 Enter Last Name
 * 4.5 Enter Password
 * 4.6 Click on Login button
 * 4.7 Verify text “My Account”
 * 4.8 Clickr on My Account Link.
 * 4.9 Call the method “selectMyAccountOptions” method and pass the parameter
 * “Logout”
 * 4.10 Verify the text “Account Logout”
 * ● Create package myaccounts
 * 1. Create the class MyAccountsTest
 * 1.1 create method with name "selectMyAccountOptions" it has one parameter name
 * "option" of type string
 * 1.2 This method should click on the options whatever name is passed as parameter.
 * (Hint: Handle List of Element and Select options)
 * Write the following test
 * 1. Test name verifyUserShouldNavigateToRegisterPageSuccessfully()
 * 1.1 Clickr on My Account Link.
 * 1.2 Call the method “selectMyAccountOptions” method and pass the parameter
 * “Register”
 * 1.3 Verify the text “Register Account”.
 * 2. Test name verifyUserShouldNavigateToLoginPageSuccessfully()
 * 2.1 Clickr on My Account Link.
 * 2.2 Call the method “selectMyAccountOptions” method and pass the parameter
 * “Login”
 * 2.3 Verify the text “Returning Customer”.
 * 3. Test name verifyThatUserRegisterAccountSuccessfully()
 * 3.1 Clickr on My Account Link.
 * 3.2 Call the method “selectMyAccountOptions” method and pass the parameter
 * “Register”
 * 3.3 Enter First Name
 * 3.4 Enter Last Name
 * 3.5 Enter Email
 * 3.6 Enter Telephone
 * 3.7 Enter Password
 * 3.8 Enter Password Confirm
 * 3.9 Select Subscribe Yes radio button
 * 3.10 Click on Privacy Policy check box
 * 3.11 Click on Continue button
 * 3.12 Verify the message “Your Account Has Been Created!”
 * 3.13 Click on Continue button
 * 3.14 Clickr on My Account Link.
 * 3.15 Call the method “selectMyAccountOptions” method and pass the parameter
 * “Logout”
 * 3.16 Verify the text “Account Logout”
 * 3.17 Click on Continue button
 * 4. Test name verifyThatUserShouldLoginAndLogoutSuccessfully()
 * 4.1 Clickr on My Account Link.
 * 4.2 Call the method “selectMyAccountOptions” method and pass the parameter
 * “Login”
 * 4.3 Enter Email address
 * 4.4 Enter Last Name
 * 4.5 Enter Password
 * 4.6 Click on Login button
 * 4.7 Verify text “My Account”
 * 4.8 Clickr on My Account Link.
 * 4.9 Call the method “selectMyAccountOptions” method and pass the parameter
 * “Logout”
 * 4.10 Verify the text “Account Logout”
 */

public class MyAccountsTest extends Utility {
    String baseUrl = "http://tutorialsninja.com/demo/index.php";

    @Before
    public void setUp() {

        openBrowser(baseUrl);
    }

    public void selectMyAccountOptions(String option) {
        List<WebElement> options = driver.findElements(By.xpath("//ul[@class = 'list-inline']//a"));
        for (WebElement list : options) {
            if (option.equalsIgnoreCase(list.getText())) {
                list.click();
                break;
            }
        }
    }

    @Test
    public void verifyUserShouldNavigateToRegisterPageSuccessfully() {
        clickOnElement(By.xpath("//span[contains(text(),'My Account')]"));
        selectMyAccountOptions("Register");

        String expected = "Register Account";
        String actual = getTextFromElement(By.xpath("//h1[contains(text(),'Register Account')]"));
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void verifyUserShouldNavigateToLoginPageSuccessfully() {
        clickOnElement(By.xpath("//span[contains(text(),'My Account')]"));
        selectMyAccountOptions("Login");

        String expected = "Returning Customer";
        String actual = getTextFromElement(By.xpath("//h2[contains(text(),'Returning Customer')]"));
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void VerifyThatUserRegisterAccountSuccessfully() {

        clickOnElement(By.xpath("//span[contains(text(),'My Account')]"));
        selectMyAccountOptions("Register");

        sendTextToElement(By.id("input-firstname"), "Kelly");
        sendTextToElement(By.id("input-lastname"), "simon");
        sendTextToElement(By.id("input-email"), "ken123@gmail.com");
        sendTextToElement(By.id("input-telephone"), "01234567891");
        sendTextToElement(By.id("input-password"), "Ken@123");
        sendTextToElement(By.id("input-confirm"), "Ken@123");
        clickOnElement(By.xpath("//label[@class = 'radio-inline']//input[@value = '1']"));
        clickOnElement(By.xpath("//input[@type = 'checkbox']"));
        clickOnElement(By.xpath("//input[@type = 'submit']"));

        String eMsg = "Your Account Has Been Created!";
        String aMsg = getTextFromElement(By.xpath("//h1[contains(text(),'Your Account Has Been Created!')]"));
        Assert.assertEquals(eMsg, aMsg);

        clickOnElement(By.xpath("//a[contains(text(),'Continue')]"));
        clickOnElement(By.xpath("//span[contains(text(),'My Account')]"));
        selectMyAccountOptions("Logout");

        String eLogout = "Account Logout";
        String aLogout = getTextFromElement(By.xpath("//h1[contains(text(),'Account Logout')]"));
        Assert.assertEquals(eLogout, aLogout);
        clickOnElement(By.xpath("//a[contains(text(),'Continue')]"));

    }

    @Test
    public void VerifyThatUserLoginAndLogoutSuccessfully() {

        clickOnElement(By.xpath("//span[contains(text(),'My Account')]"));
        selectMyAccountOptions("Login");

        sendTextToElement(By.id("input-email"), "ken123@gmail.com");
        sendTextToElement(By.id("input-password"), "Ken@123");
        clickOnElement(By.xpath("//input[@type = 'submit']"));

        String eAccount = "My Account";
        String aAccount = getTextFromElement(By.xpath("//h2[contains(text(),'My Account')]"));
        Assert.assertEquals(eAccount, aAccount);

        clickOnElement(By.xpath("//span[contains(text(),'My Account')]"));
        selectMyAccountOptions("Logout");

        String eLogout = "Account Logout";
        String aLogout = getTextFromElement(By.xpath("//h1[contains(text(),'Account Logout')]"));
        Assert.assertEquals(eLogout, aLogout);
        clickOnElement(By.xpath("//a[contains(text(),'Continue')]"));
    }

    @After
    public void tearDown() {

        closeBrowser();
    }
}