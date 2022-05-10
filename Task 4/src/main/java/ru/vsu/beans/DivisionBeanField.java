package ru.vsu.beans;

import com.opencsv.exceptions.*;
import java.util.concurrent.atomic.AtomicInteger;
import com.opencsv.bean.AbstractBeanField;
import ru.vsu.models.Division;

/**
 * Represents DivisionBeanField.
 */
public class DivisionBeanField<T> extends AbstractBeanField<T, String> implements Converter<Division> {
    private static final AtomicInteger _idFactory = new AtomicInteger(-1);

    /**
     * Convetrs arg0 to object.
     * @param arg0 Raw object.
     * @return Converted object.
     */
    @Override
    public Division convert(String arg0) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        return new Division(_idFactory.incrementAndGet(), arg0);
    }
}