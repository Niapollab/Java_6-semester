package ru.vsu.beans;

import com.opencsv.exceptions.*;
import java.util.concurrent.atomic.AtomicInteger;
import com.opencsv.bean.AbstractBeanField;
import ru.vsu.models.Division;

public class DivisionBeanField<T> extends AbstractBeanField<T, String> {
    private static final AtomicInteger _idFactory = new AtomicInteger(-1);

    @Override
    protected Object convert(String arg0) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        return new Division(_idFactory.incrementAndGet(), arg0);
    }
}