package ru.vsu;

import org.junit.Test;
import java.util.Date;
import com.opencsv.exceptions.*;
import ru.vsu.beans.*;
import static org.junit.Assert.*;

public class DateBeanFieldTest {
    @Test
    public void successfulParseTest() throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        final String expression = "13.04.2021";
        Converter<Date> converter = new DateBeanField<>();

        Date actual = converter.convert(expression);

        assertEquals(new Date(2021 - 1900, 3, 13), actual);
    }

    @Test(expected = CsvDataTypeMismatchException.class)
    public void incorrectDateParseTest() throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        final String expression = "INCORRECT_DATA";
        Converter<Date> converter = new DateBeanField<>();

        converter.convert(expression);
    }
}