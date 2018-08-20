package auto.core.element;

import org.openqa.selenium.*;
import org.openqa.selenium.internal.WrapsElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static auto.core.driver.DriverFactory.getThreadDriver;

public class UIElement implements WrapsElement, WebElement {

    private WebElement wrappedElement;

    private static final long DEFAULT_WAIT_TIME = 10L;
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
        getWrappedElement().clear();
    }

    @Override
    public String getTagName() {
        return getWrappedElement().getTagName();
    }

    @Override
    public String getAttribute(String name) {
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
        return getWrappedElement().findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
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
        return getWrappedElement().getLocation();
    }

    @Override
    public Dimension getSize() {
        return getWrappedElement().getSize();
    }

    @Override
    public Rectangle getRect() {
        return getWrappedElement().getRect();
    }

    @Override
    public String getCssValue(String propertyName) {
        return getWrappedElement().getCssValue(propertyName);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
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

    public UIElement waitForElementClickable() {
        LOGGER.debug("Waiting [{}] sec for element [{}] to be clickable", DEFAULT_WAIT_TIME, wrappedElement);
        WebDriverWait wait = new WebDriverWait(getThreadDriver(), DEFAULT_WAIT_TIME);
        wait.ignoring(Exception.class);
        wait.until(ExpectedConditions.elementToBeClickable(wrappedElement));
        return this;
    }

    public UIElement scrollTo() {
        LOGGER.debug("Scrolling element [{}] into view", wrappedElement);
        ((JavascriptExecutor) getThreadDriver()).executeScript("arguments[0].scrollIntoView(true);", wrappedElement);
        return this;
    }
}