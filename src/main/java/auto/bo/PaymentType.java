package auto.bo;

public enum PaymentType {
    KOMMUNALNIE_PLATEZHI("ЖКХ");

    String name;

    PaymentType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}