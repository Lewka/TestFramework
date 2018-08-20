package auto.core.element;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.internal.LocatingElementHandler;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static auto.core.element.ProxyFactory.createUIElementListProxy;
import static auto.core.element.ProxyFactory.createWebElementProxy;
import static auto.core.utils.ElementUtils.*;

@SuppressWarnings("unchecked")
public class ElementFieldDecorator extends DefaultFieldDecorator {

    public ElementFieldDecorator(ElementLocatorFactory factory) {
        super(factory);
    }

    @Override
    public Object decorate(ClassLoader loader, Field field) {
        if (isUIElement(field)) {
            return decorateUIElement(loader, field);
        } else if (isUIElementList(field)) {
            return decorateUIElementList(loader, field);
        }
        return super.decorate(loader, field);
    }

    protected <T extends UIElement> T decorateUIElement(ClassLoader loader, Field field) {
        WebElement elementToWrap = decorateWebElement(loader, field);
        return createUIElement((Class<T>) field.getType(), elementToWrap);
    }

    protected WebElement decorateWebElement(ClassLoader loader, Field field) {
        ElementLocator locator = factory.createLocator(field);
        InvocationHandler handler = new LocatingElementHandler(locator);

        return createWebElementProxy(loader, handler);
    }

    public static <T extends UIElement> T createUIElement(Class<T> elementClass, WebElement elementToWrap) {
        return newInstance(elementClass, elementToWrap);
    }

    public static <T> T newInstance(Class<T> clazz, WebElement elementToWrap) {
        try {
            return clazz.getDeclaredConstructor(WebElement.class).newInstance(elementToWrap);
        } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException | InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    protected <T extends UIElement> List<T> decorateUIElementList(ClassLoader loader, Field field) {
        Class<T> elementClass = (Class<T>) getGenericParameterClass(field);
        ElementLocator locator = factory.createLocator(field);

        InvocationHandler handler = new UIElementListProxyHandler<>(elementClass, locator);

        return createUIElementListProxy(loader, handler);
    }

}