package auto.page;

import auto.bo.PaymentType;
import auto.core.element.UIElement;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Optional;

public class PaymentsPage extends AbstractPage {

    @FindBy(xpath = ".//input[starts-with(@placeholder, 'Название или ИНН')]")
    private UIElement searchInput;

    @FindBy(xpath = ".//div[@aria-label]")
    private List<UIElement> paymentTypes;

    @FindBy(xpath = ".//button[@aria-label='Предыдущий слайд']")
    private UIElement prevSlide;

    @FindBy(xpath = ".//button[@aria-label='Следующий слайд']")
    private UIElement nextSlide;

    @FindBy(xpath = ".//div[@data-qa-file='GridColumn']")
    private List<UIElement> searchResults;

    public void selectPaymentType(PaymentType paymentType) {
        waitForPageLoaded();
        for (int i = 0; i < 3; i++) {
            Optional<UIElement> result = searchPaymentType(paymentType);
            if (result.isPresent()) {
                result.get().click();
                return;
            } else {
                nextSlide.waitForElementDisplayed().click();
            }
        }
        throw new ElementNotVisibleException("Couldn't find a element with payment time " + paymentType);
    }

    public void searchCompany(String companyName) {
        searchInput.sendKeys(companyName);
    }

    public boolean isSearchingCompanyIsFirst(String companyName) {
        try {
            return searchResults.get(0).getText().contains(companyName);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public void openCompanyByName(String companyName) {
        UIElement searchResult = searchResults.stream()
                .filter(company -> company.getText().contains(companyName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Couldn't find a company with name " + companyName));
        searchResult.waitForElementDisplayed().click();
    }

    private Optional<UIElement> searchPaymentType(PaymentType paymentType) {
        return paymentTypes.stream()
                .filter(el -> el.waitForElementToBeEnabled().getText().equalsIgnoreCase(paymentType.getName()))
                .findFirst();
    }
}