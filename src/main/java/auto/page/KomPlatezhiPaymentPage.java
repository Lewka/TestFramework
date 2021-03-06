package auto.page;

import auto.bo.ErrorMessage;
import auto.bo.KomPlatezhiPayment;
import auto.core.element.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import static auto.core.Wrapper.waitForTitleToBe;
import static org.openqa.selenium.Keys.ENTER;

public class KomPlatezhiPaymentPage extends AbstractPage {

    @FindBy(xpath = ".//span[starts-with(text(), 'Оплатить ')]")
    private UIElement payTab;

    @FindBy(xpath = ".//input[@name='provider-payerCode']")
    private UIElement providerPayerCodeInput;

    @FindBy(xpath = ".//input[@name='provider-period']")
    private UIElement providerPeriodInput;

    @FindBy(xpath = ".//span[starts-with(text(), 'Сумма добровольного')]//..//input")
    private UIElement amountOfInsuranceInput;

    @FindBy(xpath = ".//span[starts-with(text(), 'Сумма платежа')]//..//input")
    private UIElement amountOfPaymentInput;

    @FindBy(xpath = ".//h2[starts-with(text(), 'Оплатить ')]")
    private UIElement makePayButton;

    private static final String WRONG_INPUT_ERROR_LOCATOR = "ancestor::div[@*='FormFieldWrapper']//div[@*='UIFormRowError']";

    public void fillFields(KomPlatezhiPayment komPlatezhiPayment) {
        if (payTab.isEnabled()) {
            payTab.click();
            fillFieldIfExist(providerPayerCodeInput, komPlatezhiPayment.getPayerCode());
            fillFieldIfExist(providerPeriodInput, komPlatezhiPayment.getProviderPeriod());
            fillFieldIfExist(amountOfInsuranceInput, komPlatezhiPayment.getAmountOfInsurance());
            fillFieldIfExist(amountOfPaymentInput, komPlatezhiPayment.getAmountOfPayment());
        }
        providerPayerCodeInput.waitForElementToBeEnabled().sendKeys(ENTER);
    }

    public boolean isWrongProviderPayerCodeErrorPresented(ErrorMessage errorMessage) {
        return isErrorMessagePresentedOnElementAndContainsMessage(providerPayerCodeInput, errorMessage.getErrorMessage());
    }

    public boolean isWrongProviderPeriodErrorPresented(ErrorMessage errorMessage) {
        return isErrorMessagePresentedOnElementAndContainsMessage(providerPeriodInput, errorMessage.getErrorMessage());
    }

    public boolean isWrongAmountOfPaymentErrorPresented(ErrorMessage errorMessage) {
        return isErrorMessagePresentedOnElementAndContainsMessage(amountOfPaymentInput, errorMessage.getErrorMessage());
    }

    public boolean isCorrectCompanyPageOpened(String companyName) {
        return waitForTitleToBe(companyName);
    }

    private void fillFieldIfExist(UIElement field, String payerCode) {
        waitForPageLoaded();
        if (field.isEnabled()) {
            field.waitForElementToBeEnabled().sendKeys(payerCode);
        }
    }

    private boolean isErrorMessagePresentedOnElementAndContainsMessage(UIElement element, String message) {
        try {
            UIElement errorElement = new UIElement(element.findElement(By.xpath(WRONG_INPUT_ERROR_LOCATOR)));
            return errorElement.waitForElementDisplayed().isDisplayed() && errorElement.getText().equalsIgnoreCase(message);
        } catch (Exception e) {
            return false;
        }
    }


}