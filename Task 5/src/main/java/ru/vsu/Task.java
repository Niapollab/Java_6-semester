package ru.vsu;

import java.io.*;
import java.util.*;

public class Task {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        final String CONFIG_FILE = "resources\\config.properties";
        try (Reader reader = new FileReader(CONFIG_FILE)) {
            try {
                Properties properties = new Properties();
                properties.load(reader);

                Map<Class<?>, Class<?>> map = buildMapFromProperties(properties);

                Injector injector = new Injector(map);
                SomeBean someBean = new SomeBean();

                injector.inject(someBean);
                someBean.foo();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static Map<Class<?>, Class<?>> buildMapFromProperties(Properties properties) {
        Map<Class<?>, Class<?>> result = new Hashtable<Class<?>, Class<?>>();

        Set<String> keys = properties.stringPropertyNames();
        for (String key : keys) {
            String value = properties.getProperty(key);
            try {
                result.put(Class.forName(key), Class.forName(value));
            } catch (ClassNotFoundException ex) {
            }
        }

        return result;
    }
}