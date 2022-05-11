package ru.vsu;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

public final class Injector {
    private final Map<? extends Class<?>, ? extends Class<?>> _injectorRules;

    public Injector(Map<? extends Class<?>, ? extends Class<?>> injectorRules) {
        _injectorRules = injectorRules;
    }

    public <T> T inject(T obj) throws InstantiationException, IllegalAccessException {
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