package auto.bo;

public enum Region {
    MOSCOW("Москв"),
    SAINT_PETERBUGR("Санкт-Петербург");

    String name;

    Region(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}