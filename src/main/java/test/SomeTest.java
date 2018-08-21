package test;

import auto.bo.KomPlatezhiCompany;
import auto.bo.KomPlatezhiPayment;
import auto.steps.KomPlatezhiPaymentStep;
import auto.steps.PaymentsStep;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static auto.bo.ErrorMessage.*;
import static auto.bo.KomPlatezhiCompany.ZKHU;
import static auto.bo.Region.MOSCOW;
import static auto.bo.Region.SAINT_PETERBUGR;
import static java.lang.String.format;

public class SomeTest extends BaseTest {

    private static String companyName;

    @Test
    public void komPlatezhiInvalidValuesInputTest() {
        SoftAssert softAssert = new SoftAssert();

        KomPlatezhiPayment komPlatezhiPayment =
                new KomPlatezhiPayment("1", "1", "1", "-1");

        companyName = mainPageStep.openMainPage()
                .openPayments()
                .selectKommPlatezhi()
                .selectRegion(MOSCOW)
                .getFirstCompanyName();

        KomPlatezhiPaymentStep komPlatezhiPaymentStep = komPlatezhiStep.selectCompany(ZKHU)
                .fillFields(komPlatezhiPayment);

        softAssert.assertTrue(komPlatezhiPaymentStep.isWrongProviderPayerCodeErrorPresented(WRONG_INPUT_ERROR),
                "Wrong provider payer error wasn't presented on the page");

        softAssert.assertTrue(komPlatezhiPaymentStep.isWrongProviderPeriodErrorPresented(WRONG_INPUT_ERROR_2),
                "Wrong provider period input error wasn't presented on the page");

        softAssert.assertTrue(komPlatezhiPaymentStep.isWrongAmountOfPaymentErrorPresented(WRONG_INPUT_ERROR_3),
                "Wrong amount input error wasn't presented on the page");

        softAssert.assertAll();
    }

    @Test(dependsOnMethods = "komPlatezhiInvalidValuesInputTest", priority = 1)
    public void searchCompanyTest() {
        SoftAssert softAssert = new SoftAssert();

        PaymentsStep paymentsStep = mainPageStep.openPayments()
                .searchForCompany(companyName);

        softAssert.assertTrue(paymentsStep.isSearchingCompanyFirstInList(companyName),
                format("[%s] company should be first in the list", companyName));

        softAssert.assertTrue(paymentsStep.openCompanyByName(companyName)
                .isCorrectCompanyPageOpened(companyName), format("Opened page isn't [%s] company page", companyName));

        softAssert.assertAll();
    }

    @Test(dependsOnMethods = "komPlatezhiInvalidValuesInputTest", priority = 2)
    public void companyNotPresentedTest() {
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertFalse(mainPageStep.openPayments()
                        .selectKommPlatezhi()
                        .selectRegion(SAINT_PETERBUGR)
                        .isCompanyPresented(KomPlatezhiCompany.from(companyName)),
                format("[%s] company shouldn't be presented on the page", companyName));

        softAssert.assertAll();
    }

}