package ru.vsu;

import org.junit.Test;
import java.util.Hashtable;
import ru.vsu.implementation.*;
import ru.vsu.interfaces.*;
import static org.junit.Assert.*;

public class InjectorTest {
    @Test(expected = IllegalArgumentException.class)
    public void constructInjectorWithNullParam() {
        new Injector(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void injectWithNullParam() throws IllegalArgumentException, InstantiationException, IllegalAccessException {
        Injector injector = new Injector(new Hashtable<Class<?>, Class<?>>());
        injector.inject(null);
    }

    @Test
    public void injectWithoutAutoInjectable() throws IllegalArgumentException, InstantiationException, IllegalAccessException {
        Injector injector = new Injector(new Hashtable<Class<?>, Class<?>>());

        injector.inject(new Object());

        assertTrue(true);
    }

    @Test
    public void injectWithMultipleAutoInjectable() throws IllegalArgumentException, InstantiationException, IllegalAccessException {
        Hashtable<Class<?>, Class<?>> map = new Hashtable<Class<?>, Class<?>>();
        map.put(SomeInterface.class, B.class);
        map.put(SomeOtherInterface.class, C.class);

        SomeBean someBean = new SomeBean();
        Injector injector = new Injector(map);
        injector.inject(someBean);

        assertTrue(true);
    }
}