package auto.page;

import auto.core.element.UIElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends AbstractPage {

    @FindBy(xpath = ".//a[text()='Платежи']")
    private UIElement payments;

    public void openPayments() {
        payments.waitForElementDisplayed().scrollTo().click();
    }


}