package auto.core.utils;

import java.util.Arrays;

public enum Browser {

    CHROME("chrome"),
    FIREFOX("firefox");

    String name;

    Browser(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Browser parse(String value) {
        return Arrays.stream(values())
                .filter(browser -> browser.getName().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Couldn't find a browser with name " + value));
    }
}