package ru.vsu;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Represents Injector.
 */
public final class Injector {
    private final Map<? extends Class<?>, ? extends Class<?>> _injectorRules;

    /**
     * Represents Injector constructor.
     * @param injectorRules List of injector rules.
     */
    public Injector(Map<? extends Class<?>, ? extends Class<?>> injectorRules) throws IllegalArgumentException {
        if (injectorRules == null)
            throw new IllegalArgumentException("Param injectorRules can't be null.");

        _injectorRules = injectorRules;
    }

    /**
     * Performs an injection into an object obj. All fields marked with the AutoInjectable attribute will be filled in.
     * @param <T> Type of object.
     * @param obj Object.
     * @return Object with injected fields.
     * @throws InstantiationException if this {@code Class} represents an abstract class, an interface, an array class, a primitive type, or void; or if the class has no nullary constructor; or if the instantiation fails for some other reason.
     * @throws IllegalAccessException if the class or its nullary constructor is not accessible.
     */
    public <T> T inject(T obj) throws IllegalArgumentException, InstantiationException, IllegalAccessException {
        if (obj == null)
            throw new IllegalArgumentException("Param obj can't be null.");

        List<Field> fieldsWithAnnotation = getFieldsWithAnnotation(obj, AutoInjectable.class);

        for (Field field : fieldsWithAnnotation) {
            Class<?> mappedValue = _injectorRules.get((Class<?>)field.getGenericType());

            if (mappedValue == null)
                continue;

            Object initedInjectable = mappedValue.newInstance();

            field.setAccessible(true);
            field.set(obj, initedInjectable);
        }

        return obj;
    }

    private static List<Field> getFieldsWithAnnotation(Object obj, Class<? extends Annotation> annotation) {
        List<Field> result = new ArrayList<Field>();
        Class<?> clazz = obj.getClass();

        while (clazz != null)
        {
            for (Field field : clazz.getDeclaredFields())
                if (field.isAnnotationPresent(annotation))
                    result.add(field);

            clazz = clazz.getSuperclass();
        }

        return result;
    }
}