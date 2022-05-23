package ru.vsu.beans;

import com.opencsv.exceptions.*;
import com.opencsv.bean.AbstractBeanField;
import ru.vsu.models.Gender;

/**
 * Represents GenderBeanField.
 */
public class GenderBeanField<T> extends AbstractBeanField<T, String> implements Converter<Gender> {
    /**
     * Convetrs arg0 to object.
     * @param arg0 Raw object.
     * @return Converted object.
     */
    @Override
    public Gender convert(String arg0) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
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