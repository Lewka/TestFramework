package auto.core.utils;

import auto.core.element.UIElement;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class ElementUtils {

    public static boolean isUIElement(Field field) {
        return isUIElement(field.getType());
    }

    public static boolean isUIElement(Class<?> clazz) {
        return UIElement.class.isAssignableFrom(clazz);
    }

    public static boolean isUIElementList(Field field) {
        if (!isParametrizedList(field)) {
            return false;
        }
        Class listParameterClass = getGenericParameterClass(field);
        return isUIElement(listParameterClass);
    }

    public static Class getGenericParameterClass(Field field) {
        Type genericType = field.getGenericType();
        return (Class) ((ParameterizedType) genericType).getActualTypeArguments()[0];
    }

    private static boolean isParametrizedList(Field field) {
        return isList(field) && hasGenericParameter(field);
    }

    private static boolean isList(Field field) {
        return List.class.isAssignableFrom(field.getType());
    }

    private static boolean hasGenericParameter(Field field) {
        return field.getGenericType() instanceof ParameterizedType;
    }

}