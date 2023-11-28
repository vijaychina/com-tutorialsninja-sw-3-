package laptopsandnotebooks;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.Utility;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * ● Create package laptopsandnotebooks
 * Create the class LaptopsAndNotebooksTest
 * Write the following test
 * 1. Test name verifyProductsPriceDisplayHighToLowSuccessfully()
 * 1.1 Mouse hover on Laptops & Notebooks Tab.and click
 * 1.2 Click on “Show All Laptops & Notebooks”
 * 1.3 Select Sort By "Price (High > Low)"
 * 1.4 Verify the Product price will arrange in High to Low order.
 * 2. Test name verifyThatUserPlaceOrderSuccessfully()
 * 2.1 Mouse hover on Laptops & Notebooks Tab and click
 * 2.2 Click on “Show All Laptops & Notebooks”
 * 2.3 Select Sort By "Price (High > Low)"
 * 2.4 Select Product “MacBook”
 * 2.5 Verify the text “MacBook”
 * 2.6 Click on ‘Add To Cart’ button
 * 2.7 Verify the message “Success: You have added MacBook to your shopping cart!”
 * 2.8 Click on link “shopping cart” display into success message
 * 2.9 Verify the text "Shopping Cart"
 * 2.10 Verify the Product name "MacBook"
 * 2.11 Change Quantity "2"
 * 2.12 Click on “Update” Tab
 * 2.13 Verify the message “Success: You have modified your shopping cart!”
 * 2.14 Verify the Total £737.45
 * 2.15 Click on “Checkout” button
 * 2.16 Verify the text “Checkout”
 * 2.17 Verify the Text “New Customer”
 * 2.18 Click on “Guest Checkout” radio button
 * 2.19 Click on “Continue” tab
 * 2.20 Fill the mandatory fields
 * 2.21 Click on “Continue” Button
 * 2.22 Add Comments About your order into text area
 * 2.23 Check the Terms & Conditions check box
 * 2.24 Click on “Continue” button
 * 2.25 Verify the message “Warning: Payment method required!”
 */

public class LaptopsAndNotebooksTest extends Utility {
    String baseUrl = "http://tutorialsninja.com/demo/index.php";

    @Before
    public void setUp() {

        openBrowser(baseUrl);
    }

    @Test
    public void verifyProductsPriceDisplayHighToLowSuccessfully(){

        WebElement ln = driver.findElement(By.linkText("Laptops & Notebooks"));
        Actions actions = new Actions(driver);
        actions.moveToElement(ln).click().build().perform();
        clickOnElement(By.linkText("Show AllLaptops & Notebooks"));
        selectByVisibleTextFromDropDown(By.id("input-sort"), "Price (High > Low)");
        List<WebElement> price = driver.findElements(By.xpath("//span[@class = 'price-tax']"));
        List<Double> prices = new ArrayList<>();
        for(WebElement element : price){
            String priceText = element.getText().replaceAll("[E,x,T,a,x,:,$]","").replace(",","");
            Double priceValue = Double.parseDouble(priceText);
            prices.add(priceValue);
        }

        Boolean isSorted = true;
        for(int i = 0;i<prices.size()-1;i++){
            if(prices.get(i) < prices.get(i+1)){
                isSorted = false;
                break;
            }
        }
        if (isSorted) {
            System.out.println("Prices are in high to low order.");
        }else {
            System.out.println("Prices are not sorted.");
        }

    }

    @Test
    public void verifyThatUserPlaceOrderSuccessfully(){

        WebElement ln = driver.findElement(By.linkText("Laptops & Notebooks"));
        Actions actions = new Actions(driver);
        actions.moveToElement(ln).click().build().perform();

        clickOnElement(By.linkText("Show AllLaptops & Notebooks"));

        selectByVisibleTextFromDropDown(By.id("input-sort"), "Price (High > Low)");

        clickOnElement(By.linkText("MacBook"));
        String eMacbook = "MacBook";
        String aMacbook = getTextFromElement(By.xpath("//h1[contains(text(),'MacBook')]"));
        Assert.assertEquals(eMacbook,aMacbook);

        clickOnElement(By.id("button-cart"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        String eSuccessMsg = "Success: You have added MacBook to your shopping cart!\n"+
                "×";
        String aSuccessMsg = driver.findElement(By.xpath("//div[@class = 'alert alert-success alert-dismissible']")).getText();
        Assert.assertEquals(eSuccessMsg, aSuccessMsg);

        try {
            clickOnElement(By.xpath("//a[contains(text(),'shopping cart')]"));
        } catch (ElementClickInterceptedException e) {
            clickOnElement(By.xpath("//a[contains(text(),'shopping cart')]"));
        }

        String eText = "Shopping Cart  (0.00kg)";
        String aText = driver.findElement(By.xpath("//h1[contains(text(),'Shopping Cart')]")).getText();
        Assert.assertEquals(eText, aText);

        String eProductName = "MacBook";
        String aProductName = getTextFromElement(By.xpath("//body[1]/div[2]/div[1]/div[1]/form[1]/div[1]/table[1]/tbody[1]/tr[1]/td[2]/a[1]"));
        Assert.assertEquals(eProductName, aProductName);

        driver.findElement(By.xpath("//tbody/tr[1]/td[4]/div[1]/input[1]")).clear();
        sendTextToElement(By.xpath("//tbody/tr[1]/td[4]/div[1]/input[1]"),"2");
        clickOnElement(By.xpath("//tr[1]//button[@data-original-title = 'Update']"));

        String eSuccessMsg1 = "$1,204.00";
        String aSuccessMsg1 = driver.findElement(By.xpath("//tbody/tr[1]/td[6]")).getText();
        Assert.assertEquals(eSuccessMsg1,aSuccessMsg1);

        clickOnElement(By.xpath("//a[contains(text(),'Checkout')]"));
        String eCheckout = "Checkout";
        String aCheckout = getTextFromElement(By.xpath("//h1[contains(text(),'Checkout')]"));
        Assert.assertEquals(eCheckout,aCheckout);

        String eNewcustomer = "New Customer";
        String aNewcustomer = getTextFromElement(By.xpath("//h2[contains(text(),'New Customer')]"));
        Assert.assertEquals(eCheckout,aCheckout);

        clickOnElement(By.xpath("//input[@value = 'guest']"));
        clickOnElement(By.id("button-account"));

        sendTextToElement(By.id("input-payment-firstname"),"YK");
        sendTextToElement(By.id("input-payment-lastname"),"Modi");
        sendTextToElement(By.id("input-payment-email"),"rk12@gmail.com");
        sendTextToElement(By.id("input-payment-telephone"),"01234567891");
        sendTextToElement(By.id("input-payment-password"),"Prime@123");
        sendTextToElement(By.id("Password Confirm"),"Prime@123");
        sendTextToElement(By.id("input-payment-address-1"),"abc");
        sendTextToElement(By.id("input-payment-city"),"London");
        sendTextToElement(By.id("input-payment-postcode"),"HA32HL");
        selectByVisibleTextFromDropDown(By.id("input-payment-zone"),"Greater London");
        clickOnElement(By.xpath("//body/div[@id='checkout-checkout']/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[4]/div[1]/input[1]"));
        clickOnElement(By.id("button-guest"));
        sendTextToElement(By.xpath("//textarea[@class = 'form-control']"),"Successful");
        clickOnElement(By.xpath("//input[@type='checkbox']"));
        clickOnElement(By.id("button-payment-method"));

        String eWarning = "Warning: Payment method required!\n"+
                "×";
        String aWarning = driver.findElement(By.xpath("//div[text()='Warning: Payment method required!']")).getText();
        Assert.assertEquals(eWarning,aWarning);


    }

    @After
    public void tearDown() {

        closeBrowser();
    }
}