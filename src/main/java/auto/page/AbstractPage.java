package auto.page;

import auto.core.element.ElementFieldDecorator;
import auto.core.element.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

import static auto.core.driver.DriverFactory.getThreadDriver;

public abstract class AbstractPage {

    private static final String LOADING_BAR_LOCATOR = ".//div[@*='UILoader']";


    public AbstractPage() {
        PageFactory.initElements(new ElementFieldDecorator(new DefaultElementLocatorFactory(getThreadDriver())), this);
    }

    public void waitForPageLoaded() {
        try {
            UIElement uiElement = new UIElement(getThreadDriver().findElement(By.xpath(LOADING_BAR_LOCATOR)));
            uiElement.waitForElementToDisappear();
        } catch (Exception ignore) {
            // ignoring if we didn't find it
        }
    }
}