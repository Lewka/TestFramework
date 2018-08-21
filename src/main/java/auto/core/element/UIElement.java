package auto.core.element;

import auto.core.driver.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.internal.WrapsElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

import static auto.core.driver.DriverFactory.getThreadDriver;

/**
 * Custom decorator of WebElement
 */
public class UIElement implements WrapsElement, WebElement {

    private WebElement wrappedElement;

    private static final long DEFAULT_WAIT_TIME = 10L;
    private static final long DEFAULT_POOLING_TIME = 5L;

    private static final Logger LOGGER = LoggerFactory.getLogger(UIElement.class);

    public UIElement(final WebElement wrappedElement) {
        this.wrappedElement = wrappedElement;
    }

    @Override
    public void click() {
        LOGGER.debug("Clicking on [{}]", wrappedElement);
        try {
            getWrappedElement().click();
        } catch (StaleElementReferenceException e) {
            this.waitForElementToBeClickable().click();
        }
    }

    @Override
    public void submit() {
        LOGGER.debug("Submitting [{}]", wrappedElement);
        getWrappedElement().submit();
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        LOGGER.debug("Sending [{}] to element [{}]", keysToSend, wrappedElement);
        getWrappedElement().sendKeys(keysToSend);
    }

    public void sendKeys(String text) {
        LOGGER.debug("Sending [{}] to element [{}]", text, wrappedElement);
        getWrappedElement().sendKeys(text);
    }

    @Override
    public void clear() {
        LOGGER.debug("Clearing text on element [{}]", wrappedElement);
        getWrappedElement().clear();
    }

    @Override
    public String getTagName() {
        LOGGER.debug("Getting tag of element [{}]", wrappedElement);
        String tagName = getWrappedElement().getTagName();
        LOGGER.debug("Got tag [{}]", tagName);
        return tagName;
    }

    @Override
    public String getAttribute(String name) {
        LOGGER.debug("Getting attribute [{}] of element [{}]", name, wrappedElement);
        return getWrappedElement().getAttribute(name);
    }

    @Override
    public boolean isSelected() {
        LOGGER.debug("Checking is element [{}] selected", wrappedElement);
        boolean selected;
        try {
            selected = wrappedElement.isSelected();
        } catch (Exception e) {
            selected = false;
        }
        LOGGER.debug("Element [{}] selected [{}]", wrappedElement, selected);
        return selected;
    }

    @Override
    public boolean isEnabled() {
        LOGGER.debug("Checking is element [{}] enabled", wrappedElement);
        boolean enabled;
        try {
            enabled = wrappedElement.isEnabled();
        } catch (Exception e) {
            enabled = false;
        }
        LOGGER.debug("Element [{}] enabled [{}] ", wrappedElement, enabled);
        return enabled;
    }

    @Override
    public String getText() {
        LOGGER.debug("Getting text from element [{}]", wrappedElement);
        String text = wrappedElement.getText();
        LOGGER.debug("Got text [{}]", text);
        return text;
    }

    @Override
    public List<WebElement> findElements(By by) {
        LOGGER.debug("Find elements by [{}] on element [{}]", by, wrappedElement);
        return getWrappedElement().findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        LOGGER.debug("Find element by [{}] on element [{}]", by, wrappedElement);
        return getWrappedElement().findElement(by);
    }

    @Override
    public boolean isDisplayed() {
        LOGGER.debug("Checking is element [{}] displayed", wrappedElement);
        boolean displayed = false;
        try {
            displayed = wrappedElement.isDisplayed();
            LOGGER.debug("Element [{}] displayed [{}]", wrappedElement, displayed);
            return displayed;
        } catch (Exception e) {
            LOGGER.debug("Element [{}] displayed [{}]", wrappedElement, displayed);
            return false;
        }
    }

    @Override
    public Point getLocation() {
        LOGGER.debug("Getting location of [{}]", wrappedElement);
        Point location = getWrappedElement().getLocation();
        LOGGER.debug("Got [X : {} - Y : {}]", location.getX(), location.getY());
        return location;
    }

    @Override
    public Dimension getSize() {
        LOGGER.debug("Getting size of [{}]", wrappedElement);
        Dimension size = getWrappedElement().getSize();
        LOGGER.debug("Got [H :{}, W : {}]", size.getHeight(), size.getWidth());
        return size;
    }

    @Override
    public Rectangle getRect() {
        LOGGER.debug("Getting location and size of [{}]", wrappedElement);
        Rectangle rect = getWrappedElement().getRect();
        LOGGER.debug("Got [H : {}, W : {}, X : {}, Y : {}]", rect.getHeight(), rect.getWidth(), rect.getX(), rect.getY());
        return rect;
    }

    @Override
    public String getCssValue(String propertyName) {
        LOGGER.debug("Getting CSS value [{}] of element [{}]", propertyName, wrappedElement);
        String cssValue = getWrappedElement().getCssValue(propertyName);
        LOGGER.debug("Got [{}]", cssValue);
        return cssValue;
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        LOGGER.debug("Getting screenshot as [{}]", target);
        return getWrappedElement().getScreenshotAs(target);
    }

    @Override
    public WebElement getWrappedElement() {
        return wrappedElement;
    }

    public UIElement waitForElementToBeClickable() {
        WebDriverWait wait = new WebDriverWait(getThreadDriver(), DEFAULT_WAIT_TIME);
        LOGGER.debug("Waiting [{}] sec for element [{}] to be clickable", DEFAULT_WAIT_TIME, wrappedElement);
        wait.ignoring(Exception.class);
        wait.until(ExpectedConditions.elementToBeClickable(wrappedElement));
        return this;
    }

    public UIElement waitForElementToBeClickable(long seconds) {
        WebDriverWait wait = new WebDriverWait(getThreadDriver(), seconds);
        LOGGER.debug("Waiting [{}] sec for element [{}] to be clickable", seconds, wrappedElement);
        wait.ignoring(Exception.class);
        wait.until(ExpectedConditions.elementToBeClickable(wrappedElement));
        return this;
    }

    public UIElement waitForElementToBeEnabled() {
        LOGGER.debug("Waiting for element [{}] to be enabled", wrappedElement);
        new FluentWait<>(DriverFactory.getThreadDriver()).withTimeout(Duration.ofSeconds(DEFAULT_WAIT_TIME))
                .pollingEvery(Duration.ofMillis(DEFAULT_POOLING_TIME))
                .ignoring(NoSuchMethodException.class)
                .until((Function<WebDriver, Object>) webDriver -> wrappedElement.isEnabled());
        return this;
    }

    public UIElement waitForElementToDisappear() {
        WebDriverWait wait = new WebDriverWait(getThreadDriver(), 5);
        LOGGER.debug("Waiting for element [{}] to disappear", wrappedElement);
        wait.ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.stalenessOf(wrappedElement));
        return this;
    }

    public UIElement waitForElementDisplayed() {
        WebDriverWait wait = new WebDriverWait(getThreadDriver(), DEFAULT_WAIT_TIME);
        LOGGER.debug("Waiting [{}] sec for element [{}] to be visible", DEFAULT_WAIT_TIME, wrappedElement);
        wait.ignoring(Exception.class);
        wait.until(ExpectedConditions.visibilityOf(wrappedElement));
        return this;
    }

    public UIElement waitForElementDisplayed(long sec) {
        WebDriverWait wait = new WebDriverWait(getThreadDriver(), sec);
        LOGGER.debug("Waiting [{}] sec for element [{}] to be visible", sec, wrappedElement);
        wait.ignoring(Exception.class);
        wait.until(ExpectedConditions.visibilityOf(wrappedElement));
        return this;
    }

    public UIElement scrollTo() {
        LOGGER.debug("Scrolling element [{}] into view", wrappedElement);
        ((JavascriptExecutor) getThreadDriver()).executeScript("arguments[0].scrollIntoView(true);", wrappedElement);
        return this;
    }
}