package ru.vsu;

import java.lang.annotation.*;

/**
 * Represents AutoInjectable attribute.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface AutoInjectable {
}