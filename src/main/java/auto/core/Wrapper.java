package auto.core;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static auto.core.driver.DriverFactory.getThreadDriver;
import static auto.core.reporting.Logger.debug;

public final class Wrapper {

    private static final long DEFAULT_WAIT_TIME = 10L;

    private static final Logger LOGGER = LoggerFactory.getLogger(Wrapper.class);

    private Wrapper() {
    }

    public static void open(String absoluteUrl) {
        debug("Opening [{}] URL", absoluteUrl);
        getThreadDriver().navigate().to(absoluteUrl);
    }

    public static WebElement clearText(WebElement webElement) {
        LOGGER.debug("Clearing text on element [{}]", webElement);
        webElement.clear();
        return webElement;
    }

    public static WebElement clickOnElementViaActions(WebElement webElement) {
        LOGGER.debug("Clicking on [{}] via actions", webElement);
        new Actions(getThreadDriver()).click(webElement).build().perform();
        return webElement;
    }

    public static WebElement hoverElement(WebElement webElement) {
        LOGGER.debug("Moving mouse on element [{}]", webElement);
        new Actions(getThreadDriver()).moveToElement(webElement).perform();
        return webElement;
    }

    public static List<WebElement> waitForElementsDisplayed(List<WebElement> elements) {
        LOGGER.debug("Waiting [{}] sec for elements [{}] to be visible", DEFAULT_WAIT_TIME, elements);
        WebDriverWait wait = new WebDriverWait(getThreadDriver(), DEFAULT_WAIT_TIME);
        return wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public static List<WebElement> waitForElementsDisplayed(List<WebElement> elements, long waitingTime) {
        LOGGER.debug("Waiting [{}] sec for elements [{}] to be visible", waitingTime, elements);
        WebDriverWait wait = new WebDriverWait(getThreadDriver(), waitingTime);
        return wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public static void refreshPage() {
        LOGGER.debug("Refreshing current page");
        getThreadDriver().navigate().refresh();
    }

    public static void scrollBy(int x, int y) {
        ((JavascriptExecutor) getThreadDriver()).executeScript("scrollBy(arguments[0], arguments[1]);", x, y);
    }

    public static void sleep(int sec) {
        try {
            LOGGER.debug("Waiting for [{}] sec", sec);
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    public static void executeOnClick(WebElement element) {
        LOGGER.debug("Executing OnClick on element [{}]", element);
        ((JavascriptExecutor) getThreadDriver()).executeScript("arguments[0].onclick()", element);
    }

    public static void jSClick(WebElement element) {
        LOGGER.debug("Clicking on [{}] element via JavaScript", element);
        WebDriver driver = getThreadDriver();
        JavascriptExecutor jsExecute = ((JavascriptExecutor) driver);
        jsExecute.executeScript("arguments[0].click()", element);
    }

    public static String getTextViaJavaScript(WebElement webElement) {
        LOGGER.debug("Getting text from element [{}] via JS", webElement);
        String result = (String) ((JavascriptExecutor) getThreadDriver()).executeScript("return arguments[0].value", webElement);
        LOGGER.debug("Got text [{}]", result);
        return result;
    }

    public static void scrollOnTopOfPage() {
        LOGGER.debug("Scrolling on top of the page");
        ((JavascriptExecutor) getThreadDriver()).executeScript("window.scrollTo(document.body.scrollHeight,0)");
    }

    public static String getCurrentURL() {
        return getThreadDriver().getCurrentUrl();
    }

}