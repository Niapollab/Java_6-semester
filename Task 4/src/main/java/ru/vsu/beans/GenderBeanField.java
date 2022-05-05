package ru.vsu.beans;

import com.opencsv.exceptions.*;
import com.opencsv.bean.AbstractBeanField;
import ru.vsu.models.Gender;

public class GenderBeanField<T> extends AbstractBeanField<T, String> {
    @Override
    protected Object convert(String arg0) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        switch (arg0.toLowerCase()) {
            case "male":
                return Gender.MALE;
            case "female":
                return Gender.FEMALE;
            default:
                return Gender.OTHER;
        }
    }
}