package hotdeals;

import browserTesting.BaseTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class HotDealsTest extends BaseTest {
    String baseUrl = "https://mobile.x-cart.com/";

    @Before

    public void setUp(){
        openBrowser(baseUrl);

    }
    @Test

    public void verifyUserShouldNavigateToSalePageSuccessfully() throws InterruptedException {

        mouseHoverToElement(By.xpath("(//li[@class='leaf has-sub']/child::span[text()='Hot deals'])[2]"));
        mouseHoverAndClickElement(By.xpath("(//span[text()='Sale'])[2]"));
        Thread.sleep(3000);
        verifyPageNavigation(By.xpath("//h1[@id='page-title']"), "Sale");

    }
    @Test
    public void verifySaleProductsArrangeAlphabetically() throws InterruptedException {
        verifyUserShouldNavigateToSalePageSuccessfully();
        Thread.sleep(2000);
        mouseHoverToElement(By.xpath("(//li[@class='leaf has-sub']/child::span[text()='Hot deals'])[2]"));
        mouseHoverAndClickElement(By.xpath("(//span[text()='Sale'])[2]"));
        Thread.sleep(2500);


        List<WebElement> productName = driver.findElements(By.xpath("//div[@class='items-list items-list-products sale-products']//h5"));
        verifyProductSortedAsPerAlphabaticalOrder("(//div[@class='items-list items-list-products sale-products']//h5)", productName);
    }
    public void verifyUserShouldNavigateToBestsellersPageSuccessfully() throws InterruptedException {

        mouseHoverToElement(By.xpath("(//span[contains(text(),'Hot deals')])[2]"));
        mouseHoverAndClickElement(By.xpath("(//span[text()='Bestsellers'])[2]"));
        Thread.sleep(2000);
        verifyPageNavigation(By.xpath("//h1[@id='page-title']"), "Bestsellers");

    }

    @Test

    public void verifyBestSellersProductsArrangeByZToA() throws InterruptedException {

        verifyUserShouldNavigateToBestsellersPageSuccessfully();
        Thread.sleep(2000);
        mouseHoverToElement(By.xpath("//div[@class='sort-box']"));
        mouseHoverAndClickElement(By.xpath("//a[contains(text(),'Name Z - A')]"));
        Thread.sleep(2000);
        List<WebElement> productName = driver.findElements(By.xpath("//div[@class='products']//h5"));

        verifyProductSortedAsPerZToAFilter("(//div[@class='products']//h5)",productName);
    }

    @Test

    public void verifyBestSellersProductsPriceArrangeHighToLow() throws InterruptedException {

        verifyUserShouldNavigateToBestsellersPageSuccessfully();
        Thread.sleep(2000);
        mouseHoverToElement(By.xpath("//div[@class='sort-box']"));
        mouseHoverAndClickElement(By.xpath("//a[contains(text(),'Price High - Low')]"));
        Thread.sleep(2000);

        List<WebElement> priceList = driver.findElements(By.xpath("//div[@class='products']//span[@class='price product-price']"));

        verifyProductSortedAsPerPriceHighToLowFilter("(//div[@class='products']//span[@class='price product-price'])",priceList);
    }

    @Test

    public void verifyBestSellersProductsArrangeByRates() throws InterruptedException {

        verifyUserShouldNavigateToBestsellersPageSuccessfully();
        Thread.sleep(2000);
        mouseHoverToElement(By.xpath("//div[@class='sort-box']"));
        mouseHoverAndClickElement(By.xpath("//a[contains(text(),'Rates')]"));
        List<WebElement> productRatesList = driver.findElements(By.xpath("//div[@class='products']//div[@class='stars-row full']"));

        verifyProductSortedAsPerRatesFilter("(//div[@class='products']//div[@class='stars-row full'])",productRatesList);

    }
    @After
    public void tearDown() {
        closeBrowser();
    }
}
