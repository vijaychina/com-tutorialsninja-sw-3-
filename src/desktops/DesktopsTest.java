package desktops;


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
import java.util.List;

public class DesktopsTest extends Utility {
    String baseUrl = "http://tutorialsninja.com/demo/index.php";

    @Before
    public void setUp() {

        openBrowser(baseUrl);
    }

    @Test
    public void verifyProductArrangeInAlphabeticalOrder() {

        WebElement desktop = driver.findElement(By.linkText("Desktops"));
        Actions actions = new Actions(driver);
        actions.moveToElement(desktop).click().build().perform();
        clickOnElement(By.linkText("Show AllDesktops"));
        selectByVisibleTextFromDropDown(By.id("input-sort"), "Name (Z - A)");
        /*WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("input-sort")));
*/
        List<WebElement> items = driver.findElements(By.xpath("//div[@class = 'caption']//a"));

        //verify descending order
        boolean isDescending = false;
        for (int i = 0; i < items.size() - 1; i++) {
            String currentElement = items.get(i).getText();
            String nextElement = items.get(i + 1).getText();
            if (currentElement.compareTo(nextElement) > 0) {
                isDescending = true;
                break;
            }
        }

        if (isDescending) {
            System.out.println("Elements are in descending order.");
        } else {
            System.out.println("Elements are not in descending order.");
        }
    }

    @Test
    public void verifyProductAddedToShoppingCartSuccessfully() {

        WebElement desktop = driver.findElement(By.linkText("Desktops"));
        Actions actions = new Actions(driver);
        actions.moveToElement(desktop).click().build().perform();
        clickOnElement(By.linkText("Show AllDesktops"));
        selectByVisibleTextFromDropDown(By.id("input-sort"), "Name (A - Z)");
        clickOnElement(By.linkText("HP LP3065"));
        String expectedmodel = "HP LP3065";
        String actualmodel = getTextFromElement(By.xpath("//h1[contains(text(),'HP LP3065')]"));
        Assert.assertEquals(expectedmodel, actualmodel);

        String year = "2022";
        String month = "November";
        String date = "30";

        clickOnElement(By.xpath("//span[@class = 'input-group-btn']//button[@class = 'btn btn-default']"));
        while (true) {
            String monthYear = driver.findElement(By.xpath("//div[@class = 'datepicker-days']//th[@class = 'picker-switch']")).getText();
            String[] a = monthYear.split(" ");
            String mon = a[0];
            String yer = a[1].split("\n")[0];

            if (mon.equalsIgnoreCase(month) && yer.equalsIgnoreCase(year)) {
                break;
            } else {
                clickOnElement(By.xpath("//div[@class = 'datepicker-days']//th[@class = 'next']"));
            }
        }

        //select the date
        List<WebElement> allDates = driver.findElements(By.xpath("//tbody/tr/td"));
        for (WebElement dt : allDates) {
            if (dt.getText().equalsIgnoreCase(date)) {
                dt.click();
                break;
            }
        }

        driver.findElement(By.id("input-quantity")).clear();
        sendTextToElement(By.id("input-quantity"), "1");
        clickOnElement(By.id("button-cart"));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        String eSuccessMsg = "Success: You have added HP LP3065 to your shopping cart!\n" +
                "×";
        String aSuccessMsg = driver.findElement(By.xpath("//div[@class = 'alert alert-success alert-dismissible']")).getText();
        Assert.assertEquals(eSuccessMsg, aSuccessMsg);

        try {
            clickOnElement(By.xpath("//a[contains(text(),'shopping cart')]"));
        } catch (ElementClickInterceptedException e) {
            clickOnElement(By.xpath("//a[contains(text(),'shopping cart')]"));
        }

        String eText = "Shopping Cart  (1.00kg)";
        String aText = driver.findElement(By.xpath("//h1[contains(text(),'Shopping Cart')]")).getText();
        Assert.assertEquals(eText, aText);

        String eProductName = "HP LP3065";
        String aProductName = getTextFromElement(By.xpath("//body[1]/div[2]/div[1]/div[1]/form[1]/div[1]/table[1]/tbody[1]/tr[1]/td[2]/a[1]"));
        Assert.assertEquals(eProductName, aProductName);

        String eDate = "Delivery Date:2022-11-30";
        String aDate = getTextFromElement(By.xpath("//small[contains(text(),'Delivery Date:2022-11-30')]"));
        Assert.assertEquals(eDate, aDate);

        String eModel = "Product 21";
        String aModel = getTextFromElement(By.xpath("//td[contains(text(),'Product 21')]"));
        Assert.assertEquals(eModel, aModel);

        String eTotal = "$122.00";
        String aTotal = getTextFromElement(By.xpath("//tbody/tr[1]/td[6]"));
        Assert.assertEquals(eTotal, aTotal);

    }


    @After
    public void tearDown() {

        closeBrowser();
    }

}