package ru.vsu.beans;

import java.util.*;
import com.opencsv.exceptions.*;
import com.opencsv.bean.AbstractBeanField;
import java.text.SimpleDateFormat;

public class DateBeanField<T> extends AbstractBeanField<T, String> {
    public static final String PATTERN = "dd.MM.yyyy";
    public static final Locale LOCALE = Locale.ENGLISH;

    @Override
    protected Object convert(String arg0) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        try {
            return new SimpleDateFormat(PATTERN, LOCALE).parse(arg0);
        } catch (Exception e) {
            throw new CsvDataTypeMismatchException(arg0, Date.class, "Unable to cast object to Date.");
        }
    }
}