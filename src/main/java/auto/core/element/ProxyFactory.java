package auto.core.element;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.internal.WrapsElement;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.List;

@SuppressWarnings("unchecked")
public class ProxyFactory {

    public static <T extends WebElement> T createWebElementProxy(ClassLoader loader, InvocationHandler handler) {
        Class<?>[] interfaces = new Class[]{WebElement.class, WrapsElement.class, Locatable.class};
        return (T) Proxy.newProxyInstance(loader, interfaces, handler);
    }

    public static <T extends UIElement> List<T> createUIElementListProxy(ClassLoader loader, InvocationHandler handler) {
        return (List<T>) Proxy.newProxyInstance(loader, new Class[]{List.class}, handler);
    }

}