package auto.core.element;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import static auto.core.element.ElementFieldDecorator.createUIElement;

public class UIElementListProxyHandler<T extends UIElement> implements InvocationHandler {

    private final Class<T> elementClass;
    private final ElementLocator locator;

    public UIElementListProxyHandler(Class<T> elementClass, ElementLocator locator) {
        this.elementClass = elementClass;
        this.locator = locator;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        List<T> elements = new LinkedList<>();
        for (WebElement element : locator.findElements()) {
            elements.add(createUIElement(elementClass, element));
        }
        try {
            return method.invoke(elements, objects);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }
}
