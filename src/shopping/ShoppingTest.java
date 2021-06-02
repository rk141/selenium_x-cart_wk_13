package shopping;

import hotdeals.HotDealsTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ShoppingTest extends HotDealsTest {

    String baseUrl = "https://mobile.x-cart.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);

    }
    @Test
    public void verifyThatUserShouldPlaceOrderSuccessfullyForAvengersFabrikationsPlush() throws InterruptedException {

        verifySaleProductsArrangeAlphabetically();
        Thread.sleep(2500);

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,500)", "");
        Thread.sleep(5500);
        mouseHoverAndClickElement(By.xpath("(//button[@type='button'])[4]"));

        try {
            Alert alert = driver.switchTo().alert();
            String actualText = alert.getText();
            String expectedText = "Product has been added to your cart";
            alert.accept();
            Thread.sleep(2500);
            Assert.assertEquals("Text does not match", actualText, expectedText);
        }
        catch (Exception e) {

            verifyText(By.xpath("//li[contains(text(),'Product has been added to your cart')]"), "Product has been added to your cart");
            clickOnElement(By.xpath("//a[@class='close']"));
        }

        clickOnElement(By.xpath("//div[@title='Your cart']"));
        Thread.sleep(2500);
        clickOnElement(By.xpath("//span[contains(text(),'View cart')]"));
        Thread.sleep(2500);
        verifyPageNavigation(By.xpath("//h1[@id='page-title']"), "Your shopping cart - 1 item");
        Thread.sleep(2000);
        changeQuantityOnElement(By.xpath("//input[contains(@id,'amount')]"), "2");
        Thread.sleep(4500);
        verifyText(By.xpath("//h1[@id='page-title']"), "Your shopping cart - 2 items");
        verifyText(By.xpath("(//li[@class='subtotal'])[1]"), "Subtotal: $29.98");
        Thread.sleep(2500);
        verifyText(By.xpath("(//span[@class='surcharge-cell'])[7]"), "$36.00");
        clickOnElement(By.xpath("//span[contains(text(),'Go to checkout')]"));
        Thread.sleep(4000);
        verifyPageNavigation(By.xpath("//h3[contains(text(),'Log in to your account')]"), "Log in to your account");
        sendTextToElement(By.xpath("//input[@id='email']"), "nids82@yahoo.com");
        clickOnElement(By.xpath("//button[@class='btn  regular-button anonymous-continue-button submit']"));
        Thread.sleep(4000);
        verifyPageNavigation(By.xpath("//h1[contains(text(),'Secure Checkout')]"), "Secure Checkout");
        sendTextToElement(By.xpath("//input[@id='shippingaddress-firstname']"), "Kitten");
        sendTextToElement(By.xpath("//input[@id='shippingaddress-lastname']"), "Cruz");
        Thread.sleep(1500);
        sendTextToElement(By.xpath("//input[@id='shippingaddress-street']"), "8 park Street");
        changeQuantityOnElement(By.xpath("//input[@id='shippingaddress-city']"), "southampton");

        selectByValueDropDown(By.xpath("//select[@id='shippingaddress-country-code']"), "GB");
        Thread.sleep(2500);
        sendTextToElement(By.xpath("//input[@id='shippingaddress-custom-state']"), "southampton");
        changeQuantityOnElement(By.xpath("//input[@id='shippingaddress-zipcode']"), "35409");

        JavascriptExecutor jsx3 = (JavascriptExecutor) driver;
        jsx3.executeScript("window.scrollBy(0,600)", "");
        Thread.sleep(3500);
        clickOnElement(By.xpath("//input[@id='create_profile']"));
        Thread.sleep(3500);
        sendTextToElement(By.xpath("//input[@id='password']"), "r123456");
        Thread.sleep(3500);
        JavascriptExecutor jsx1 = (JavascriptExecutor) driver;
        jsx1.executeScript("window.scrollBy(0,100)", "");
        Thread.sleep(3500);
        mouseHoverAndClickElement(By.xpath("(//input[contains(@id,'method')])[2]"));
        Thread.sleep(3000);
        clickOnElement(By.xpath("(//input[contains(@id,'pmethod')])[6]"));
        Thread.sleep(3500);
        verifyText(By.xpath("(//span[@class='surcharge-cell'])[6]"), "$37.03");
        clickOnElement(By.xpath("//button[@class='btn regular-button regular-main-button place-order submit']"));
        Thread.sleep(4000);
        verifyPageNavigation(By.xpath("//h1[@id='page-title']"), "Thank you for your order");

    }
    @Test
    public void verifyThatUserShouldClearShoppingCartSuccessfully() throws InterruptedException {

        verifyUserShouldNavigateToBestsellersPageSuccessfully();
        Thread.sleep(2500);
        mouseHoverToElement(By.xpath("//div[@class='sort-box']"));
        mouseHoverAndClickElement(By.xpath("//a[contains(text(),'Name A - Z')]"));

        List<WebElement> productName = driver.findElements(By.xpath("//ul[@class='products-grid grid-list']//h5"));

        verifyProductSortedAsPerAlphabaticalOrder("(//ul[@class='products-grid grid-list']//h5)", productName);
        Thread.sleep(3500);
        JavascriptExecutor jsx1 = (JavascriptExecutor) driver;
        jsx1.executeScript("window.scrollBy(0,700)", "");

        clickOnElement(By.xpath("(//span[text()='Add to cart'])[7]"));
        try {
            verifyPageNavigation(By.xpath("//li[contains(text(),'Product has been added to your cart')]"), "Product has been added to your cart");
            clickOnElement(By.xpath("//a[@class='close']"));
        }
        catch (Exception e) {

            System.out.println(e.getMessage());
        }

        clickOnElement(By.xpath("//div[@title='Your cart']"));
        Thread.sleep(2500);
        clickOnElement(By.xpath("//span[contains(text(),'View cart')]"));
        Thread.sleep(3500);
        verifyPageNavigation(By.xpath("//h1[@id='page-title']"), "Your shopping cart - 1 item");
        clickOnElement(By.xpath("//a[contains(text(),'Empty your cart')]"));
        Thread.sleep(2500);
        verifyAndAcceptAlert("Are you sure you want to clear your cart?");
        verifyText(By.xpath("//li[contains(text(),'Item(s) deleted from your cart')]"), "Item(s) deleted from your cart");
        Thread.sleep(2500);
        verifyText(By.xpath("//h1[@id='page-title']"), "Your cart is empty");
    }

    @After
    public void tearDown() {

        closeBrowser();
    }
}
