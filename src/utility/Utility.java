package utility;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Utility {

    public WebDriver driver;

    public void clickOnElement(By by) {

        driver.findElement(by).click();
    }

    //this method get text form element
    public String getTextFromElement(By by) {

        return driver.findElement(by).getText();

    }

    //this method will send text on element
    public void sendTextToElement(By by, String text) {

        driver.findElement(by).sendKeys(text);

    }

    public void selectByValueDropDown(By by, String value) {

        //this method select value from drop down

        Select select = new Select(driver.findElement(by));

        //Select by value
        select.selectByValue(value);
    }

    //this method verify the text of navigated page
    public void verifyPageNavigation(By by, String expectedMessage) {

        String actualMessage = driver.findElement(by).getText();

        Assert.assertEquals("User does not navigate to " + expectedMessage + "page", expectedMessage, actualMessage);
    }

    //this method verify the text

    public void verifyText(By by, String expectedText) {

        String actualText = driver.findElement(by).getText();
        Assert.assertEquals("Text does not match", expectedText, actualText);
    }


    //this method mouse hover on selected element
    public void mouseHoverToElement(By by) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(by)).build().perform();
    }

    //this method mouse hover on selected element and click on that element
    public void mouseHoverAndClickElement(By by) {

        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(by)).click().build().perform();

    }

    //This method verify text of alert and accept alert

    public void verifyAndAcceptAlert(String expectedTxt) throws InterruptedException {

        Alert alert = driver.switchTo().alert();
        String actualTxt = alert.getText();
        Thread.sleep(3000);
        Assert.assertEquals("Alert text does not match", expectedTxt, actualTxt);
        alert.accept();

    }

    //this method check product name sorted in ascending order
    public void verifyProductSortedAsPerAlphabaticalOrder(String xpath, List<WebElement> productName) throws InterruptedException {

        int size = productName.size();
        boolean result = false;
        String[] actual = new String[size];
        String[] sorted = new String[size];
        for (int i = 0; i < size; i++) {

            Thread.sleep(1000);
            int xpath_index = i + 1;
            actual[i] = sorted[i] = driver.findElement(By.xpath(xpath + "[" + xpath_index + "]")).getText();
        }
        Arrays.sort(sorted);
        for (int i = 0; i < size; i++) {

            if (!actual[i].equals(sorted[i])) {

                System.out.println("Products not sorted as per A to Z filter");
                System.out.println("Expected result:\t" + sorted[i] + "\tActual result:\t" + actual[i]);
                result = true;

            }
        }

    }

    //this method check product name sorted as per Z to A filter
    public void verifyProductSortedAsPerZToAFilter(String xPath, List<WebElement> productList) throws InterruptedException {

        int size = productList.size();
        boolean result = false;
        List<String> sortedProductNameList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Thread.sleep(500);
            int xPathIndex = i + 1;
            String product = driver.findElement(By.xpath(xPath + "[" + xPathIndex + "]")).getText();
            sortedProductNameList.add(product);
        }

        List<String> actualProductNameList = new ArrayList<>(sortedProductNameList);
        Collections.sort(sortedProductNameList);
        Collections.reverse(sortedProductNameList);

        for (int i = 0; i < size; i++) {
            if (!actualProductNameList.get(i).equals(sortedProductNameList.get(i))) {
                System.out.println("Products not sorted as per Z to A filter");
                System.out.println("Expected result:" + sortedProductNameList.get(i) + "\tActual result:\t" + actualProductNameList.get(i));
                result = true;
            }
        }
    }

    //This method check product sorted as per Rates filter
    public void verifyProductSortedAsPerRatesFilter(String xPath, List<WebElement> productRatesList) throws InterruptedException {

        int size = productRatesList.size();
        List<String> actualList = new ArrayList<>();
        boolean result = false;
        for (int i = 0; i < size; i++) {

            Thread.sleep(500);
            int j = i + 1;
            WebElement element = driver.findElement(By.xpath(xPath + "[" + j + "]"));
            actualList.add(element.getAttribute("style"));
        }
        List<String> tempActualList = new ArrayList<>(actualList);
        Collections.sort(tempActualList);
        Collections.reverse(tempActualList);
        List<String> sortedList = new ArrayList<>(tempActualList);
        for (int i = 0; i < size; i++) {
            if (!actualList.get(i).equals(sortedList.get(i))) {
                System.out.println("Products not sorted as per Rates filter");
                System.out.println("Expected list:\t" + sortedList.get(i) + "\tActual list:\t" + actualList.get(i));
                result = true;
            }
        }
    }

    //This method verify product sorted as per Price high to low filter

    public void verifyProductSortedAsPerPriceLowToHighFilter(String xPath, List<WebElement> productPriceList) throws InterruptedException {

        int size = productPriceList.size();
        boolean result = false;
        String[] actualPriceList = new String[size];
        String[] sortedPriceList = new String[size];
        for (int i = 0; i < size; i++) {
            Thread.sleep(1000);
            int j = i + 1;
            actualPriceList[i] = sortedPriceList[i] = driver.findElement(By.xpath(xPath + "[" + j + "]")).getText();
        }
        Arrays.sort(sortedPriceList);
        for (int i = 0; i < size; i++) {
            if (!actualPriceList[i].equals(sortedPriceList[i])) {
                System.out.println("Products not sorted as per Price Low to High");
                System.out.println("Expected List:\t" + sortedPriceList[i] + "Actual List:\t" + actualPriceList[i]);
                result = true;
            }
        }
    }

    //this method change the quantity of selected element
    public void changeQuantityOnElement(By by, String quantity) {

        WebElement quantityElement = driver.findElement(by);
        quantityElement.clear();
        quantityElement.sendKeys(quantity);
    }

    //this method scrolled down the page
    public void scrollDown(By by, int yAxis) {

        Actions actions = new Actions(driver);
        WebElement slider = driver.findElement(by);

        actions.dragAndDropBy(slider, 0, yAxis).build().perform();

    }

    //This method verify product sorted as per Price:High to Low filter

    public void verifyProductSortedAsPerPriceHighToLowFilter(String xpath, List<WebElement> priceList) throws InterruptedException {

        int size = priceList.size();
        boolean result = false;
        List<Double> actualList = new ArrayList<>();
        for (int i = 0, j = i + 1; i < size; i++) {
            Thread.sleep(500);
            String price = driver.findElement(By.xpath(xpath + "[" + j + "]")).getText();
            String newPrice1 = price.replace("$", " ");
            double newPrice = Double.parseDouble(newPrice1);
            actualList.add(newPrice);
        }
        List<Double> sortedList = new ArrayList<>(actualList);
        Collections.sort(sortedList);
        Collections.reverse(sortedList);
        for (int i = 0; i < size; i++) {
            if (!actualList.get(i).equals(sortedList.get(i))) {
                System.out.println("Products not sorted as per Price High to Low");
                System.out.println("Expected result:" + sortedList.get(i) + "\tActual result:\t" + actualList.get(i));
                result = true;
            }
        }

    }

    //this method verify current and base url
    public void verifyCurrentUrl(String baseUrl) {

        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals("Current url and baseurl not match", baseUrl, currentUrl);

    }
}
