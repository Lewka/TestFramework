package auto.steps;

import auto.bo.KomPlatezhiCompany;
import auto.bo.Region;
import auto.page.KomPlatezhiPage;

public class KomPlatezhiStep extends BaseStep {

    private KomPlatezhiPage komPlatezhiPage = new KomPlatezhiPage();

    public boolean isRegionEqualsTo(Region region) {
        return komPlatezhiPage.isRegionEqualTo(region);
    }

    public KomPlatezhiStep selectRegion(Region region) {
        if (!isRegionEqualsTo(region)) {
            komPlatezhiPage.selectRegion(region);
        }
        return this;
    }

    public KomPlatezhiPaymentStep selectCompany(KomPlatezhiCompany komPlatezhiCompany) {
        komPlatezhiPage.selectCompany(komPlatezhiCompany);
        return new KomPlatezhiPaymentStep();
    }

    public void selectCompany(String komPlatezhiCompany) {
        selectCompany(KomPlatezhiCompany.from(komPlatezhiCompany));
    }

    public String getFirstCompanyName() {
        return komPlatezhiPage.getFirstCompanyName();
    }

    public boolean isCompanyPresented(KomPlatezhiCompany komPlatezhiCompany) {
        return komPlatezhiPage.isCompanyPresented(komPlatezhiCompany);
    }

}