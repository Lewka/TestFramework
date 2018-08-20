package auto.bo;

public class KomPlatezhiPayment {

    private String payerCode;
    private String providerPeriod;
    private String amountOfInsurance;
    private String amountOfPayment;

    public KomPlatezhiPayment(String payerCode, String providerPeriod, String amountOfInsurance, String amountOfPayment) {
        this.payerCode = payerCode;
        this.providerPeriod = providerPeriod;
        this.amountOfInsurance = amountOfInsurance;
        this.amountOfPayment = amountOfPayment;
    }

    public String getPayerCode() {
        return payerCode;
    }

    public void setPayerCode(String payerCode) {
        this.payerCode = payerCode;
    }

    public String getProviderPeriod() {
        return providerPeriod;
    }

    public void setProviderPeriod(String providerPeriod) {
        this.providerPeriod = providerPeriod;
    }

    public String getAmountOfInsurance() {
        return amountOfInsurance;
    }

    public void setAmountOfInsurance(String amountOfInsurance) {
        this.amountOfInsurance = amountOfInsurance;
    }

    public String getAmountOfPayment() {
        return amountOfPayment;
    }

    public void setAmountOfPayment(String amountOfPayment) {
        this.amountOfPayment = amountOfPayment;
    }
}