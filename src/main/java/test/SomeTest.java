package test;

import auto.bo.KomPlatezhiCompany;
import auto.bo.KomPlatezhiPayment;
import auto.steps.KomPlatezhiPaymentStep;
import auto.steps.PaymentsStep;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static auto.bo.KomPlatezhiCompany.ZKHU;
import static auto.bo.Region.MOSCOW;
import static auto.bo.Region.SAINT_PETERBUGR;
import static java.lang.String.format;

public class SomeTest extends BaseTest {

    @Test
    public void someTest() {
        SoftAssert softAssert = new SoftAssert();

        KomPlatezhiPayment komPlatezhiPayment =
                new KomPlatezhiPayment("1", "1", "1", "-1");

        String firstCompanyName = mainPageStep.openMainPage()
                .openPayments()
                .selectKommPlatezhi()
                .selectRegion(MOSCOW)
                .getFirstCompanyName();

        KomPlatezhiPaymentStep komPlatezhiPaymentStep = komPlatezhiStep.selectCompany(ZKHU)
                .fillFields(komPlatezhiPayment);

        softAssert.assertTrue(komPlatezhiPaymentStep.isWrongProviderPayerCodeErrorPresented(),
                "Wrong provider payer error wasn't presented on the page");

        softAssert.assertTrue(komPlatezhiPaymentStep.isWrongProviderPeriodErrorPresented(),
                "Wrong provider period input error wasn't presented on the page");

        softAssert.assertTrue(komPlatezhiPaymentStep.isWrongAmountOfPaymentErrorPresented(),
                "Wrong amount input error wasn't presented on the page");

        PaymentsStep paymentsStep = komPlatezhiPaymentStep.openPayments()
                .searchForCompany(firstCompanyName);

        softAssert.assertTrue(paymentsStep.isSearchingCompanyFirstInList(firstCompanyName),
                format("%s company should be first in the list", firstCompanyName));

        softAssert.assertTrue(paymentsStep.openCompanyByName(firstCompanyName)
                .isCorrectCompanyPageOpened(firstCompanyName), format("Opened page isn't [%s] company page", firstCompanyName));

        softAssert.assertFalse(paymentsStep.openPayments()
                        .selectKommPlatezhi()
                        .selectRegion(SAINT_PETERBUGR)
                        .isCompanyPresented(KomPlatezhiCompany.from(firstCompanyName)),
                format("%s company shouldn't be presented on the page", firstCompanyName));

        softAssert.assertAll();
    }

}