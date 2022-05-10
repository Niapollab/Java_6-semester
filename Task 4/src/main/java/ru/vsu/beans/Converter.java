package ru.vsu.beans;

import com.opencsv.exceptions.*;

/**
 * Represents Converter.
 */
public interface Converter<T> {
    /**
     * Convetrs serializedObject to T.
     * @param arg0 Raw object.
     * @return Converted object.
     */
    T convert(String serializedObject) throws CsvDataTypeMismatchException, CsvConstraintViolationException;
}