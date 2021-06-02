package homepage;

import browserTesting.BaseTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class TopMenuTest extends BaseTest {

    String baseUrl = " https://mobile.x-cart.com/";

    @Before

    public void setup(){

        openBrowser(baseUrl);
    }
    @Test

    public void verifyUserShouldNavigateToShippingPageSuccessfully(){

        clickOnElement(By.xpath("(//span[text()='Shipping'])[2]"));
        verifyPageNavigation(By.xpath("//h1[contains(text(),'Shipping')]"), "Shipping");

    }
    @Test

    public void verifyUserShouldNavigateToNewPageSuccessfully(){
        clickOnElement(By.xpath("(//span[text()='New!'])[2]"));
        verifyPageNavigation(By.xpath("//h1[@id='page-title']"), "New arrivals");
    }
    @Test

    public void verifyUserShouldNavigateToComingsoonPageSuccessfully() throws InterruptedException {
       clickOnElement(By.xpath("(//span[text()='Coming soon'])[2]"));
     Thread.sleep(3000);
        verifyPageNavigation(By.xpath("(//h1[@id='page-title']"),"Coming soon");
        clickOnElement(By.xpath("(//span[text()='Coming soon'])[2]"));
        Thread.sleep(3000);
        verifyPageNavigation(By.xpath("//h1[@id='page-title']"),"Coming soon");
    }
    @Test

    public void verifyUserShouldNavigateToContactUsPageSuccessfully() throws InterruptedException{
        clickOnElement(By.xpath("(//span[text()='Contact us'])[2]"));
        Thread.sleep(3000);
        verifyPageNavigation(By.xpath("//h1[@id='page-title']"),"Contact us");

    }
    @After

    public void tearDown(){

        closeBrowser();
    }

}
