package auto.page;

import auto.bo.KomPlatezhiCompany;
import auto.bo.Region;
import auto.core.element.UIElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static auto.core.driver.DriverFactory.getThreadDriver;
import static org.openqa.selenium.By.xpath;

public class KomPlatezhiPage extends AbstractPage {

    @FindBy(xpath = ".//span[contains(@class, 'regionSelect')]")
    private UIElement regionSelector;

    @FindBy(xpath = ".//li[starts-with(@class, 'UIMenu')]")
    private List<UIElement> companies;

    private static String cityLocator = ".//span[contains(text(), '%s')] | .//div[contains(text(), '%s')]";

    public boolean isRegionEqualTo(Region region) {
        return regionSelector.waitForElementDisplayed().getText().contains(region.getName());
    }

    public void selectCompany(KomPlatezhiCompany komPlatezhiCompany) {
        WebElement companyWebElement = companies.stream()
                .filter(company -> company.waitForElementDisplayed().getText().equalsIgnoreCase(komPlatezhiCompany.getCompanyName()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Couldn't find a company with name " + komPlatezhiCompany));
        companyWebElement.click();
    }

    public boolean isCompanyPresented(KomPlatezhiCompany komPlatezhiCompany) {
        waitForPageLoaded();
        return companies.stream()
                .anyMatch(company -> company.waitForElementToBeEnabled().getText().equalsIgnoreCase(komPlatezhiCompany.getCompanyName()));
    }

    public void selectRegion(Region region) {
        regionSelector.waitForElementDisplayed().click();
        cityLocator = String.format(cityLocator, region.getName(), region.getName());
        WebElement regionElement = getThreadDriver().findElement(xpath(cityLocator));
        regionElement.click();
    }

    public String getFirstCompanyName() {
        return companies.get(0).getText();
    }
}