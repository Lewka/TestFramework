package auto.page;

import auto.core.element.ElementFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

import static auto.core.driver.DriverFactory.getThreadDriver;

public abstract class AbstractPage {

    public AbstractPage() {
        PageFactory.initElements(new ElementFieldDecorator(new DefaultElementLocatorFactory(getThreadDriver())), this);
    }
}